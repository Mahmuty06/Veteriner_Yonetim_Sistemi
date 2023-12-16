package patika.dev.veterineryonetimsistemi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patika.dev.veterineryonetimsistemi.entity.AvailableDate;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IAvailableDateRepo extends JpaRepository<AvailableDate, Long> {

    Optional<AvailableDate> findByAvailableDateAndDoctorId(LocalDate localDate, Long doctorId);

}
