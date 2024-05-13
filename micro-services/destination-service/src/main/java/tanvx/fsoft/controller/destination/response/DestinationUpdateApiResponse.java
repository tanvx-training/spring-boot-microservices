package tanvx.fsoft.controller.destination.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Builder
public class DestinationUpdateApiResponse {

    @JsonProperty("destination_id")
    private Long destinationId;

    @JsonProperty("delete_flg")
    private Boolean deleteFlg;

    @JsonProperty("update_at")
    private Instant updatedAt;

    @JsonProperty("update_by")
    private String updatedBy;
}
