package com.company.controller;

import com.company.dto.AdminDTO;
import com.company.config.TokenUtils;
import com.company.dto.PasswordDTO;
import com.company.dto.RegisterRequestDTO;
import com.company.dto.SoftwareEngineerDTO;
import com.company.dto.UserDataDTO;
import com.company.model.RegistrationRequest;
import com.company.service.AdministratorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:4200")
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private TokenUtils tokenUtils;

    private final static Logger logger = LogManager.getLogger(AuthenticationController.class);

    @PreAuthorize("hasPermission(#id, 'RegisterRequest', 'read')")
    @GetMapping(value = "/regRequestAll")
    public ResponseEntity<List<RegisterRequestDTO>> registerRequests(HttpServletRequest request) {

        List<RegisterRequestDTO> registerRequestDTO = administratorService.findAllPendingRequestsDTO();

        return new ResponseEntity<>(registerRequestDTO, HttpStatus.OK);

    }
    @PreAuthorize("hasPermission(#id, 'RegisterRequest', 'update')")
    @PutMapping(value = "/regRequestApprove", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<RegisterRequestDTO> approveRegistration(@RequestBody RegisterRequestDTO registerRequestDTO, HttpServletRequest request){

        this.administratorService.approveRegistration(registerRequestDTO);

        String address = request.getHeader("x-forwarded-for");
        if (address == null || address.length() == 0) {
            address = request.getHeader("http-x-forwarded-for");
            if (address == null || address.length() == 0) {
                address = request.getHeader("remote-addr");
                if (address == null || address.length() == 0) {
                    address = request.getRemoteAddr();
                }
            }
        }
        logger.info("Registration has been successfully approved from Host:"+ address +", Port:"+request.getRemotePort());

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasPermission(#id, 'RegisterRequest', 'update')")
    @PutMapping(value = "/regRequestCancel", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<RegisterRequestDTO> cancelRegistration(@RequestBody RegisterRequestDTO registerRequestDTO, HttpServletRequest request){

        this.administratorService.cancelRegistration(registerRequestDTO);

        String address = request.getHeader("x-forwarded-for");
        if (address == null || address.length() == 0) {
            address = request.getHeader("http-x-forwarded-for");
            if (address == null || address.length() == 0) {
                address = request.getHeader("remote-addr");
                if (address == null || address.length() == 0) {
                    address = request.getRemoteAddr();
                }
            }
        }
        logger.info("Registration has been successfully canceled from Host:" + address + ", Port:" + request.getRemotePort());

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasPermission(#id, 'Administrator', 'update')")
    @PutMapping(value = "/profileUpdate", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<AdminDTO> updateProfile(@RequestBody AdminDTO adminDTO, HttpServletRequest request){

        this.administratorService.updateProfile(adminDTO);

        String address = request.getHeader("x-forwarded-for");
        if (address == null || address.length() == 0) {
            address = request.getHeader("http-x-forwarded-for");
            if (address == null || address.length() == 0) {
                address = request.getHeader("remote-addr");
                if (address == null || address.length() == 0) {
                    address = request.getRemoteAddr();
                }
            }
        }
        logger.info("Admin data has been successfully updated from Host:"+ address +", Port:"+request.getRemotePort());

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasPermission(#id, 'Administrator', 'read')")
    @GetMapping(value="/changedPassword")
    @ResponseBody
    public ResponseEntity<?> CheckIfPasswordChanged(HttpServletRequest request){
        String username = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        boolean flag = administratorService.checkIfPasswordChanged(username);
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }
    @PreAuthorize("hasPermission(#id, 'Administrator', 'update')")
    @PostMapping(value = "/changePassword")
    public ResponseEntity<?> ChangePassword(HttpServletRequest request, @RequestBody PasswordDTO passwordDTO){
        if(passwordDTO.password == null || passwordDTO.confirmPassword == null ||
         !passwordDTO.password.equals(passwordDTO.confirmPassword)){

            String address = request.getHeader("x-forwarded-for");
            if (address == null || address.length() == 0) {
                address = request.getHeader("http-x-forwarded-for");
                if (address == null || address.length() == 0) {
                    address = request.getHeader("remote-addr");
                    if (address == null || address.length() == 0) {
                        address = request.getRemoteAddr();
                    }
                }
            }
            logger.info("Password unsuccessfully tried to be changed from Host:"+ address +", Port:"+request.getRemotePort() + " - Bad Request");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String username = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        administratorService.changePassword(username, passwordDTO);

        String address = request.getHeader("x-forwarded-for");
        if (address == null || address.length() == 0) {
            address = request.getHeader("http-x-forwarded-for");
            if (address == null || address.length() == 0) {
                address = request.getHeader("remote-addr");
                if (address == null || address.length() == 0) {
                    address = request.getRemoteAddr();
                }
            }
        }
        logger.info("Password successfully changed from Host:"+ address +", Port:"+request.getRemotePort());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
