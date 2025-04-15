package com.my.pharmacy.service;

import com.my.pharmacy.controller.dto.DrugDto;
import org.example.pharmacy.infrastructure.entity.DrugEntity;
import com.my.pharmacy.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.example.pharmacy.service.model.DrugModel;

@Service
public class DrugService {
    private final DrugRepository drugRepository;

    @Autowired
    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public DrugDto getDrug(Long id) {
        var drugEntity = drugRepository.findById(id).orElseThrow(() -> new RuntimeException("Drug not found"));
        return new DrugDto(
                drugEntity.getId(),
                drugEntity.getCode(),
                drugEntity.getName(),
                drugEntity.getManufacturer(),
                drugEntity.getAvailableUnits(),
                drugEntity.getDose(),
                drugEntity.getForm(),
                drugEntity.getPrice(),
                drugEntity.getSymptom()
        );
    }

    public DrugEntity getOne(long id) {
        return drugRepository.findById(id).orElseThrow(() -> new RuntimeException("Drug not found"));
    }

    public DrugDto create(DrugEntity drug) {
        var price = org.my.pharmacy.service.valueObjects.Price.create((float) drug.getPrice());
        var drugModel = new DrugModel(null, drug.getName(), price, drug.getDescription());

        var drugEntity = new DrugEntity();
        drugEntity.setCode(drug.getCode());
        drugEntity.setName(drug.getName());
        drugEntity.setManufacturer(drug.getManufacturer());
        drugEntity.setAvailableUnits(drug.getAvailableUnits());
        drugEntity.setDose(drug.getDose());
        drugEntity.setForm(drug.getForm());
        drugEntity.setPrice(drug.getPrice());
        drugEntity.setSymptom(drug.getSymptom());

        drugRepository.save(drugEntity);

        return new DrugDto(
                drugEntity.getId(),
                drugEntity.getCode(),
                drugEntity.getName(),
                drugEntity.getManufacturer(),
                drugEntity.getAvailableUnits(),
                drugEntity.getDose(),
                drugEntity.getForm(),
                drugEntity.getPrice(),
                drugEntity.getSymptom()
        );
    }

    public List<DrugEntity> getAll() {
        return drugRepository.findAll();
    }

    public void delete(long id) {
        if (!drugRepository.existsById(id)) {
            throw new RuntimeException("Drug not found");
        }
        drugRepository.deleteById(id);
    }
}
