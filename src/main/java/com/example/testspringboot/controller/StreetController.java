package com.example.testspringboot.controller;

import com.example.testspringboot.entity.Street;
import com.example.testspringboot.entity.enums.StreetStatus;
import com.example.testspringboot.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/streets")
public class StreetController {
    private final StreetService streetService;

    public StreetController(StreetService streetService) {
        this.streetService = streetService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Street>> findAll(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0") Integer districtId) {
        return ResponseEntity.ok(streetService.findAll(keyword, districtId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Street> save(@RequestBody Street street) {
        street.setStatus(StreetStatus.USING);
        return ResponseEntity.status(HttpStatus.CREATED).body(streetService.save(street));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Street> update(@PathVariable Integer id, @RequestBody Street street) {
        Optional<Street> optionalProduct = streetService.findById(id);

        if (!optionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Street existStreet = optionalProduct.get();


        if (street.getStatus() == null) {
            street.setStatus(StreetStatus.USING);
        }

        existStreet.setName(street.getName());
        existStreet.setDescription(street.getDescription());
        existStreet.setStatus(street.getStatus());
        existStreet.setDistrictId(street.getDistrictId());

        return ResponseEntity.ok(streetService.save(existStreet));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Street> findById(@PathVariable Integer id) {
        Optional<Street> optionalProduct = streetService.findById(id);

        if (!optionalProduct.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(optionalProduct.get());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        Optional<Street> optionalProduct = streetService.findById(id);

        if (!optionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }

        Street existStreet = optionalProduct.get();
        existStreet.setStatus(StreetStatus.REPAIR);
        streetService.save(existStreet);

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
