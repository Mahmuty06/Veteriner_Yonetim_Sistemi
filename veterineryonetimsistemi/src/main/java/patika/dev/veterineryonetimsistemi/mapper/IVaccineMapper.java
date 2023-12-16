package patika.dev.veterineryonetimsistemi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import patika.dev.veterineryonetimsistemi.dto.request.VaccineRequest;
import patika.dev.veterineryonetimsistemi.dto.response.VaccineResponse;
import patika.dev.veterineryonetimsistemi.entity.Vaccine;

import java.util.List;

@Mapper
public interface IVaccineMapper {
    Vaccine asEntity(VaccineRequest vaccineRequest);

    VaccineResponse asOutPut(Vaccine vaccine);

    List<VaccineResponse> asOutPut(List<Vaccine> vaccineList);

    void update(@MappingTarget Vaccine entity, VaccineRequest request);
}
