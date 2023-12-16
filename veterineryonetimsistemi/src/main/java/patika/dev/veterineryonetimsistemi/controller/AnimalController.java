package patika.dev.veterineryonetimsistemi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import patika.dev.veterineryonetimsistemi.core.result.ResultData;
import patika.dev.veterineryonetimsistemi.core.utilies.ResultHelper;
import patika.dev.veterineryonetimsistemi.dto.request.AnimalRequest;
import patika.dev.veterineryonetimsistemi.dto.response.AnimalResponse;
import patika.dev.veterineryonetimsistemi.service.AnimalService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/animal")
public class AnimalController {


    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> findAll() {
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse getById(@PathVariable("id") Long id) {
        return animalService.getById(id);
    }

    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse getByName(@RequestParam(name = "name") String name) {
        return animalService.getByName(name);
    }


    //Hayvan sahibinin sistemde kayıtlı tüm hayvanlarını görüntülemek için API end point'ini oluşturmak.
    // Hayvan sahibine göre hayvanlara filtreleme yapmalısın.
    @GetMapping("/animalcust")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> getAnimalsByOwnerId(@RequestParam(name = "id", required = false) Long id,
                                                    @RequestParam(name = "name", required = false) String name,
                                                    @RequestParam(name = "species", required = false) String species) {
        List<AnimalResponse> animalResponseList = animalService.getAnimalsByOwnerId(id);
        if (name != null) {
            animalResponseList = animalResponseList.stream().filter(t -> t.getName().equalsIgnoreCase(name)).collect(Collectors.toList());

        }
        if (species != null) {
            animalResponseList = animalResponseList.stream().filter(t -> t.getSpecies().equalsIgnoreCase(species)).collect(Collectors.toList());
        }

        return animalResponseList;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@RequestBody AnimalRequest animal) {
        AnimalResponse animalResponse = animalService.create(animal);
        return ResultHelper.created(animalResponse);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse update(@PathVariable Long id, @RequestBody AnimalRequest request) {
        return animalService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        animalService.deleteById(id);
    }

}
