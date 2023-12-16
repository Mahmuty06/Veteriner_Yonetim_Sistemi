package patika.dev.veterineryonetimsistemi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patika.dev.veterineryonetimsistemi.entity.Animal;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAnimalRepo extends JpaRepository<Animal, Long> {
    Optional<Animal> findByName(String name);


    List<Animal> findByCustomer_id(Long customer_id);


}
