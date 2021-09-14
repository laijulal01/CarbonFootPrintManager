package com.allianz.coreader.service;

import com.allianz.coreader.dto.DistrictDto;
import com.allianz.coreader.dto.DistrictResponseDto;
import com.allianz.coreader.entity.District;

import java.util.List;
import java.util.Optional;

public interface DistrictService {

    public DistrictDto createDistrict(DistrictDto districtDto);
    public void deleteDistrict(Long id);
    public DistrictResponseDto getDistrict(Long id);
    public List<DistrictDto> getAllDistricts();
    public DistrictDto updateDistrict(Long id, DistrictDto sensorDto);
}
