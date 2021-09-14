package com.allianz.coreader.repository;

import com.allianz.coreader.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Long> {
   Optional<Sensor> findBySensorCode(String Code);
}
