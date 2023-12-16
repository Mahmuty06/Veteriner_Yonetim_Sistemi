package patika.dev.veterineryonetimsistemi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patika.dev.veterineryonetimsistemi.entity.Doctor;

import java.util.Optional;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByNameAndMail(String name, String mail);

    Optional<Doctor> findById(Long id);

}
