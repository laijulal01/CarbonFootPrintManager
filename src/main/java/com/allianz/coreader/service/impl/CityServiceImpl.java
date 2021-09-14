package com.allianz.coreader.service.impl;

import com.allianz.coreader.dto.*;
import com.allianz.coreader.entity.City;
import com.allianz.coreader.entity.Co2Concentration;
import com.allianz.coreader.exception.AlreadyExistsException;
import com.allianz.coreader.repository.CityRepository;
import com.allianz.coreader.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CityDto createCity(CityDto cityDto) {
        Optional<City> optionalCity = cityRepository.findByCityCode(cityDto.getCityCode());
        if (optionalCity.isPresent()) {
            throw new AlreadyExistsException("City is already present in the system");
        }
        City city = convertDtoToEntity(cityDto);
        return convertEntityToDto(cityRepository.save(city));
    }

    @Override
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public CityResponseDto getCity(Long id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            CityResponseDto cityResponseDto = new CityResponseDto();
            cityResponseDto.setName(city.getName());
            List<DistrictResponseDto> districtResponseDtoList = new ArrayList<>();
            city.getDistricts().forEach(district -> {
                List<SensorResponseDto> sensorResponseDtoList = new ArrayList<>();
                DistrictResponseDto districtResponseDto = new DistrictResponseDto();
                districtResponseDto.setName(district.getName());
                district.getSensors().forEach(sensor -> {
                    SensorResponseDto sensorResponseDto = new SensorResponseDto();
                    List<String> concentrations = new ArrayList<>();
                    sensor.getCo2Concentrations().forEach(co2Concentration -> {
                        concentrations.add(co2Concentration.getConcentration());
                    });
                    sensorResponseDto.setConcentration(concentrations);
                    sensorResponseDto.setSensorCode(sensor.getSensorCode());
                    sensorResponseDtoList.add(sensorResponseDto);
                });
                districtResponseDto.setSensors(sensorResponseDtoList);
                districtResponseDtoList.add(districtResponseDto);
            });
            cityResponseDto.setDistricts(districtResponseDtoList);
            return cityResponseDto;
        } else {
            throw new AlreadyExistsException("No Such city Exists");
        }
    }

    @Override
    public List<CityDto> getAllCities() {

        List<City> cities = cityRepository.findAll();
        List<CityDto> cityDtos = new ArrayList<>();
        cities.forEach(city -> {
            cityDtos.add(convertEntityToDto(city));
        });
        return cityDtos;
    }

    @Override
    public CityDto updateCity(Long id, CityDto cityDto) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            if (cityDto.getName() != null) {
                city.setName(cityDto.getName());
            }
            if (cityDto.getStateName() != null) {
                city.setStateName(cityDto.getStateName());
            }
            return convertEntityToDto(cityRepository.save(city));
        } else {
            throw new AlreadyExistsException("No City is found");
        }
    }

    private CityDto convertEntityToDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setCityCode(city.getCityCode());
        cityDto.setName(city.getName());
        cityDto.setStateName(city.getStateName());
        return cityDto;
    }

    private City convertDtoToEntity(CityDto cityDto) {
        City city = new City();
        if (cityDto.getId() != null) {
            city.setId(cityDto.getId());
        }
        if (cityDto.getCityCode() != null) {
            city.setCityCode(cityDto.getCityCode());
        }
        if (cityDto.getName() != null) {
            city.setName(cityDto.getName());
        }
        if (cityDto.getStateName() != null) {
            city.setStateName(cityDto.getStateName());
        }
        return city;
    }
}
