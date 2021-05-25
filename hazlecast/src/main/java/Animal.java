import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Animal implements Serializable {

    private String species;
    private Integer amount;

}
