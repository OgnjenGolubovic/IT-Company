package com.company.service;

import com.company.dto.SoftwareEngineerDTO;
import com.company.model.SoftwareEngineer;
import com.company.repository.SoftwareEngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SoftwareEngineerService {
    @Autowired
    private SoftwareEngineerRepository softwareEngineerRepository;
    public int findByUsername(String username) throws UsernameNotFoundException {
        SoftwareEngineer softwareEngineer = softwareEngineerRepository.findByUsername(username);
        if(softwareEngineer != null){
            return softwareEngineer.getId();
        }
        return 0;
    }

    public void updateProfile(SoftwareEngineerDTO softwareEngineerDTO) {

    SoftwareEngineer se = softwareEngineerRepository.findByUsername(softwareEngineerDTO.getEmail());

    se.setName(softwareEngineerDTO.getName());
    se.setSurname(softwareEngineerDTO.getSurname());
    se.setState(softwareEngineerDTO.getState());
    se.setCity(softwareEngineerDTO.getCity());
    se.setStreet(softwareEngineerDTO.getStreet());
    se.setStreetNumber(softwareEngineerDTO.getStreetNumber());
    se.setPhoneNumber(softwareEngineerDTO.getPhone());
    se.setSkills(softwareEngineerDTO.getSkills());

    softwareEngineerRepository.save(se);

    }

    public SoftwareEngineer findById(Integer id) {

        return this.softwareEngineerRepository.findById(id).orElse(null);
    }
}
