package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Policjant implements Serializable {

    @JsonProperty("_id") private String id;
    @JsonProperty("_rev") private String revision;

    private String imie;
    private String nazwisko;
    private String stopien;
    private Komisariat komisariat;

}
