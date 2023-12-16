package patika.dev.veterineryonetimsistemi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import patika.dev.veterineryonetimsistemi.entity.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAppointmentRepo extends JpaRepository<Appointment, Long> {
    //  Optional<Appointment> findByAppointmentDate(LocalDate appointmentDate);

    //Randevular sisteme tarih ve saat içerecek şekilde kaydedilmelidir. Bunun için LocalDateTime kullanılmalıdır.
    Optional<Appointment> findByAppointmentDate(LocalDateTime appointmentDate);


    List<Appointment> findByAppointmentDateAndDoctorId(LocalDateTime appointmentDate, Long doctor_id);


    // Hayvanların her türlü muayenesi için doktorlardan uygun tarihlerde ve saatlerde randevu oluşturulmalıdır.
    // Her doktor için sadece saat başı randevu oluşturulabilir. Bir muayenenin sabit olarak bir saat süreceğini kabul edin.
    @Query(value = "SELECT * FROM appointment WHERE appointment_date = ?1 AND doctor_id = ?2", nativeQuery = true)
    Optional<Appointment> FindSearchAppointmentDateAndDoctorId(LocalDateTime appointmentDate, Long doctor_id);

    //Randevular kullanıcı tarafından girilen tarih aralığına ve doktora göre filtrelenmelidir.
    List<Appointment> findByAppointmentDateBetweenAndDoctorId(LocalDateTime start, LocalDateTime finish, Long doctorId);

    //Randevular kullanıcı tarafından girilen tarih aralığına ve Hayvana göre filtrelenmelidir.
    List<Appointment> findByAppointmentDateBetweenAndAnimalId(LocalDateTime start, LocalDateTime finish, Long animalId);
}


