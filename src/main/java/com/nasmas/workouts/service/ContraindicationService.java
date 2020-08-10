package com.nasmas.workouts.service;

import com.nasmas.workouts.model.Contraindication;
import com.nasmas.workouts.repository.ContraindicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContraindicationService {

    @Autowired
    private ContraindicationRepository contraindicationRepository;

    public Contraindication getContraindicationByUuid(UUID uuid) {
        return contraindicationRepository.findByUuid(uuid);
    }

    public List<Contraindication> getContraindicationList() {
        return contraindicationRepository.findAll();
    }
}
