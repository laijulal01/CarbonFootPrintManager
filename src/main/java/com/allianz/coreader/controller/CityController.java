package com.allianz.coreader.controller;


import com.allianz.coreader.dto.CityDto;
import com.allianz.coreader.dto.CityResponseDto;
import com.allianz.coreader.dto.ResponseDTO;
import com.allianz.coreader.entity.City;
import com.allianz.coreader.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.allianz.coreader.util.Constants.SUCCESS;

@RestController
@RequestMapping("api/v1")
public class CityController {

    @Autowired
    CityService cityService;

    @PostMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createCity(@Valid @RequestBody CityDto cityDto) {
        CityDto city = cityService.createCity(cityDto);

        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "City created successfully").setData(city),
                HttpStatus.OK);
    }

    @PutMapping(value = "/cities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateCity(@PathVariable(name = "id") Long id, @Valid @RequestBody CityDto cityDto) {

        CityDto city = cityService.updateCity(id, cityDto);
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "City updated successfully").setData(city),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/cities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> deleteCity(@PathVariable(name = "id") Long id) {
        cityService.deleteCity(id);
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "City deleted successfully").setData(null),
                HttpStatus.OK);
    }

    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getAllCities() {

        List<CityDto> cities = cityService.getAllCities();

        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "City retrieved successfully").setData(cities),
                HttpStatus.OK);
    }

    @GetMapping(value = "/cities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDTO> getDistrict(@PathVariable(name = "id") Long id) {

        CityResponseDto city = cityService.getCity(id);
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "City details are retrieved successfully").setData(city),
                HttpStatus.OK);
    }
}
