package com.company.repository;

import com.company.model.SoftwareEngineerProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareEngineerProjectRepository extends JpaRepository<SoftwareEngineerProject, Integer> {


}
