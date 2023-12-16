package patika.dev.veterineryonetimsistemi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.veterineryonetimsistemi.dto.request.DoctorRequest;
import patika.dev.veterineryonetimsistemi.dto.response.DoctorResponse;
import patika.dev.veterineryonetimsistemi.entity.Doctor;
import patika.dev.veterineryonetimsistemi.mapper.IDoctorMapper;
import patika.dev.veterineryonetimsistemi.repository.IDoctorRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final IDoctorRepo iDoctorRepo;
    private final IDoctorMapper iDoctorMapper;


    public List<DoctorResponse> findAll() {   // bütün kayıtları getiriyor
        return iDoctorMapper.asOutPut(iDoctorRepo.findAll());
    }


    public DoctorResponse getById(Long id) {    // id'ye göre kayıt getiriyor
        return iDoctorMapper.asOutPut(iDoctorRepo.findById(id).orElseThrow(()
                -> new RuntimeException(id + "id'li doktor bulunamadı")));
    }

    public DoctorResponse create(DoctorRequest request) {   //kayıt oluşturuyor

        Optional<Doctor> isAuthorExist = iDoctorRepo.findByNameAndMail(request.getName(), request.getMail());
        if (isAuthorExist.isEmpty()) {
            Doctor doctorSaved = iDoctorRepo.save(iDoctorMapper.asEntity(request));
            return iDoctorMapper.asOutPut(doctorSaved);
        }
        throw new RuntimeException("Bu doktor daha önce sisteme kayıt olmuştur !!!");
    }

    public DoctorResponse update(Long id, DoctorRequest request) {

        Optional<Doctor> doctorFromDb = iDoctorRepo.findById(id);

        Optional<Doctor> isAuthorExist = iDoctorRepo.findByNameAndMail(request.getName(), request.getMail());

        if (doctorFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız müşteri sistemde bulunamadı. !!!.");
        }

        if (isAuthorExist.isPresent()) {
            throw new RuntimeException("Bu müşteri daha önce sisteme kayıt olmuştur !!!");
        }
        Doctor doctor = doctorFromDb.get();
        iDoctorMapper.update(doctor, request);
        return iDoctorMapper.asOutPut(iDoctorRepo.save(doctor));
    }

    public void deleteById(Long id) {
        Optional<Doctor> doctorFromDb = iDoctorRepo.findById(id);
        if (doctorFromDb.isPresent()) {
            iDoctorRepo.delete(doctorFromDb.get());
        } else {
            throw new RuntimeException(id + "id li Müşteri sistemde bulunamadı !!!");
        }
    }


}

