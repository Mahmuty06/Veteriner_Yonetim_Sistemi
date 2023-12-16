package patika.dev.veterineryonetimsistemi.dto.response;

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
public class AppointmentResponse {
    private Doctor doctor;
    private Animal animal;
    private LocalDateTime appointmentDate;
}
