package com.my.pharmacy.controller;


import com.my.pharmacy.controller.dto.DrugResponseDto;
import com.my.pharmacy.infrastructure.entity.DrugEntity;
import com.my.pharmacy.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
@PreAuthorize("isAuthenticated()")
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
    public DrugResponseDto getOne(@PathVariable long id) {
        var drug = drugService.getOne(id);
        return new DrugResponseDto(drug.getId(), drug.getCode(), drug.getName(), drug.getManufacturer(), drug.getAvailableUnits(), drug.getDose(), drug.getForm(), drug.getPrice(), drug.getSymptom(), drug.getPrescriptionRequired(), drug.getStorageRequirements());
    }

    @PostMapping
    public DrugResponseDto create(@Validated @RequestBody DrugEntity drug) {
        return drugService.create(drug);
    }

}