package uz.pdp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.domain.Room;

import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    Page<Room> findAllByHotelId(Long id, Pageable pageable);

    Page<Room> findRoomById(Long id, Pageable pageable);

    boolean existsByNumberAndHotelId(Long number, Long hotel_id);

}
