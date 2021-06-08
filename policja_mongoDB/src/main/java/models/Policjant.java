package models;

import lombok.*;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Policjant implements Serializable {

    private ObjectId id;
    private String imie;
    private String nazwisko;
    private String stopien;
    private Komisariat komisariat;

}
