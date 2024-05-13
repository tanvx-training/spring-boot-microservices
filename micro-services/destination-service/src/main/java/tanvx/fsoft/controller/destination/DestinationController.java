package tanvx.fsoft.controller.destination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanvx.fsoft.controller.destination.request.DestinationCreateApiRequest;
import tanvx.fsoft.controller.destination.request.DestinationUpdateApiRequest;
import tanvx.fsoft.controller.destination.response.DestinationCreateApiResponse;
import tanvx.fsoft.controller.destination.response.DestinationUpdateApiResponse;
import tanvx.fsoft.service.destination.DestinationService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/destination")
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping("/{destinationId}/edit")
    public ResponseEntity<DestinationUpdateApiResponse> updateDestination(@PathVariable("destinationId") Long id,
                                                                          @RequestBody DestinationUpdateApiRequest apiRequest) {
        // Logging
        log.info("{} - Method updateDestination is called with id {}, request: {}",
                DestinationController.class.getSimpleName(), id, apiRequest);
        // Invoke useCase
        return ResponseEntity.ok(destinationService.updateDestination(id, apiRequest));
    }


    @PostMapping("/create")
    public ResponseEntity<DestinationCreateApiResponse> createDestination(@RequestBody DestinationCreateApiRequest apiRequest) {
        // Logging
        log.info("{} - Method createDestination is called with request: {}",
                DestinationController.class.getSimpleName(), apiRequest);
        // Invoke useCase
        return ResponseEntity.ok(destinationService.createDestination(apiRequest));
    }
}
