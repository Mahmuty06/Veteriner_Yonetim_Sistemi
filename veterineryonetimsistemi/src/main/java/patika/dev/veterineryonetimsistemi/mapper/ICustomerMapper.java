package patika.dev.veterineryonetimsistemi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import patika.dev.veterineryonetimsistemi.dto.request.CustomerRequest;
import patika.dev.veterineryonetimsistemi.dto.response.CustomerResponse;
import patika.dev.veterineryonetimsistemi.entity.Customer;

import java.util.List;

@Mapper

public interface ICustomerMapper {

    Customer asEntity(CustomerRequest customerRequest);

    CustomerResponse asOutPut(Customer customer);

    List<CustomerResponse> asOutPut(List<Customer> customer);

    void update(@MappingTarget Customer entity, CustomerRequest request);


}
