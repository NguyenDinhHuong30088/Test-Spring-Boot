package com.example.testspringboot.seed;

import com.example.testspringboot.entity.District;
import com.example.testspringboot.entity.Street;
import com.example.testspringboot.entity.enums.StreetStatus;
import com.example.testspringboot.service.DistrictService;
import com.example.testspringboot.service.StreetService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    private final Faker faker = new Faker();
    @Autowired
    private DistrictService districtService;
    @Autowired
    private StreetService streetService;

    @Override
    public void run(String... args) throws Exception {
        if (districtService.findAll().isEmpty()) {
            List<District> districts = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                District district = new District();
                district.setName(faker.pokemon().name());
                districts.add(district);
            }
            districtService.saveAll(districts);
        }

        if (streetService.findAll("", 0).isEmpty()) {
            List<Street> streets = new ArrayList<>();
            List<District> districts = districtService.findAll();
            for (int i = 0; i < 10; i++) {
                Street street = new Street();
                street.setName(faker.pokemon().name());
                street.setDistrictId(districts.get(faker.number().numberBetween(0, districts.size() - 1)).getId());
                street.setStatus(StreetStatus.USING);
                streets.add(street);
            }
            streetService.saveAll(streets);
        }

    }
}