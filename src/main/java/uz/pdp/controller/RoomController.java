package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.domain.Hotel;
import uz.pdp.domain.Room;
import uz.pdp.model.RoomDto;
import uz.pdp.repository.HotelRepo;
import uz.pdp.repository.RoomRepo;

import java.util.Objects;
import java.util.Optional;

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
    public Page<Room> getRooms(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return roomRepo.findAll(pageable);
    }


    @GetMapping("/forHotel/{hotelId}")
    public Page<Room> forHotel(@PathVariable Long hotelId, @RequestParam int page) {
        Optional<Hotel> optionalHotel = hotelRepo.findById(hotelId);
        if (!optionalHotel.isPresent()) throw new RuntimeException("Hotel not found");
        Pageable pageable = PageRequest.of(page, 5);
        return roomRepo.findAllByHotelId(hotelId, pageable);
    }


    @GetMapping("/get/{id}")
    public Room getRoomId(@PathVariable Long id){
        Optional<Room> optionalRoom = roomRepo.findById(id);
        if (!optionalRoom.isPresent()) throw new RuntimeException("Room not found");
        return optionalRoom.get();
    }


    @PostMapping("/add")
    public String add(@RequestBody RoomDto dto){
        Optional<Hotel> optionalHotel = hotelRepo.findById(dto.getHotelId());
        if (!optionalHotel.isPresent()) throw new RuntimeException("Hotel not found");
                Room room = Room.builder()
                        .number(dto.getNumber())
                        .floor(dto.getFloor())
                        .size(dto.getSize())
                        .hotel(optionalHotel.get()).build();
                roomRepo.save(room);
                return "Room added";
    }


    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody RoomDto dto){
        Optional<Room> optionalRoom = roomRepo.findById(id);
        if (!optionalRoom.isPresent()) throw new RuntimeException("Room not found");

        Optional<Hotel> optionalHotel = hotelRepo.findById(dto.getHotelId());
        if (!optionalHotel.isPresent()) throw new RuntimeException("Hotel not found");

        boolean exists = roomRepo.existsByNumberAndHotelId(dto.getNumber(), dto.getHotelId());
        if (exists){
            throw new RuntimeException("Room number exist");
        }

        Room room = optionalRoom.get();
        room = Room.builder()
                .number(dto.getNumber() != null ? dto.getNumber() : room.getNumber())
                .floor(dto.getFloor() != null ? dto.getFloor() : room.getFloor())
                .size(dto.getSize() != null ? dto.getSize() : room.getSize())
                .hotel(optionalHotel.get()).build();
        roomRepo.save(room);
        return "Room edited";
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        try {
            roomRepo.deleteById(id);
            return "Room deleted";
        } catch (Exception e) {
            return "Error in deleting";
        }
    }
}
