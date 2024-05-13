package tanvx.fsoft.service.destination;

import fsoft.tanvx.exception.ServiceException;
import fsoft.tanvx.exception.ValidationException;
import fsoft.tanvx.utils.MessageUtil;
import fsoft.tanvx.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tanvx.fsoft.controller.destination.request.DestinationCreateApiRequest;
import tanvx.fsoft.controller.destination.request.DestinationUpdateApiRequest;
import tanvx.fsoft.controller.destination.response.DestinationCreateApiResponse;
import tanvx.fsoft.controller.destination.response.DestinationUpdateApiResponse;
import tanvx.fsoft.enums.Continent;
import tanvx.fsoft.model.MessageProperties;
import tanvx.fsoft.repository.destination.Destination;
import tanvx.fsoft.repository.destination.DestinationRepository;
import tanvx.fsoft.repository.location.LocationRepository;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {

    private final MessageUtil messageUtil;

    private final ValidationUtil validationUtil;

    private final LocationRepository locationRepository;

    private final DestinationRepository destinationRepository;

    @Override
    @Transactional
    public DestinationUpdateApiResponse updateDestination(Long id, DestinationUpdateApiRequest apiRequest) {
        // Logging
        log.info("{} - Method updateDestination is called with id: {}, destination: {}", DestinationServiceImpl.class.getSimpleName(), id, apiRequest);
        try {
            // Validate id
            destinationRepository.findById(id)
                    .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND,
                            messageUtil.getMessage(MessageProperties.DESTINATION_ID_NOT_VALID)));
            // Validate request
            validationUtil.validateRequest(apiRequest);
            // Check existing destination name
            destinationRepository.findDestinationByName(apiRequest.getName())
                    .ifPresent(o -> {
                        log.info("{} - Destination name already exists: {}", DestinationServiceImpl.class, apiRequest.getName());
                        throw new ServiceException(HttpStatus.BAD_REQUEST, messageUtil.getMessage(MessageProperties.DESTINATION_NAME_ALREADY_EXISTS));
                    });
            // Check valid list of id location
            if (!CollectionUtils.isEmpty(apiRequest.getLocationIds())) {
                int idCount = locationRepository.findAllByLocationIdInAndDeleteFlg(apiRequest.getLocationIds(), Boolean.FALSE)
                        .size();
                if (apiRequest.getLocationIds().size() != idCount) {
                    throw new ServiceException(HttpStatus.BAD_REQUEST, messageUtil.getMessage(MessageProperties.LOCATION_ID_NOT_VALID));
                }
            }
            // Check enum continent
            if (Objects.nonNull(apiRequest.getContinent())) {
                Continent.valueOf(apiRequest.getContinent());
            }
            // Build entity
            Destination destination = Destination.builder()
                    .name(apiRequest.getName())
                    .slug(convertToSlug(apiRequest.getName()))
                    .population(apiRequest.getPopulation())
                    .capitalCity(apiRequest.getCapitalCity())
                    .language(apiRequest.getLanguage())
                    .currency(apiRequest.getCurrency())
                    .description(apiRequest.getDescription())
                    .continent(apiRequest.getContinent())
                    .build();
            // Save entity
            destinationRepository.save(destination);
            // Update location display_flg
            locationRepository.findAllByDestinationDestinationIdAndDeleteFlg(id, Boolean.FALSE)
                    .forEach(location -> {
                        location.setDisplayFlg(Boolean.TRUE);
                        locationRepository.save(location);
                    });
            // Return response
            return DestinationUpdateApiResponse.builder()
                    .destinationId(destination.getDestinationId())
                    .deleteFlg(destination.getDeleteFlg())
                    .updatedAt(destination.getUpdatedAt())
                    .updatedBy(destination.getUpdatedBy())
                    .build();
        } catch (IllegalArgumentException e) {
            throw new ValidationException("continent",
                    String.format("%s does not have such value => [%s]", Continent.class.getSimpleName(), apiRequest.getContinent()));
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, messageUtil.getMessage(MessageProperties.RESPONSE_500));
        }
    }

    @Override
    @Transactional
    public DestinationCreateApiResponse createDestination(DestinationCreateApiRequest apiRequest) {
        // Logging
        log.info("{} - Method createDestination is called with destination: {}", DestinationServiceImpl.class.getSimpleName(), apiRequest);
        try {
            // Validate request
            validationUtil.validateRequest(apiRequest);
            // Check existing destination name
            destinationRepository.findDestinationByName(apiRequest.getName())
                    .ifPresent(o -> {
                        log.info("{} - Destination name already exists: {}", DestinationServiceImpl.class, apiRequest.getName());
                        throw new ServiceException(HttpStatus.BAD_REQUEST, messageUtil.getMessage(MessageProperties.DESTINATION_NAME_ALREADY_EXISTS));
                    });
            // Check enum continent
            if (Objects.nonNull(apiRequest.getContinent())) {
                Continent.valueOf(apiRequest.getContinent());
            }
            // Build entity
            Destination destination = Destination.builder()
                    .name(apiRequest.getName())
                    .slug(convertToSlug(apiRequest.getName()))
                    .population(0)
                    .capitalCity(null)
                    .language(null)
                    .currency(null)
                    .description(null)
                    .continent(apiRequest.getContinent())
                    .deleteFlg(Boolean.FALSE)
                    .build();
            // Save entity
            destinationRepository.save(destination);

            // Return response
            return DestinationCreateApiResponse.builder()
                    .destinationId(destination.getDestinationId())
                    .deleteFlg(destination.getDeleteFlg())
                    .createdAt(destination.getCreatedAt())
                    .createdBy(destination.getCreatedBy())
                    .build();
        } catch (IllegalArgumentException e) {
            throw new ValidationException("continent",
                    String.format("%s does not have such value => [%s]", Continent.class.getSimpleName(), apiRequest.getContinent()));
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, messageUtil.getMessage(MessageProperties.RESPONSE_500));
        }
    }

    // Method to convert a string from name to slug
    public static String convertToSlug(String name) {
        // Remove punctuation, special characters, and double spaces
        String cleanedName = name.replaceAll("[^\\p{L}\\p{N}]+", " ");
        // Convert all characters to lowercase
        String lowercaseName = cleanedName.toLowerCase();
        // Replace spaces with dashes
        return lowercaseName.replaceAll("\\s+", "-");
    }
}
