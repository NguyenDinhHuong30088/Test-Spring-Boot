package com.example.testspringboot.controller;

import com.example.testspringboot.entity.Route;
import com.example.testspringboot.repository.RouteRepository;
import com.example.testspringboot.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/routes")
public class RouteApi {

    @Autowired
    private RouteService routeService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Route> getList(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "1") int limit) {
        return routeService.findAll(page, limit);
    }
    @RequestMapping(method = RequestMethod.GET, path = "/get-all")
    public List<Route> getList() {
        return routeService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Route save(@RequestBody Route route) {
        routeService.save(route);
        return route;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Route getDetail(@PathVariable int id) {
        return routeService.findById(id).get();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public boolean delete(@PathVariable int id) {
        routeService.deleteById(id);
        return true;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public Route update(@PathVariable int id, @RequestBody Route updateProduct) {
        Route existing = routeService.findById(id).get();
        existing.setName(updateProduct.getName());
        existing.setDescription(updateProduct.getDescription());
        existing.setDistrict(updateProduct.getDistrict());
        routeService.save(existing);
        return updateProduct;
    }
}
