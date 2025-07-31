package com.mymanger.moneymanager.controller;

import com.mymanger.moneymanager.dto.AuthDTO;
import com.mymanger.moneymanager.dto.ProfileDTO;
import com.mymanger.moneymanager.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/register")
    public  ResponseEntity<ProfileDTO> registerProfile(@RequestBody ProfileDTO profileDTO){
        ProfileDTO registerProfile = profileService.registerProfile(profileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerProfile);

    }

    @GetMapping("/activate")
    public ResponseEntity<String> activateProfile(@RequestParam String token){
        boolean isActivated = profileService.activatePofile( token);
        if(isActivated){
            return ResponseEntity.ok("Profile activated successful");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activation Token not found or already exist");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody AuthDTO authDTO){
        try{
             if(!profileService.isAccountActive(authDTO.getEmail())){
                 return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message",
                         "Account is not active. Please activate the account first"));
             }

             Map<String,Object> response= profileService.authenticateAndGenerateToken(authDTO);
             return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message",e.getMessage()));
        }

    }

//    @GetMapping("/test")       //for testing using bearer token
//    public String test(){
//        return "Test Succefull";
//    }





}
