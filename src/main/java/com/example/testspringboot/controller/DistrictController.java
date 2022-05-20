package com.example.testspringboot.controller;

import com.example.testspringboot.entity.District;
import com.example.testspringboot.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/districts")
public class DistrictController {

    final
    DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<District>> findAll() {
        return ResponseEntity.ok(districtService.findAll());
    }
}
