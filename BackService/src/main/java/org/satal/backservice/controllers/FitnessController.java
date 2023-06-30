package org.satal.backservice.controllers;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.MaintenanceDto;
import org.satal.backservice.dto.users.SpecializationDto;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.services.MaintenanceService;
import org.satal.backservice.services.SpecializationService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fit")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FitnessController {
    private final MaintenanceService maintenanceService;

    private final SpecializationService specializationService;

    @PostMapping("/maintenance")
    public List<MaintenanceDto> getAllMaintenance(){
        return maintenanceService.findAll().stream().map(MaintenanceDto::new).toList();
    }

    @PostMapping("/spec_list")
    public Page<SpecializationDto> getAllSpecializations(@RequestParam(required = false, defaultValue = "1") Integer page){
        if(page < 1){
            page = 1;
        }
//        return specializationService.findAll(page).map(SpecializationDto::new);
        Page<Specialization> specializationPage = specializationService.findAll(page);
        System.out.println(">>>>>>>>>>>>");
        System.out.println(specializationPage.getTotalElements());
        for (Specialization specialization : specializationPage) {
            System.out.println(specialization.getSpecializationTitle());
        }
        return specializationPage.map(SpecializationDto::new);
    }

    @PostMapping("/spec_one")
    public SpecializationDto getProductById(@RequestParam Long id){
        Optional<Specialization> specializationOptional = specializationService.findById(id);
        return specializationOptional.map(SpecializationDto::new).orElseGet(() -> new SpecializationDto(null, "Специализация с id:" + id + " не найдена."));
    }

}
