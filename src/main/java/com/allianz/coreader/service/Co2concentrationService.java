package com.allianz.coreader.service;


import com.allianz.coreader.dto.Co2ConcentrationDto;
import com.allianz.coreader.entity.Co2Concentration;

public interface Co2concentrationService {

    Co2ConcentrationDto createCo2Concentration(Co2ConcentrationDto co2ConcentrationDto);

    Co2ConcentrationDto updateCo2Concentration(String sensorId, Co2ConcentrationDto co2ConcentrationDto);
}
