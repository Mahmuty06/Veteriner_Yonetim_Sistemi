package patika.dev.veterineryonetimsistemi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.veterineryonetimsistemi.dto.request.AppointmentRequest;
import patika.dev.veterineryonetimsistemi.dto.response.AppointmentResponse;
import patika.dev.veterineryonetimsistemi.entity.Appointment;
import patika.dev.veterineryonetimsistemi.entity.AvailableDate;
import patika.dev.veterineryonetimsistemi.mapper.IAppointmentMapper;
import patika.dev.veterineryonetimsistemi.repository.IAppointmentRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AppointmentService {

    private final IAppointmentRepo iAppointmentRepo;
    private final IAppointmentMapper iAppointmentMapper;
    private final AvailableDateService availableDateService;


    public List<AppointmentResponse> findAll() {   // bütün kayıtları getiriyor
        return iAppointmentMapper.asOutPut(iAppointmentRepo.findAll());
    }

    public AppointmentResponse getById(Long id) {    // id'ye göre kayıt getiriyor
        return iAppointmentMapper.asOutPut(iAppointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "id'li randevu bulunamadı")));
    }


    //Hayvanların her türlü muayenesi için doktorlardan uygun tarihlerde ve saatlerde randevu oluşturulmalıdır. Her doktor için sadece saat başı randevu oluşturulabilir.
    // Bir muayenenin sabit olarak bir saat süreceğini kabul edin.

    public AppointmentResponse create(AppointmentRequest request) {

        Optional<AvailableDate> checkAvailableDate = availableDateService.findByAvailableDateAndDoctorId(
                request.getAppointmentDate().toLocalDate(),
                request.getDoctor().getId()
        );

        if (checkAvailableDate.isEmpty())
            throw new RuntimeException("id 'si " + request.getDoctor().getId() + " olan doktorun bu tarihi doludur.");

        Optional<Appointment> checkAppointment = iAppointmentRepo.FindSearchAppointmentDateAndDoctorId(
                request.getAppointmentDate(),
                request.getDoctor().getId()
        );

        if (checkAppointment.isPresent())
            throw new RuntimeException("id 'si " + request.getDoctor().getId() + " olan doktorun bu saati doludur.");

        Appointment appointment = iAppointmentMapper.asEntity(request);

        iAppointmentRepo.save(appointment);

        return iAppointmentMapper.asOutPut(appointment);
    }

    public AppointmentResponse update(Long id, AppointmentRequest request) {

        Optional<Appointment> appointmentFromDb = iAppointmentRepo.findById(id);

        Optional<Appointment> isAuthorExist = iAppointmentRepo.findByAppointmentDate(request.getAppointmentDate());

        if (appointmentFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız randevu sistemde bulunamadı. !!!.");
        }

        if (isAuthorExist.isPresent()) {
            throw new RuntimeException("Bu randevu daha önce sisteme kaydedilmiştir !!!");
        }
        Appointment appointment = appointmentFromDb.get();
        iAppointmentMapper.update(appointment, request);
        return iAppointmentMapper.asOutPut(iAppointmentRepo.save(appointment));
    }

    public void deleteById(Long id) {
        Optional<Appointment> appointmentFromDb = iAppointmentRepo.findById(id);
        if (appointmentFromDb.isPresent()) {
            iAppointmentRepo.delete(appointmentFromDb.get());
        } else {
            throw new RuntimeException(id + "id li randevu sistemde bulunamadı !!!");
        }
    }

    //Randevular kullanıcı tarafından girilen tarih aralığına ve doktora göre filtrelenmelidir.
    public List<Appointment> appointmentsSearchDoctorId(LocalDateTime start, LocalDateTime finish, Long doctorId) {
        List<Appointment> appointmentList = iAppointmentRepo.findByAppointmentDateBetweenAndDoctorId(start, finish, doctorId);
        return appointmentList;

    }

    //Randevular kullanıcı tarafından girilen tarih aralığına ve Hayvana göre filtrelenmelidir.
    public List<Appointment> appointmentsSearchAnimalId(LocalDateTime start, LocalDateTime finish, Long animalId) {
        List<Appointment> appointmentList = iAppointmentRepo.findByAppointmentDateBetweenAndAnimalId(start, finish, animalId);
        return appointmentList;
    }
}


