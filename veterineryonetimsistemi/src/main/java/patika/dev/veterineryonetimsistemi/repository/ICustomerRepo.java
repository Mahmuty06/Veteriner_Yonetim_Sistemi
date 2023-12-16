package patika.dev.veterineryonetimsistemi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patika.dev.veterineryonetimsistemi.entity.Customer;

import java.util.Optional;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByNameAndMail(String name, String mail);

    Optional<Customer> findByName(String name);

}
