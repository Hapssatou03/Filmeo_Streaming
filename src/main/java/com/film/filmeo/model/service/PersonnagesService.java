package com.film.filmeo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.film.filmeo.model.entity.Personnages;
import com.film.filmeo.model.repository.PersonnageRepository;

import org.springframework.stereotype.Service;

@Service
public class PersonnagesService {

    @Autowired
    private PersonnageRepository personnagesRepository;

    public List<Personnages> getAll() {
        return personnagesRepository.findAll();
    }

    public Personnages getOne(Long id) {
        return personnagesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personnage introuvable avec id " + id));
    }

    public Personnages insert(Personnages personnages) {
        return personnagesRepository.save(personnages);
    }

    public Personnages update(Long id, Personnages personnagesDetails) {
        Personnages personnages = getOne(id);
        personnages.setNom(personnagesDetails.getNom());
        personnages.setPrenom(personnagesDetails.getPrenom());
        personnages.setDateNaissance(personnagesDetails.getDateNaissance());
        personnages.setNationalite(personnagesDetails.getNationalite());
        personnages.setBiographie(personnagesDetails.getBiographie());
        personnages.setPhotoUrl(personnagesDetails.getPhotoUrl());
        personnages.setSexe(personnagesDetails.getSexe());
        personnages.setMetier(personnagesDetails.getMetier());
        return personnagesRepository.save(personnages);
    }


    public void delete(Long id) {
        Personnages personnages = getOne(id);
        personnagesRepository.delete(personnages);
    }
}
