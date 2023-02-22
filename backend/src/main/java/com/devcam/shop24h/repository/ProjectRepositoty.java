package com.devcam.shop24h.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcam.shop24h.entity.Projects;


public interface ProjectRepositoty extends JpaRepository<Projects, Integer>{
    @Query(value = "select * from project where status = 1 limit 6", nativeQuery = true)
    List<Projects> get8Projects();
    @Query(value = "select * from project where status = 1 limit 3", nativeQuery = true)
    List<Projects> get3Projects();
    @Query(value = "SELECT * FROM `project` ORDER BY price ASC;", nativeQuery = true)
    List<Projects> getProjectSortAsc();
    @Query(value = "SELECT * FROM `project` ORDER BY price DESC;", nativeQuery = true)
    List<Projects> getProjectSortDesc();
    @Query(value = "SELECT * FROM `project` WHERE _province_id LIKE " + ":provinceid" + " AND _ward_id LIKE "+ ":wardid"+ " AND _district_id LIKE " +":districtid" + " AND price BETWEEN :price1 AND :price2", nativeQuery = true)
    List<Projects> searchProject(@Param("provinceid") String provinceid, 
    @Param("districtid") String districtid, 
    @Param("wardid") String wardid, 
    @Param("price1") String price1, 
    @Param("price2") String price2);
}
