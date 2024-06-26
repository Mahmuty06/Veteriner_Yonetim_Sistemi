package patika.dev.veterineryonetimsistemi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import patika.dev.veterineryonetimsistemi.dto.request.DoctorRequest;
import patika.dev.veterineryonetimsistemi.dto.response.DoctorResponse;
import patika.dev.veterineryonetimsistemi.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorResponse> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse getById(@PathVariable("id") Long id) {
        return doctorService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorResponse save(@RequestBody DoctorRequest doctor) {
        return doctorService.create(doctor);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse update(@PathVariable Long id, @RequestBody DoctorRequest request) {
        return doctorService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        doctorService.deleteById(id);
    }

}


