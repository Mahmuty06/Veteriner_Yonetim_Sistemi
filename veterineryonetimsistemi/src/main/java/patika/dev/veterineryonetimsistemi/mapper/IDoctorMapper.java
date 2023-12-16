package patika.dev.veterineryonetimsistemi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import patika.dev.veterineryonetimsistemi.dto.request.DoctorRequest;
import patika.dev.veterineryonetimsistemi.dto.response.DoctorResponse;
import patika.dev.veterineryonetimsistemi.entity.Doctor;

import java.util.List;

@Mapper
public interface IDoctorMapper {

    Doctor asEntity(DoctorRequest doctorRequest);

    DoctorResponse asOutPut(Doctor doctor);

    List<DoctorResponse> asOutPut(List<Doctor> doctorList);

    void update(@MappingTarget Doctor entity, DoctorRequest request);

}
