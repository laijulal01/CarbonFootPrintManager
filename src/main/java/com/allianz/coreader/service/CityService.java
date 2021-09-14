package com.allianz.coreader.service;

import com.allianz.coreader.dto.CityDto;
import com.allianz.coreader.dto.CityResponseDto;
import com.allianz.coreader.dto.DistrictDto;
import com.allianz.coreader.entity.City;
import com.allianz.coreader.entity.District;

import java.util.List;

public interface CityService {

    public CityDto createCity(CityDto cityDto);
    public void deleteCity(Long id);
    public CityResponseDto getCity(Long id);
    public List<CityDto> getAllCities();
    public CityDto updateCity(Long id,CityDto sensorDto);
}
