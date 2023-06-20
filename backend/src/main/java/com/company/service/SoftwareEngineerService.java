package com.company.service;

import com.company.config.PrivateKeyEncryption;
import com.company.dto.SoftwareEngineerDTO;
import com.company.model.SoftwareEngineer;
import com.company.model.enums.CompanyRole;
import com.company.repository.SoftwareEngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SoftwareEngineerService {
    @Autowired
    private SoftwareEngineerRepository softwareEngineerRepository;
    @Autowired
    private PrivateKeyEncryption privateKeyEncryption;
    public int findByUsername(String username) throws UsernameNotFoundException {
        SoftwareEngineer softwareEngineer = softwareEngineerRepository.findByUsername(privateKeyEncryption.encryptToString(username));
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

    public List<SoftwareEngineerDTO> getAll() {
        List<SoftwareEngineer> se = this.softwareEngineerRepository.findAll();
        List<SoftwareEngineerDTO> ret = new ArrayList<>();

        for(SoftwareEngineer s: se){
            SoftwareEngineerDTO seDTO = new SoftwareEngineerDTO(s.getId(), s.getName(), s.getSurname());
            ret.add(seDTO);
        }
        return ret;
    }

    public List<SoftwareEngineerDTO> findByIds(List<Integer> usersIds) {

        List<SoftwareEngineer> temp = this.softwareEngineerRepository.findAllById(usersIds);

        List<SoftwareEngineerDTO> ret = new ArrayList<>();

        for(SoftwareEngineer se : temp){
            SoftwareEngineerDTO seDTO = new SoftwareEngineerDTO(se.getName(), se.getSurname(), se.getPhoneNumber());
            ret.add(seDTO);
        }

        return ret;
    }
}
