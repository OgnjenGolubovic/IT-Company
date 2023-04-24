package com.company.repository;

import com.company.model.HumanResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanResourcesRepository extends JpaRepository<HumanResources, Integer> {
    HumanResources findByUsername(String username);
}
