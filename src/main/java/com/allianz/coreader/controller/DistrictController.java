package com.allianz.coreader.controller;

import com.allianz.coreader.dto.DistrictDto;
import com.allianz.coreader.dto.DistrictResponseDto;
import com.allianz.coreader.dto.ResponseDTO;
import com.allianz.coreader.entity.District;
import com.allianz.coreader.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.allianz.coreader.util.Constants.SUCCESS;

@RestController
@RequestMapping("api/v1")
public class DistrictController {

    @Autowired
    DistrictService districtService;

    @PostMapping(value = "/districts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createDistrict(@Valid @RequestBody DistrictDto districtDto) {
        DistrictDto district = districtService.createDistrict(districtDto);
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "District  created successfully").setData(district),
                HttpStatus.OK);
    }

    @PutMapping(value = "/districts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateDistrict(@PathVariable(name = "id") Long id, @Valid @RequestBody DistrictDto districtDto) {

        DistrictDto district = districtService.updateDistrict(id, districtDto);
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "District updated successfully").setData(district),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/districts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> deleteDistrict(@PathVariable(name = "id") Long id) {
        districtService.deleteDistrict(id);
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "District deleted successfully").setData(null),
                HttpStatus.OK);

    }

    @GetMapping(value = "/districts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getAllDistricts() {

        List<DistrictDto> allDistricts = districtService.getAllDistricts();
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "All districts retrieved successfully").setData(allDistricts),
                HttpStatus.OK);
    }

    @GetMapping(value = "/districts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getDistrict(@PathVariable(name = "id") Long id) {

        DistrictResponseDto district = districtService.getDistrict(id);
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "District retrieved successfully").setData(district),
                HttpStatus.OK);
    }
}
