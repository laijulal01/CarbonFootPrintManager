package com.allianz.coreader.dto;

import com.allianz.coreader.entity.District;

import java.util.List;

public class CityResponseDto {
    private String name;
    
    private List<DistrictResponseDto>  districts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DistrictResponseDto> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictResponseDto> districts) {
        this.districts = districts;
    }
}
