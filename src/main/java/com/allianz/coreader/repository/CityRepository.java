package com.allianz.coreader.repository;

import com.allianz.coreader.entity.City;
import com.allianz.coreader.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    Optional<City> findByCityCode(String Code);
}
