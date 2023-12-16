package patika.dev.veterineryonetimsistemi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import patika.dev.veterineryonetimsistemi.entity.Animal;
import patika.dev.veterineryonetimsistemi.entity.Doctor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentRequest {
    private Doctor doctor;
    private Animal animal;
    private LocalDateTime appointmentDate;
}
