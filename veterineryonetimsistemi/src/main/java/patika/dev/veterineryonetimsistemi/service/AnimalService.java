package patika.dev.veterineryonetimsistemi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.veterineryonetimsistemi.dto.request.AnimalRequest;
import patika.dev.veterineryonetimsistemi.dto.response.AnimalResponse;
import patika.dev.veterineryonetimsistemi.entity.Animal;
import patika.dev.veterineryonetimsistemi.mapper.IAnimalMapper;
import patika.dev.veterineryonetimsistemi.repository.IAnimalRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final IAnimalRepo iAnimalRepo;
    private final IAnimalMapper iAnimalMapper;


    public List<AnimalResponse> findAll() {   // bütün kayıtları getiriyor
        return iAnimalMapper.asOutPut(iAnimalRepo.findAll());
    }

    public AnimalResponse getById(Long id) {    // id'ye göre kayıt getiriyor
        return iAnimalMapper.asOutPut(iAnimalRepo.findById(id).orElseThrow(() -> new RuntimeException(id + "id'li animal bulunamadı")));
    }


    public AnimalResponse create(AnimalRequest request) {   //kayıt oluşturuyor

        Optional<Animal> isAuthorExist = iAnimalRepo.findByName(request.getName());
        if (isAuthorExist.isEmpty()) {
            Animal animalSaved = iAnimalRepo.save(iAnimalMapper.asEntity(request));
            return iAnimalMapper.asOutPut(animalSaved);
        }
        throw new RuntimeException("Bu hayvan daha önce sisteme kayıt olmuştur !!!");
    }

    public AnimalResponse update(Long id, AnimalRequest request) {

        Optional<Animal> animalFromDb = iAnimalRepo.findById(id);

        Optional<Animal> isAuthorExist = iAnimalRepo.findByName(request.getName());

        if (animalFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız hayvan sistemde bulunamadı. !!!.");
        }

        if (isAuthorExist.isPresent()) {
            throw new RuntimeException("Bu hayvan daha önce sisteme kayıt olmuştur !!!");
        }
        Animal animal = animalFromDb.get();
        iAnimalMapper.update(animal, request);
        return iAnimalMapper.asOutPut(iAnimalRepo.save(animal));
    }

    public void deleteById(Long id) {
        Optional<Animal> animalFromDb = iAnimalRepo.findById(id);
        if (animalFromDb.isPresent()) {
            iAnimalRepo.delete(animalFromDb.get());
        } else {
            throw new RuntimeException(id + "id li hayvan sistemde bulunamadı !!!");
        }
    }

    public AnimalResponse getByName(String name) {    //Hayvanlar isme göre filtrelenecek şekilde end point oluşturmak.
        return iAnimalMapper.asOutPut(iAnimalRepo.findByName(name).orElseThrow(() -> new RuntimeException(name + "isim'li hayvan bulunamadı")));
    }

    //Hayvan sahibinin sistemde kayıtlı tüm hayvanlarını görüntülemek için API end point'ini oluşturmak.
    // Hayvan sahibine göre hayvanlara filtreleme yapmalısın.
    // Müşterinin id si göndererek sahip olduğu tüm hayvanları listeledik  sonra controlerde çagırdık ve filtreleme yapıldı.
    public List<AnimalResponse> getAnimalsByOwnerId(Long customer_id) {
        return iAnimalMapper.asOutPut(iAnimalRepo.findByCustomer_id(customer_id));
    }

}
