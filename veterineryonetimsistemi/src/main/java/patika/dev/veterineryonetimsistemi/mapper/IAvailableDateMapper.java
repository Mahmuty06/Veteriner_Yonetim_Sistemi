package patika.dev.veterineryonetimsistemi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import patika.dev.veterineryonetimsistemi.dto.request.AvailableDateRequest;
import patika.dev.veterineryonetimsistemi.dto.response.AvailableDateResponse;
import patika.dev.veterineryonetimsistemi.entity.AvailableDate;

import java.util.List;

@Mapper

public interface IAvailableDateMapper {
    AvailableDate asEntity(AvailableDateRequest availableDateRequest);

    AvailableDateResponse asOutPut(AvailableDate availableDate);

    List<AvailableDateResponse> asOutPut(List<AvailableDate> availableDateList);

    void update(@MappingTarget AvailableDate entity, AvailableDateRequest request);
}
