package com.my.pharmacy.service;

import com.my.pharmacy.controller.dto.DrugResponseDto;
import com.my.pharmacy.infrastructure.entity.DrugEntity;
import com.my.pharmacy.repository.DrugRepository;
import com.my.pharmacy.service.valueObjects.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {
    private final DrugRepository drugRepository;

    @Autowired
    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public List<DrugEntity> getAll() {
        return drugRepository.findAll();
    }

    public DrugEntity getOne (long id) {
        return drugRepository.findById(id).orElseThrow(() -> new RuntimeException("Drug not found"));

    }

    public DrugResponseDto create(DrugEntity drug) {
        var price = Price.create((float) drug.getPrice());


        var drugEntity = new DrugEntity();
        drugEntity.setCode(drug.getCode());
        drugEntity.setName(drug.getName());
        drugEntity.setManufacturer(drug.getManufacturer());
        drugEntity.setAvailableUnits(drug.getAvailableUnits());
        drugEntity.setDose(drug.getDose());
        drugEntity.setForm(drug.getForm());
        drugEntity.setPrice(price.getValue());
        drugEntity.setSymptom(drug.getSymptom());


        drugRepository.save(drugEntity);

        return new DrugResponseDto(
                drugEntity.getId(),
                drugEntity.getCode(),
                drugEntity.getName(),
                drugEntity.getManufacturer(),
                drugEntity.getAvailableUnits(),
                drugEntity.getDose(),
                drugEntity.getForm(),
                drugEntity.getPrice(),
                drugEntity.getSymptom(),
                drug.getPrescriptionRequired(), drug.getStorageRequirements());
    }


    public void delete(long id) {
        if(!drugRepository.existsById(id)){
            throw new RuntimeException();
        }
        drugRepository.deleteById(id);
    }
}