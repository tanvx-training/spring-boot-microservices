package tanvx.fsoft.controller.destination.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class DestinationUpdateApiRequest {
    @JsonProperty("name")
    @NotNull(message = "Destination name is required.")
    @Size(max = 100, min = 1, message = "Length of destination name must be between 1 and 100 characters.")
    private String name;

    @JsonProperty("slug")
    @NotNull(message = "Destination slug is required.")
    @Size(max = 100, min = 1, message = "Length of destination slug must be between 1 and 100 characters.")
    private String slug;

    @JsonProperty("population")
    @Min(value = 1, message = "Value of destination population must be greater than 1.")
    private Integer population;

    @JsonProperty("capital_city")
    private String capitalCity;

    @JsonProperty("language")
    private String language;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("description")
    private String description;

    @JsonProperty("continent")
    @NotNull(message = "Destination continent is required.")
    private String continent;

    @JsonProperty("locationIdList")
    private List<Long> locationIds;
}
