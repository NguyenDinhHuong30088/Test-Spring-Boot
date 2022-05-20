package com.example.testspringboot.repository;

import com.example.testspringboot.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends JpaRepository<Street, Integer>, JpaSpecificationExecutor<Street> {
}
