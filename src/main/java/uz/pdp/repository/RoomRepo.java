package uz.pdp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.domain.Room;
@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    Page<Room> findAllByHotelId(Integer id, Pageable pageable);
}
