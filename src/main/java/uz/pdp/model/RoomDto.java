package uz.pdp.model;

import lombok.*;

import java.io.Serializable;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto implements Serializable {

    private Long number;

    private Short floor;

    private Long size;

    private Long hotelId;
}
