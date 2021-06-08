package models;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Komisariat implements Serializable {

    private String id;
    private String miejscowosc;

}
