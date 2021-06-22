import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ksiazka implements Serializable {
    private String id;
    private String tytul;
    private Integer ocena;
}