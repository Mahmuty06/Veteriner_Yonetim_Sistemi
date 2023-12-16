package patika.dev.veterineryonetimsistemi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import patika.dev.veterineryonetimsistemi.dto.request.AvailableDateRequest;
import patika.dev.veterineryonetimsistemi.dto.response.AvailableDateResponse;
import patika.dev.veterineryonetimsistemi.service.AvailableDateService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/availableDate")
public class AvailableDateController {

    private final AvailableDateService availableDateService;

    @Autowired
    public AvailableDateController(AvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDateResponse> findAll() {
        return availableDateService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse getById(@PathVariable("id") Long id) {
        return availableDateService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AvailableDateResponse save(@RequestBody AvailableDateRequest availableDateRequest) {
        return availableDateService.create(availableDateRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse update(@PathVariable Long id, @RequestBody AvailableDateRequest availableDateRequest) {
        return availableDateService.update(id, availableDateRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        availableDateService.deleteById(id);
    }

}



