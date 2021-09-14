package com.allianz.coreader.service.impl;

import com.allianz.coreader.dto.SensorDto;
import com.allianz.coreader.entity.District;
import com.allianz.coreader.entity.Sensor;
import com.allianz.coreader.exception.AlreadyExistsException;
import com.allianz.coreader.repository.DistrictRepository;
import com.allianz.coreader.repository.SensorRepository;
import com.allianz.coreader.service.SensorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SensorDto createSensor(SensorDto sensorDto) {
        Optional<Sensor> optionalSensor = sensorRepository.findBySensorCode(sensorDto.getSensorCode());
        if (optionalSensor.isPresent()) {
            throw new AlreadyExistsException("Sensor model is already present in the system");
        }
        Optional<District> district = districtRepository.findByAreaCode(sensorDto.getDistrictCode());
        if (!district.isPresent()) {
            throw new AlreadyExistsException("District is not present in the system");
        }
        Sensor sensor = new Sensor();
        sensor.setSensorCode(sensorDto.getSensorCode());
        sensor.setName(sensorDto.getName());
        sensor.setType(sensorDto.getType());
        sensor.setDistrict(district.get());
        sensorRepository.save(sensor);
        sensorDto = modelMapper.map(sensor, SensorDto.class);
        return sensorDto;
    }


    @Override
    public void deleteSensor(Long id) {
        sensorRepository.deleteById(id);
    }

    @Override
    public SensorDto getSensor(Long id) {
        Sensor sensor = sensorRepository.findById(id).get();
        return modelMapper.map(sensor, SensorDto.class);
    }

    @Override
    public List<SensorDto> getAllSensors() {

        List<Sensor> sensors = sensorRepository.findAll();
        List<SensorDto> sensorDtos = new ArrayList<>();
        sensors.forEach(sensor -> {
            sensorDtos.add(convertEntityToDto(sensor)); 
        });
        return sensorDtos;
    }

    @Override
    public SensorDto updateSensor(Long id, SensorDto sensorDto) {
        Optional<Sensor> optionalSensor = sensorRepository.findById(id);
        if (optionalSensor.isPresent()) {
            Sensor sensor = optionalSensor.get();
            if(sensorDto.getName()!=null){
                sensor.setName(sensorDto.getName());
            }
            if(sensorDto.getType()!=null){
                sensor.setType(sensorDto.getType());
            }
            return convertEntityToDto(sensorRepository.save(sensor));
        } else {
            throw new RuntimeException("No sensor is present");
        }
    }

    private SensorDto convertEntityToDto(Sensor sensor) {
        SensorDto sensorDto = new SensorDto();
        sensorDto.setId(sensor.getId());
        sensorDto.setName(sensor.getName());
        sensorDto.setSensorCode(sensor.getSensorCode());
        sensorDto.setType(sensor.getType());
        sensorDto.setDistrictCode(sensor.getDistrict().getAreaCode());
        return sensorDto;
    }

    private Sensor convertDtoToEntity(SensorDto sensorDto) {
        Sensor sensor = new Sensor();
        if (sensorDto.getName() != null) {
            sensor.setName(sensorDto.getName());
        }
        if (sensorDto.getSensorCode() != null) {
            sensor.setSensorCode(sensorDto.getName());
        }
        if (sensorDto.getType() != null) {
            sensor.setType(sensorDto.getType());
        }
        return sensor;
    }
}
