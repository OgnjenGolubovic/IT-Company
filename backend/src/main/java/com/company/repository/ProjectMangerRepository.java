package com.company.repository;

import com.company.model.ProjectManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMangerRepository extends JpaRepository<ProjectManager, Integer> {
    ProjectManager findByUsername(String username);
}
