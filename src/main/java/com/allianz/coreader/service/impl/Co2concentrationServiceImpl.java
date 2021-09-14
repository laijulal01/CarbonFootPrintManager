package com.allianz.coreader.service.impl;

import com.allianz.coreader.dto.Co2ConcentrationDto;
import com.allianz.coreader.entity.Co2Concentration;
import com.allianz.coreader.entity.District;
import com.allianz.coreader.entity.Sensor;
import com.allianz.coreader.exception.AlreadyExistsException;
import com.allianz.coreader.repository.Co2ConcentrationRepository;
import com.allianz.coreader.repository.DistrictRepository;
import com.allianz.coreader.repository.SensorRepository;
import com.allianz.coreader.service.Co2concentrationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Co2concentrationServiceImpl implements Co2concentrationService {

    @Autowired
    Co2ConcentrationRepository co2ConcentrationRepository;
    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Co2ConcentrationDto createCo2Concentration(Co2ConcentrationDto co2ConcentrationDto) {
        Optional<Sensor> sensor = sensorRepository.findBySensorCode(co2ConcentrationDto.getSensorCode());
        if (sensor.isPresent()) {
            Co2Concentration co2Concentration = new Co2Concentration();
            co2Concentration.setSensor(sensor.get());
            co2Concentration.setConcentration(co2ConcentrationDto.getConcentration());
            co2Concentration.setTimestamp(co2ConcentrationDto.getTimeStamp());
            co2ConcentrationRepository.save(co2Concentration);
            return convertEntityToDto(co2Concentration);
        } else {
            throw new AlreadyExistsException("Data provided is invalid");
        }

    }


    @Override
    public Co2ConcentrationDto updateCo2Concentration(String sensorId, Co2ConcentrationDto co2ConcentrationDto) {
        Optional<Sensor> sensor = sensorRepository.findBySensorCode(sensorId);
        if (sensor.isPresent()) {
            Co2Concentration co2Concentration = co2ConcentrationRepository.findBySensor(sensor.get());
            co2Concentration.setConcentration(co2ConcentrationDto.getConcentration());
            return convertEntityToDto(co2ConcentrationRepository.save(co2Concentration));
        } else {
            throw new AlreadyExistsException("Sensor Provided is not present in the system");
        }
    }
    private Co2ConcentrationDto convertEntityToDto(Co2Concentration co2Concentration){
        Co2ConcentrationDto co2ConcentrationDto=new Co2ConcentrationDto();
        co2ConcentrationDto.setTimeStamp(co2Concentration.getTimestamp());
        co2ConcentrationDto.setSensorCode(co2Concentration.getSensor().getSensorCode());
        co2ConcentrationDto.setConcentration(co2Concentration.getConcentration());
        return co2ConcentrationDto;
    }

}
