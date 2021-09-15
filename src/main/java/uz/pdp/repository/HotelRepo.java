package uz.pdp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.domain.Hotel;


@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {

    boolean existsByName(String name);
}
