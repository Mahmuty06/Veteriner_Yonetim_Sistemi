package patika.dev.veterineryonetimsistemi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.veterineryonetimsistemi.dto.request.CustomerRequest;
import patika.dev.veterineryonetimsistemi.dto.response.CustomerResponse;
import patika.dev.veterineryonetimsistemi.entity.Customer;
import patika.dev.veterineryonetimsistemi.mapper.ICustomerMapper;
import patika.dev.veterineryonetimsistemi.repository.ICustomerRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ICustomerRepo iCustomerRepo;
    private final ICustomerMapper iCustomerMapper;


    public List<CustomerResponse> findAll() {   // bütün kayıtları getiriyor
        return iCustomerMapper.asOutPut(iCustomerRepo.findAll());
    }

    public CustomerResponse getById(Long id) {    // id'ye göre kayıt getiriyor
        return iCustomerMapper.asOutPut(iCustomerRepo.findById(id).orElseThrow(() -> new RuntimeException(id + "id'li customer bulunamadı")));
    }

    public CustomerResponse create(CustomerRequest request) {   //kayıt oluşturuyor

        Optional<Customer> isAuthorExist = iCustomerRepo.findByNameAndMail(request.getName(), request.getMail());
        if (isAuthorExist.isEmpty()) {
            Customer customerSaved = iCustomerRepo.save(iCustomerMapper.asEntity(request));
            return iCustomerMapper.asOutPut(customerSaved);
        }
        throw new RuntimeException("Bu müşteri daha önce sisteme kayıt olmuştur !!!");
    }

    public CustomerResponse update(Long id, CustomerRequest request) {

        Optional<Customer> customerFromDb = iCustomerRepo.findById(id);

        Optional<Customer> isAuthorExist = iCustomerRepo.findByNameAndMail(request.getName(), request.getMail());

        if (customerFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız müşteri sistemde bulunamadı. !!!.");
        }

        if (isAuthorExist.isPresent()) {
            throw new RuntimeException("Bu müşteri daha önce sisteme kayıt olmuştur !!!");
        }
        Customer customer = customerFromDb.get();
        iCustomerMapper.update(customer, request);
        return iCustomerMapper.asOutPut(iCustomerRepo.save(customer));
    }

    public void deleteById(Long id) {
        Optional<Customer> customerFromDb = iCustomerRepo.findById(id);
        if (customerFromDb.isPresent()) {
            iCustomerRepo.delete(customerFromDb.get());
        } else {
            throw new RuntimeException(id + "id li Müşteri sistemde bulunamadı !!!");
        }

    }

    public CustomerResponse getByName(String name) { //Hayvan sahipleri isme göre filtrelenecek şekilde end point oluşturmak.
        return iCustomerMapper.asOutPut(iCustomerRepo.findByName(name).orElseThrow(() -> new RuntimeException(name + "isim'li hayvan bulunamadı")));

    }
}
