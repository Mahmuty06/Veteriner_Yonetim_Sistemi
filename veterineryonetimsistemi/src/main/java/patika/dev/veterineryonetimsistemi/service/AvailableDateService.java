package patika.dev.veterineryonetimsistemi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.veterineryonetimsistemi.dto.request.AvailableDateRequest;
import patika.dev.veterineryonetimsistemi.dto.response.AvailableDateResponse;
import patika.dev.veterineryonetimsistemi.entity.AvailableDate;
import patika.dev.veterineryonetimsistemi.mapper.IAvailableDateMapper;
import patika.dev.veterineryonetimsistemi.repository.IAvailableDateRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateService {
    private final IAvailableDateRepo iAvailableDateRepo;
    private final IAvailableDateMapper iAvailableDateMapper;


    public List<AvailableDateResponse> findAll() {   // bütün kayıtları getiriyor
        return iAvailableDateMapper.asOutPut(iAvailableDateRepo.findAll());
    }

    public AvailableDateResponse getById(Long id) {    // id'ye göre kayıt getiriyor
        return iAvailableDateMapper.asOutPut(iAvailableDateRepo.findById(id).orElseThrow(() -> new RuntimeException(id + "id'li uygun tarih bulunamadı")));
    }

    public Optional<AvailableDate> findByAvailableDateAndDoctorId(LocalDate availableDate, Long doctor_id) {
        return iAvailableDateRepo.findByAvailableDateAndDoctorId(availableDate, doctor_id);
    }

    public AvailableDateResponse create(AvailableDateRequest request) {   //kayıt oluşturuyor

        Optional<AvailableDate> isAvailableDateExist = iAvailableDateRepo.findByAvailableDateAndDoctorId(
                request.getAvailableDate(), request.getDoctor().getId());
        if (isAvailableDateExist.isPresent()) {
            throw new RuntimeException("id 'si " + request.getDoctor().getId() + " olan doktorun bu tarihi doludur.");
        }
        AvailableDate availableDateSaved = iAvailableDateRepo.save(iAvailableDateMapper.asEntity(request));
        return iAvailableDateMapper.asOutPut(availableDateSaved);
    }

    public AvailableDateResponse update(Long id, AvailableDateRequest request) {

        Optional<AvailableDate> availableDateFromDb = iAvailableDateRepo.findById(id);

        Optional<AvailableDate> isAuthorExist = iAvailableDateRepo.findByAvailableDateAndDoctorId(
                request.getAvailableDate(), request.getDoctor().getId());

        if (availableDateFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız randevu sistemde bulunamadı. !!!.");
        }

        if (isAuthorExist.isPresent()) {
            throw new RuntimeException("Bu randevu daha önce sisteme kaydedilmiştir !!!");
        }
        AvailableDate availableDate = availableDateFromDb.get();
        iAvailableDateMapper.update(availableDate, request);
        return iAvailableDateMapper.asOutPut(iAvailableDateRepo.save(availableDate));
    }

    public void deleteById(Long id) {
        Optional<AvailableDate> availableDateFromDb = iAvailableDateRepo.findById(id);
        if (availableDateFromDb.isPresent()) {
            iAvailableDateRepo.delete(availableDateFromDb.get());
        } else {
            throw new RuntimeException(id + "id li uygun tarih sistemde bulunamadı !!!");
        }
    }
}



