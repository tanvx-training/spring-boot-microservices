package tanvx.fsoft.controller.destination.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DestinationCreateApiRequest {
    @JsonProperty("name")
    @NotNull(message = "Destination name is required.")
    @Size(max = 100, min = 1, message = "Length of destination name must be between 1 and 100 characters.")
    private String name;

    @JsonProperty("continent")
    @NotNull(message = "Destination continent is required.")
    private String continent;
}
