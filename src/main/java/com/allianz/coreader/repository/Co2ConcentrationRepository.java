package com.allianz.coreader.repository;

import com.allianz.coreader.entity.Co2Concentration;
import com.allianz.coreader.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Co2ConcentrationRepository extends JpaRepository<Co2Concentration,Long> {

    Co2Concentration findBySensor(Sensor sensorCode);
}
