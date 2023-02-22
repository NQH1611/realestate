package com.devcam.shop24h.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devcam.shop24h.entity.Projects;
import com.devcam.shop24h.repository.ProjectRepositoty;
 
@RestController
@CrossOrigin
public class ProjectController {
    @Autowired
    ProjectRepositoty gProjectRepositoty;

    @GetMapping("/projects")
    public ResponseEntity<List<Projects>> getAllProjects(){
        List<Projects> lstProjects = new ArrayList<Projects>();
        lstProjects = gProjectRepositoty.findAll();
        if(lstProjects.size() !=0 ) return new ResponseEntity<>(lstProjects, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/ascprojects")
    public ResponseEntity<List<Projects>> getASCProjects(){
        List<Projects> lstProjects = new ArrayList<Projects>();
        gProjectRepositoty.getProjectSortAsc().forEach(lstProjects::add);
        if(lstProjects.size() !=0 ) return new ResponseEntity<>(lstProjects, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/descprojects")
    public ResponseEntity<List<Projects>> getDESCProjects(){
        List<Projects> lstProjects = new ArrayList<Projects>();
        gProjectRepositoty.getProjectSortDesc().forEach(lstProjects::add);
        if(lstProjects.size() !=0 ) return new ResponseEntity<>(lstProjects, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/searchproject/{provinceId}/{districtId}/{wardId}/{price}")
    public ResponseEntity<List<Projects>> searchProject(@PathVariable("provinceId") String provinceId,
    @PathVariable("districtId") String districtId,
    @PathVariable("wardId") String wardId,
    @PathVariable("price") int price){
        List<Projects> lstProjects = new ArrayList<Projects>();
        String province = "";
        String district = "";
        String ward = "";

        if(provinceId.equals("-1")){
            province = "%";
        }else{
            province = provinceId;
        }
        if(districtId.equals("-1")){
            district = "%";
        }else{
            district = districtId;
        }
        if(wardId.equals("-1")){
            ward = "%";
        }else{
            ward = wardId;
        }
        String price1 = "";
        String price2 = "";
        if(price ==     1){
            price1 = "0";
            price2 = "500000000";
        } else if(price == 2){
            price1 = "500000000";
            price2 = "1500000000";
        } else if(price == 3){
            price1 = "1500000000";
            price2 = "4500000000";
        }else if(price == 4){
            price1 = "4500000000";
            price2 = "99999999999";
        }else{
            price1 = "0";
            price2 = "999999999999";
        }
        System.out.println(province);
        gProjectRepositoty.searchProject(province, district, ward, price1, price2).forEach(lstProjects::add);
        if(lstProjects.size() !=0 ) return new ResponseEntity<>(lstProjects, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Projects> getProjectsByID(@PathVariable("id") int id){
        Optional<Projects> lstProjects = gProjectRepositoty.findById(id);
        if(lstProjects.isPresent() && lstProjects.get().getStatus() == 1) return new ResponseEntity<>(lstProjects.get(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/projectadmin/{id}")
    public ResponseEntity<Projects> getProjectsByIDWithAdmin(@PathVariable("id") int id){
        Optional<Projects> lstProjects = gProjectRepositoty.findById(id);
        if(lstProjects.isPresent()) return new ResponseEntity<>(lstProjects.get(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/eightproject")
    public ResponseEntity<List<Projects>> get8Projects(){
        List<Projects> lstProjects = new ArrayList<>();
        gProjectRepositoty.get8Projects().forEach(lstProjects::add);
        if(lstProjects.size() != 0) return new ResponseEntity<>(lstProjects, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/threeproject")
    public ResponseEntity<List<Projects>> get3Projects(){
        List<Projects> lstProjects = new ArrayList<>();
        gProjectRepositoty.get3Projects().forEach(lstProjects::add);
        if(lstProjects.size() != 0) return new ResponseEntity<>(lstProjects, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/projects")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HOMESELLER', 'ROLE_CUSTOMER')")
	public ResponseEntity<Object> createProject(@RequestBody Projects projects) {
		try {
			
			Projects newProject = new Projects();
            newProject.setName(projects.getName());
            newProject.setLat(projects.getLat());
            newProject.setLng(projects.getLng());
            newProject.setStreetId(projects.getStreetId());
            newProject.setProvinceId(projects.getProvinceId());
            newProject.setWardId(projects.getWardId());
            newProject.setDistrictId(projects.getDistrictId());
            newProject.setAcreage(projects.getAcreage());
            newProject.setAddress(projects.getAddress());
            newProject.setApartmenttArea(projects.getApartmenttArea());
            newProject.setConstructArea(projects.getConstructArea());
            newProject.setConstructContractor(projects.getConstructContractor());
            newProject.setDesignUnit(projects.getDesignUnit());
            newProject.setInvestor(projects.getInvestor());
            newProject.setNumApartment(projects.getNumApartment());
            newProject.setNumBlock(projects.getNumBlock());
            newProject.setNumFloors(projects.getNumFloors());
            newProject.setPhoto(projects.getPhoto());
            newProject.setRegionLink(projects.getRegionLink());
            newProject.setSlogan(projects.getSlogan());
            newProject.setUtilities(projects.getUtilities());
            newProject.setStatus(0);
            newProject.setPrice(projects.getPrice());
            newProject.setCustomer(projects.getCustomer());
           
			Projects savedRole = gProjectRepositoty.save(newProject);
			return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
			
		} catch (Exception e) {
			System.out.println("+++++++++++++++++++++::::: "+e.getCause().getCause().getMessage());
			return ResponseEntity.unprocessableEntity().body("Failed to Create specified Project: "+e.getCause().getCause().getMessage());
		}
	}

	@PutMapping("/project/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HOMESELLER')")
	public ResponseEntity<Object> updateProject(@PathVariable("id") int id, @RequestBody  Projects projects) {
		Optional<Projects> projectData = gProjectRepositoty.findById(id);
		if (projectData.isPresent()) {
			Projects newProject = projectData.get();
            newProject.setName(projects.getName());
            newProject.setAddress(projects.getAddress());
            newProject.setSlogan(projects.getSlogan());
            newProject.setDescription(projects.getDescription());
            newProject.setAcreage(projects.getAcreage());
            newProject.setNumBlock(projects.getNumBlock());
            newProject.setNumFloors(projects.getNumFloors());
            newProject.setNumApartment(projects.getNumApartment());
            newProject.setPhoto(projects.getPhoto());
            newProject.setStatus(projects.getStatus());
            newProject.setPrice(projects.getPrice());
			Projects saveProject = gProjectRepositoty.save(newProject);
			return new ResponseEntity<>(saveProject, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/project/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Object> deletecProjectById(@PathVariable int id) {
		try {
			Optional<Projects> optional= gProjectRepositoty.findById(id);
			if (optional.isPresent()) {
				gProjectRepositoty .deleteById(id);
			}else {
				
			}			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
