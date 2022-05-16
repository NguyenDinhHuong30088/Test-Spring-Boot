package com.example.testspringboot.repository;

import com.example.testspringboot.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Integer> {
    @Query(value = "SELECT * FROM products p WHERE p.name like %: name% and p.district like %: district%",
            nativeQuery = true)
    List<Route> search(@Param("name") String name, @Param("district") String district);

    List<Route> findAllByNameContainsAndContainsDistrict(String name, Integer district);

    @Query(value = "select * from products p WHERE p.name LIKE %:name% and p.price <= :price", nativeQuery = true)
    List<Object[]> search(@Param("name") String name, @Param("price") Integer district);
}
