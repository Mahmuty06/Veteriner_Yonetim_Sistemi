package patika.dev.veterineryonetimsistemi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import patika.dev.veterineryonetimsistemi.dto.request.VaccineRequest;
import patika.dev.veterineryonetimsistemi.dto.response.VaccineResponse;
import patika.dev.veterineryonetimsistemi.service.VaccineService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vaccine")
public class VaccineController {
    private final VaccineService vaccineService;

    @Autowired
    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<VaccineResponse> findAll() {
        return vaccineService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse getById(@PathVariable("id") Long id) {
        return vaccineService.getById(id);
    }

    // girilen json'da code animal_id ve start date kontrolü yapılıp eğer servisten bu şartlara uygun olmayan kayıt varsa yeni aşı ekleme


    //Eğer hastaya ait aynı tip aşının (adı ve kodu aynı olan aşı) aşı koruyuculuk bitiş tarihi daha gelmemiş ise
    // sisteme yeni aşı girilememelidir. Aşı kodlarından ve aşı bitiş tarihlerinden bunu kontrol edebilirsin.
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public VaccineResponse save(@RequestBody VaccineRequest vaccine) {
        List<VaccineResponse> vaccineResponseList = vaccineService.getAnimalsVaccine(vaccine.getCode(), vaccine.getAnimal().getId(), vaccine.getProtectionStartDate());
        if (vaccineResponseList.isEmpty()) {
            return vaccineService.create(vaccine);
        } else {
            throw new RuntimeException();
        }
    }

    //Hayvan id’sine göre belirli bir hayvana ait tüm aşı kayıtlarını listelemek için gerekli API end point'ini oluşturmak.
    @GetMapping("/animalIdList")
    @ResponseStatus(HttpStatus.OK)
    public List<VaccineResponse> getVaccineListAnimalId(@RequestParam(name = "animal_id", required = false) Long animalId) {
        List<VaccineResponse> vaccineResponseList = vaccineService.getVaccineListAnimalId(animalId);
        return vaccineResponseList;
    }


    //Kullanıcının aşı koruyuculuk bitiş tarihi yaklaşan hayvanları listeleyebilmesi için kullanıcının gireceği başlangıç ve bitiş tarihlerine göre
    // aşı koruyuculuk tarihi bu aralıkta olan hayvanların listesini geri döndüren API end  point'ini oluşturmak.
    @GetMapping("/finishandstartdate")
    @ResponseStatus(HttpStatus.OK)
    public List<VaccineResponse> getVaccineFinishAndStartdate(@RequestParam(name = "protection_start_date", required = false) LocalDate StartDate,
                                                              @RequestParam(name = "protection_finish_date", required = false) LocalDate FinishDate) {
        List<VaccineResponse> vaccineResponseList = vaccineService.getFinishStartNewVaccine(StartDate, FinishDate);
        return vaccineResponseList;
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse update(@PathVariable Long id, @RequestBody VaccineRequest request) {
        return vaccineService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        vaccineService.deleteById(id);
    }

}



