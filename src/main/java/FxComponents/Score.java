package FxComponents;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Score {

    String name;
    double TimeInMilis;
}
