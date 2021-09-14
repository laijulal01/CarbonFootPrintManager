package com.allianz.coreader.service.impl;

import com.allianz.coreader.dto.DistrictDto;
import com.allianz.coreader.dto.DistrictResponseDto;
import com.allianz.coreader.dto.SensorResponseDto;
import com.allianz.coreader.entity.City;
import com.allianz.coreader.entity.District;
import com.allianz.coreader.exception.AlreadyExistsException;
import com.allianz.coreader.repository.CityRepository;
import com.allianz.coreader.repository.DistrictRepository;
import com.allianz.coreader.service.DistrictService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public DistrictDto createDistrict(DistrictDto districtDto) {
        Optional<District> byAreaCode = districtRepository.findByAreaCode(districtDto.getAreaCode());
        if (byAreaCode.isPresent()) {
            throw new AlreadyExistsException("District is already present in the system");
        }
        // District district = modelMapper.map(districtDto, District.class);
        Optional<City> byCode = cityRepository.findByCityCode(districtDto.getCityId());
        if (byCode.isPresent()) {
            District district = new District();
            district.setName(districtDto.getName());
            district.setCity(byCode.get());
            district.setAreaCode(districtDto.getAreaCode());
            return convertEntityToDto(districtRepository.save(district));
        } else {
            throw new AlreadyExistsException("The given city is not present in the system");
        }

    }

    @Override
    public void deleteDistrict(Long id) {
        districtRepository.deleteById(id);
    }

    @Override
    public DistrictResponseDto getDistrict(Long id) {
        Optional<District> district = districtRepository.findById(id);
        if (district.isPresent()) {
            List<SensorResponseDto> sensorResponseDtoList = new ArrayList<>();
            DistrictResponseDto districtResponseDto = new DistrictResponseDto();
            districtResponseDto.setName(district.get().getName());
            district.get().getSensors().forEach(sensor -> {
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
            return districtResponseDto;
        } else {
            throw new AlreadyExistsException("No such district is present in the system");
        }
    }

    @Override
    public List<DistrictDto> getAllDistricts() {
        List<District> districts = districtRepository.findAll();
        List<DistrictDto> districtDtoList = new ArrayList<>();
        districts.forEach(district -> {
            districtDtoList.add(convertEntityToDto(district));
        });
        return districtDtoList;
    }

    @Override
    public DistrictDto updateDistrict(Long id, DistrictDto districtDto) {
        
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if(optionalDistrict.isPresent()){
            District district = optionalDistrict.get();
            if(districtDto.getName()!=null){
                district.setName(districtDto.getName());
            }
            if(districtDto.getAreaCode()!=null){
                district.setAreaCode(districtDto.getAreaCode());
            }
            return convertEntityToDto(districtRepository.save(district));
        }
        else{
            throw new RuntimeException("No District is present");
        }
    }

    private DistrictDto convertEntityToDto(District district) {
        DistrictDto districtDto = new DistrictDto();
        districtDto.setId(district.getId());
        districtDto.setId(district.getId());
        districtDto.setAreaCode(district.getAreaCode());
        districtDto.setCityId(district.getCity().getCityCode());
        districtDto.setName(district.getName());
        return districtDto;
    }

    private District convertDtoToEntity(DistrictDto districtDto) {
        District district = new District();
        if (districtDto.getAreaCode() != null) {
            district.setAreaCode(districtDto.getAreaCode());
        }
        if (districtDto.getName()!= null) {
            district.setName(districtDto.getName());
        }
        return district;
    }
}
