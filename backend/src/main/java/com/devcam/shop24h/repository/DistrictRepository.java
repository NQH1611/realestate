package com.devcam.shop24h.repository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devcam.shop24h.entity.District;

public interface DistrictRepository extends JpaRepository<District, Long>{
    List<District> findByProvince(int province);
}
