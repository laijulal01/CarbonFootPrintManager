package com.allianz.coreader.repository;

import com.allianz.coreader.entity.City;
import com.allianz.coreader.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {
    Optional<District> findByAreaCode(String areaCode);
}
