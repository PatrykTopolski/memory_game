package FxComponents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonPropertyOrder({"id", "name","description","price", "available"})
public class Score {

    @JsonProperty("name")
    private String name;
    @JsonProperty("TimeInMilis")
    private double TimeInMilis;
    @JsonProperty("cardsDiscoveredFirstTime")
    private int cardsDiscoveredFirstTime;
    @JsonProperty("numberOfCards")
    private int numberOfCards;


}
