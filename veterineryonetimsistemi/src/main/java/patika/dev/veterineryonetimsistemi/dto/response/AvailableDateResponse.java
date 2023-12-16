package patika.dev.veterineryonetimsistemi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import patika.dev.veterineryonetimsistemi.entity.Doctor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailableDateResponse {
    private Doctor doctor;
    private LocalDate availableDate;
}
