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
public class Komisariat implements Serializable {

    private ObjectId id;
    private String miejscowosc;

}
