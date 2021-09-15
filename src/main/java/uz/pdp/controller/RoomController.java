package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.repository.HotelRepo;
import uz.pdp.repository.RoomRepo;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomRepo roomRepo;

    private final HotelRepo hotelRepo;

    @Autowired
    public RoomController(RoomRepo roomRepo, HotelRepo hotelRepo) {
        this.roomRepo = roomRepo;
        this.hotelRepo = hotelRepo;
    }

    @GetMapping("/getAll")
}
