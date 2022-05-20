package com.example.testspringboot.service;

import com.example.testspringboot.entity.SearchCriteria;
import com.example.testspringboot.entity.Street;
import com.example.testspringboot.repository.StreetRepository;
import com.example.testspringboot.specifications.StreetSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreetService {

    private final StreetRepository streetRepository;

    public StreetService(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }

    public List<Street> findAll(String keyword, Integer districtId) {
        Specification<Street> specifications = Specification.where(null);

        if (keyword != null && keyword.length() > 0) {
            specifications = new StreetSpecifications(new SearchCriteria("name", ":", keyword));
        }
        if (districtId > 0) {
            specifications = new StreetSpecifications(new SearchCriteria("districtId", ":", districtId));
        }

        return streetRepository.findAll(specifications);
    }

    public Optional<Street> findById(Integer id) {
        return streetRepository.findById(id);
    }

    public Street save(Street street) {
        return streetRepository.save(street);
    }

    public List<Street> saveAll(List<Street> streets) {
        return streetRepository.saveAll(streets);
    }

    public void deleteById(Integer id) {
        streetRepository.deleteById(id);
    }
}



