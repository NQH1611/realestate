package com.devcam.shop24h.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devcam.shop24h.entity.Employees;
import com.devcam.shop24h.entity.Role;
import com.devcam.shop24h.entity.Token;
import com.devcam.shop24h.repository.EmployeeRepository;
import com.devcam.shop24h.repository.RoleRepository;
import com.devcam.shop24h.security.JwtUtil;
import com.devcam.shop24h.security.UserPrincipal;
import com.devcam.shop24h.service.EmployeeService;
import com.devcam.shop24h.service.TokenService;


@RestController
@CrossOrigin
public class EmployeeController {
    @Autowired
    EmployeeRepository gEmployeeRepository;
    @Autowired
    TokenService gTokenService;
    @Autowired
    EmployeeService gEmployeeService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/employee")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<Employees>> getAllEmployee(){
        List<Employees> lstEmployees= new ArrayList<Employees>();
        gEmployeeRepository.findAll().forEach(lstEmployees::add);
        if(lstEmployees.size() !=0 ) return new ResponseEntity<>(lstEmployees, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/employee/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable("id") Long id){
        Optional<Employees> employees= gEmployeeRepository.findById(id);
        if(employees.isPresent() ) return new ResponseEntity<>(employees.get(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<Employees> getEmployeeByUsername(@PathVariable("username") String username){
        Employees employees= gEmployeeRepository.getEmployeesByUserName(username);
        if(employees != null ) return new ResponseEntity<>(employees, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @PostMapping("/employee")
    public ResponseEntity<Object> createEmployee(@RequestBody Employees employees){
        try {
            Set<Role> roleUser = new HashSet();
            roleUser.add(roleRepository.findByRoleKey("ROLE_CUSTOMER"));
            Employees newEmployees = new Employees();
            newEmployees.setLastName(employees.getLastName());
            newEmployees.setFirstName(employees.getFirstName());
            newEmployees.setTitle(employees.getTitle());
            newEmployees.setTitleOfCourtesy(employees.getTitleOfCourtesy());
            newEmployees.setBirthDay(employees.getBirthDay());
            newEmployees.setHireDate(employees.getHireDate());
            newEmployees.setAddress(employees.getAddress());
            newEmployees.setCity(employees.getCity());
            newEmployees.setRegion(employees.getRegion());
            newEmployees.setPostalCode(employees.getPostalCode());
            newEmployees.setCountry(employees.getCountry());
            newEmployees.setHomePhone(employees.getHomePhone());
            newEmployees.setExtention(employees.getExtention());
            newEmployees.setPhoto(employees.getPhoto());
            newEmployees.setNotes(employees.getNotes());
            newEmployees.setReportsTo(employees.getReportsTo());
            newEmployees.setUserName(employees.getUserName());
            newEmployees.setPassword(new BCryptPasswordEncoder().encode(employees.getPassword()));
            newEmployees.setEmail(employees.getEmail());
            newEmployees.setActivated(employees.getActivated());
            newEmployees.setProfile(employees.getProfile());
            newEmployees.setUserLevel(employees.getUserLevel());
            newEmployees.setRoles(roleUser);
            Employees saveEmployees = gEmployeeRepository.saveAndFlush(newEmployees);
            return new ResponseEntity<>(saveEmployees, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified Employees: " + e.getCause().getCause().getMessage());
        }
    }
    @PostMapping("/loginemploy")
    public ResponseEntity<?> login(@RequestBody UserPrincipal user) {
        UserPrincipal userPrincipal = gEmployeeService.findByUserName(user.getUsername());
        if (new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword()) == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản hoặc mật khẩu không chính xác");
        }
        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));
        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatedBy(userPrincipal.getUserId());
        gTokenService.createToken(token);
        return ResponseEntity.ok(token.getToken());
    }
    @PutMapping("/employee/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") Long id, @RequestBody Employees employees){
        try {
            Optional<Employees> resultEmployees = gEmployeeRepository.findById(id);
            if(resultEmployees.isPresent())
            {
                Employees newEmployees = resultEmployees.get();
                newEmployees.setLastName(employees.getLastName());
                newEmployees.setFirstName(employees.getFirstName());
                newEmployees.setTitle(employees.getTitle());
                newEmployees.setTitleOfCourtesy(employees.getTitleOfCourtesy());
                newEmployees.setBirthDay(employees.getBirthDay());
                newEmployees.setHireDate(employees.getHireDate());
                newEmployees.setAddress(employees.getAddress());
                newEmployees.setCity(employees.getCity());
                newEmployees.setRegion(employees.getRegion());
                newEmployees.setPostalCode(employees.getPostalCode());
                newEmployees.setCountry(employees.getCountry());
                newEmployees.setHomePhone(employees.getHomePhone());
                newEmployees.setExtention(employees.getExtention());
                newEmployees.setPhoto(employees.getPhoto());
                newEmployees.setNotes(employees.getNotes());
                newEmployees.setReportsTo(employees.getReportsTo());
                newEmployees.setUserName(employees.getUserName());
                newEmployees.setPassword(employees.getPassword());
                newEmployees.setEmail(employees.getEmail());
                newEmployees.setActivated(employees.getActivated());
                newEmployees.setProfile(employees.getProfile());
                newEmployees.setUserLevel(employees.getUserLevel());
                String role = "";
                if(employees.getUserLevel() == 1){
                    role = "ROLE_ADMIN";
                } else if(employees.getUserLevel() == 2){
                    role = "ROLE_CUSTOMER";
                }else{
                    role = "ROLE_HOMESELLER";
                }
                Set<Role> roleUser = new HashSet();
                roleUser.add(roleRepository.findByRoleKey(role));
                newEmployees.setRoles(roleUser);
                Employees saveEmployees = gEmployeeRepository.save(newEmployees);
                return new ResponseEntity<>(saveEmployees, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Update specified Employees: " + e.getCause().getCause().getMessage());
        }
    }
    @DeleteMapping("/employee/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    private ResponseEntity<Object> deleteEmployeesById(@PathVariable Long id) {
        Optional<Employees> vDistrict = gEmployeeRepository.findById(id);
        if (vDistrict.isPresent()) {
            try {
                gEmployeeRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            Employees employees = new Employees();
            return new ResponseEntity<>(employees, HttpStatus.NOT_FOUND);
        }
    }
}
