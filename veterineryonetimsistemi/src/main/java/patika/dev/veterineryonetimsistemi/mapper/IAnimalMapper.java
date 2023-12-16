package patika.dev.veterineryonetimsistemi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import patika.dev.veterineryonetimsistemi.dto.request.AnimalRequest;
import patika.dev.veterineryonetimsistemi.dto.response.AnimalResponse;
import patika.dev.veterineryonetimsistemi.entity.Animal;

import java.util.List;

@Mapper
public interface IAnimalMapper {
    Animal asEntity(AnimalRequest animalRequest);

    AnimalResponse asOutPut(Animal animal);

    List<AnimalResponse> asOutPut(List<Animal> animal);

    void update(@MappingTarget Animal entity, AnimalRequest request);
}
