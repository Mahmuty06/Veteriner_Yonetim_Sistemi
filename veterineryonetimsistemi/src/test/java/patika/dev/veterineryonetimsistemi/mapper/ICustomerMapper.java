package patika.dev.veterineryonetimsistemi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import patika.dev.veterineryonetimsistemi.dto.response.CustomerResponse;
import patika.dev.veterineryonetimsistemi.entity.Customer;
import patika.dev.veterineryonetimsistemi.repository.ICustomerRepo;

import java.util.List;

@Mapper

public interface ICustomerMapper {
    Customer asEntity(ICustomerRepo iCustomerRepo)  ;

    CustomerResponse asOutPut(Customer customer);

    List<CustomerResponse>asOutPut(List<Customer>customer);

    void update(@MappingTarget Customer entity, ICustomerRepo repo);

}
