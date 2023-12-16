package patika.dev.veterineryonetimsistemi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.veterineryonetimsistemi.dto.request.VaccineRequest;
import patika.dev.veterineryonetimsistemi.dto.response.VaccineResponse;
import patika.dev.veterineryonetimsistemi.entity.Vaccine;
import patika.dev.veterineryonetimsistemi.mapper.IVaccineMapper;
import patika.dev.veterineryonetimsistemi.repository.IVaccineRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class VaccineService {
    private final IVaccineRepo iVaccineRepo;
    private final IVaccineMapper iVaccineMapper;


    public List<VaccineResponse> findAll() {   // bütün kayıtları getiriyor
        return iVaccineMapper.asOutPut(iVaccineRepo.findAll());
    }

    public VaccineResponse getById(Long id) {    // id'ye göre kayıt getiriyor
        return iVaccineMapper.asOutPut(iVaccineRepo.findById(id).orElseThrow(() -> new RuntimeException(id + "id'li aşı bulunamadı")));
    }

    public VaccineResponse create(VaccineRequest request) {   //kayıt oluşturuyor

        Optional<Vaccine> isAuthorExist = iVaccineRepo.findByNameAndCodeAndProtectionFinishDate
                (request.getName(), request.getCode(), request.getProtectionFinishDate());
        if (isAuthorExist.isEmpty()) {
            Vaccine vaccineSaved = iVaccineRepo.save(iVaccineMapper.asEntity(request));
            return iVaccineMapper.asOutPut(vaccineSaved);
        }
        throw new RuntimeException("Bu aşı sistemde kayıtlıdır !!!");
    }

    public VaccineResponse update(Long id, VaccineRequest request) {

        Optional<Vaccine> vaccineFromDb = iVaccineRepo.findById(id);

        Optional<Vaccine> isAuthorExist = iVaccineRepo.findByNameAndCodeAndProtectionFinishDate
                (request.getName(), request.getCode(), request.getProtectionFinishDate());

        if (vaccineFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız aşı sistemde bulunamadı. !!!.");
        }

        if (isAuthorExist.isPresent()) {
            throw new RuntimeException("Bu aşı daha önce sisteme kaydedilmiştir !!!");
        }
        Vaccine vaccine = vaccineFromDb.get();
        iVaccineMapper.update(vaccine, request);
        return iVaccineMapper.asOutPut(iVaccineRepo.save(vaccine));
    }

    public void deleteById(Long id) {
        Optional<Vaccine> vaccineFromDb = iVaccineRepo.findById(id);
        if (vaccineFromDb.isPresent()) {
            iVaccineRepo.delete(vaccineFromDb.get());
        } else {
            throw new RuntimeException(id + "id li Müşteri sistemde bulunamadı !!!");
        }
    }

    //Eğer hastaya ait aynı tip aşının (adı ve kodu aynı olan aşı) aşı koruyuculuk bitiş tarihi daha gelmemiş ise sisteme yeni aşı girilememelidir.
    // Aşı kodlarından ve aşı bitiş tarihlerinden bunu kontrol edebilirsin.
    public List<VaccineResponse> getAnimalsVaccine(String code, Long animal_id, LocalDate protection_finish_date) {
        return iVaccineMapper.asOutPut(iVaccineRepo.findByCodeAndAnimalIdAndProtectionFinishDateAfter
                (code, animal_id, protection_finish_date));
    }


    //Hayvan id’sine göre belirli bir hayvana ait tüm aşı kayıtlarını listelemek için gerekli API end point'ini oluşturmak.
    public List<VaccineResponse> getVaccineListAnimalId(Long animal_id) {
        return iVaccineMapper.asOutPut(iVaccineRepo.findByAnimalId(animal_id));
    }


    //Kullanıcının aşı koruyuculuk bitiş tarihi yaklaşan hayvanları listeleyebilmesi için kullanıcının gireceği başlangıç ve bitiş tarihlerine göre
    // aşı koruyuculuk tarihi bu aralıkta olan hayvanların listesini geri döndüren API end  point'ini oluşturmak.
    public List<VaccineResponse> getFinishStartNewVaccine(LocalDate startDate, LocalDate finishDate) {
        return iVaccineMapper.asOutPut(iVaccineRepo.findSaveStartAndFinishDate(startDate, finishDate));
    }

}
