package patika.dev.veterineryonetimsistemi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import patika.dev.veterineryonetimsistemi.dto.request.AppointmentRequest;
import patika.dev.veterineryonetimsistemi.dto.response.AppointmentResponse;
import patika.dev.veterineryonetimsistemi.entity.Appointment;
import patika.dev.veterineryonetimsistemi.service.AnimalService;
import patika.dev.veterineryonetimsistemi.service.AppointmentService;
import patika.dev.veterineryonetimsistemi.service.DoctorService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment")

public class AppointmentController {

    private final AppointmentService appointmentService;


    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService, AnimalService animalService) {
        this.appointmentService = appointmentService;


    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> findAll() {
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse getById(@PathVariable("id") Long id) {
        return appointmentService.getById(id);
    }


    //Randevular kullanıcı tarafından girilen tarih aralığına ve doktora göre filtrelenmelidir.
    @GetMapping("/appointmentsearchDoctorId")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> appointmentSearhDoctorId(@RequestParam(name = "appointment_start_date", required = false) LocalDate StartDate,
                                                      @RequestParam(name = "appointment_finish_date", required = false) LocalDate FinishDate,
                                                      @RequestParam(name = "doctor_id", required = false) Long DoctorId) {
        return appointmentService.appointmentsSearchDoctorId(StartDate.atStartOfDay(), FinishDate.atStartOfDay(), DoctorId);
    }

    //Randevular kullanıcı tarafından girilen tarih aralığına ve Hayvana göre filtrelenmelidir.
    @GetMapping("/appointmentsearchAnımalId")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> appointmentSearhAnimalId(@RequestParam(name = "appointment_start_date", required = false) LocalDate StartDate,
                                                      @RequestParam(name = "appointment_finish_date", required = false) LocalDate FinishDate,
                                                      @RequestParam(name = "animal_id", required = false) Long AnimalId) {
        return appointmentService.appointmentsSearchAnimalId(StartDate.atStartOfDay(), FinishDate.atStartOfDay(), AnimalId);
    }

    //Randevular sisteme tarih ve saat içerecek şekilde kaydedilmelidir. Bunun için LocalDateTime kullanılmalıdır.


    //Hayvanların her türlü muayenesi için doktorlardan uygun tarihlerde ve saatlerde randevu oluşturulmalıdır.
    // Her doktor için sadece saat başı randevu oluşturulabilir. Bir muayenenin sabit olarak bir saat süreceğini kabul edin.
    @PostMapping("/appointmentlistdoctorid")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse appointmentSaveDoctorId(@RequestBody AppointmentRequest request) {
        return appointmentService.create(request);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse update(@PathVariable Long id, @RequestBody AppointmentRequest request) {
        return appointmentService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        appointmentService.deleteById(id);
    }

}
