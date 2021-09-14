package com.allianz.coreader.dto;

import java.util.List;

public class SensorResponseDto {
    private List<String> concentration;
    
    private String sensorCode;


    public List<String> getConcentration() {
        return concentration;
    }

    public void setConcentration(List<String> concentration) {
        this.concentration = concentration;
    }

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }
}
