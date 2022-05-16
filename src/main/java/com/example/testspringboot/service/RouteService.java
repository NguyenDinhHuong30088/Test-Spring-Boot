package com.example.testspringboot.service;

import com.example.testspringboot.entity.Route;
import com.example.testspringboot.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public Page<Route> findAll(int page, int limit) {
        return routeRepository.findAll(
                PageRequest.of(page - 1, limit));
    }

    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    public List<Route> findByNameAndDistrict(String name, Integer district) {
        return routeRepository.findAllByNameContainsAndContainsDistrict(name, district);
    }

    public Optional<Route> findById(Integer id) {
        return routeRepository.findById(id);
    }

    public Route save(Route route) {
        return routeRepository.save(route);
    }

    public void deleteById(Integer id) {
        routeRepository.deleteById(id);
    }
}



