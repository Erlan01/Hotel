package uz.pdp.model;

import lombok.*;

import java.io.Serializable;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto implements Serializable {

    private String name;
}
