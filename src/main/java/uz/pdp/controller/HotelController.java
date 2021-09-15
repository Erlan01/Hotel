package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.domain.Hotel;
import uz.pdp.model.HotelDto;
import uz.pdp.repository.HotelRepo;
import uz.pdp.repository.RoomRepo;

import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    private final HotelRepo hotelRepo;

    @Autowired
    public HotelController(HotelRepo hotelRepo, RoomRepo roomRepo) {
        this.hotelRepo = hotelRepo;
    }

    @GetMapping("/getAll")
    public Page<Hotel> getHotel(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return hotelRepo.findAll(pageable);
    }

    @GetMapping("/get/{id}")
    public Hotel get(@PathVariable Long id){
        Optional<Hotel> byId = hotelRepo.findById(id);
        return byId.get();
    }

    @PostMapping("/add")
    public String addHotel(@RequestBody HotelDto dto){
        boolean exists = hotelRepo.existsByName(dto.getName());
        if (exists){
            throw new RuntimeException("This hotel name exist");
        }
        Hotel hotel = Hotel.builder().name(dto.getName()).build();
        hotelRepo.save(hotel);
        return "Hotel added";
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody HotelDto dto){
        Optional<Hotel> optionalHotel = hotelRepo.findById(id);
        if (!optionalHotel.isPresent()) throw new RuntimeException("Hotel not found");
        Hotel hotel = optionalHotel.get();
        hotel.setName(dto.getName() != null ? dto.getName() : hotel.getName());
        hotelRepo.save(hotel);
        return "Hotel edited";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        try {
            hotelRepo.deleteById(id);
            return "Hotel deleted";
        } catch (Exception e) {
            return "Error in deleting";
        }
    }
}
