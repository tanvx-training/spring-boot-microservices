package tanvx.fsoft.service.destination;

import tanvx.fsoft.controller.destination.request.DestinationCreateApiRequest;
import tanvx.fsoft.controller.destination.request.DestinationUpdateApiRequest;
import tanvx.fsoft.controller.destination.response.DestinationCreateApiResponse;
import tanvx.fsoft.controller.destination.response.DestinationUpdateApiResponse;

public interface DestinationService {
    /**
     * Update a destination
     * @param apiRequest DestinationUpdateApiRequest
     * @return DestinationUpdateApiResponse
     */
    DestinationUpdateApiResponse updateDestination(Long id, DestinationUpdateApiRequest apiRequest);

    /**
     * Create a new destination
     * @param apiRequest DestinationCreateApiRequest
     * @return DestinationCreateApiResponse
     */
    DestinationCreateApiResponse createDestination(DestinationCreateApiRequest apiRequest);
}
