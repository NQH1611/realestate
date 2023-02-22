package com.devcam.shop24h.repository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devcam.shop24h.entity.Ward;
public interface WardRepository extends JpaRepository<Ward, Long>{
    List<Ward> findByDistrict(int district);
}
