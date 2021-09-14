package com.allianz.coreader.service;

import com.allianz.coreader.dto.SensorDto;
import com.allianz.coreader.entity.Sensor;

import java.util.List;

public interface SensorService {
    public SensorDto createSensor(SensorDto sensorDto);
    public void deleteSensor(Long id);
    public SensorDto getSensor(Long id);
    public List<SensorDto> getAllSensors();
    public SensorDto updateSensor(Long id,SensorDto sensorDto);
}
