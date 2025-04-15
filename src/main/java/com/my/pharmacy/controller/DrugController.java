package org.example.pharmacy.controller;


import com.my.pharmacy.controller.dto.DrugDto;
import com.my.pharmacy.service.DrugService;
import org.example.pharmacy.infrastructure.entity.DrugEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
public class DrugController {
    private final DrugService drugService;

    @Autowired
    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping
    public List<DrugEntity> getALlDrugs() {
        return drugService.getAll();
    }

    @GetMapping("/{id}")
    public DrugDto getOne(@PathVariable long id) {
        var drug = drugService.getOne(id);
        return new DrugDto(drug.getId(), drug.getCode(), drug.getName(), drug.getManufacturer(), drug.getAvailableUnits(), drug.getDose(), drug.getForm(), drug.getPrice(), drug.getSymptom());
    }




    @PostMapping
    public DrugDto create(@Validated @RequestBody DrugEntity drug) {
        var drugEntity = new DrugEntity();
        drugEntity.setCode(drug.getCode());
        drugEntity.setName(drug.getName());
        drugEntity.setManufacturer(drug.getManufacturer());
        drugEntity.setAvailableUnits(drug.getAvailableUnits());
        drugEntity.setDose(drug.getDose());
        drugEntity.setForm(drug.getForm());
        drugEntity.setPrice(drug.getPrice());
        drugEntity.setSymptom(drug.getSymptom());

        var createdDrug = drugService.create(drugEntity);
        return createdDrug;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        drugService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


