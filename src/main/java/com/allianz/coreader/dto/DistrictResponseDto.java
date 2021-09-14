package com.allianz.coreader.dto;

import java.util.List;

public class DistrictResponseDto {
    
    private String name;
    
    private List<SensorResponseDto> sensors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SensorResponseDto> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorResponseDto> sensors) {
        this.sensors = sensors;
    }
}
