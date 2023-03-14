package com.example.mefitbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/resources")
public class ResourceController {

    //TODO: add more if needed, remove unnecessary.
    @GetMapping("public")
    public ResponseEntity getPublic() {
        return ResponseEntity.ok("message");
    }

    @GetMapping("authenticated")
    public ResponseEntity getAuthenticated (){
        return ResponseEntity.ok("authenticated");
    }

    @GetMapping("authorized")
    public ResponseEntity getAuthorized (){
        return ResponseEntity.ok("authorized");
    }

    @GetMapping("authorized/offline")
    @PreAuthorize("hasRole('offline_access')")
    public ResponseEntity getAuthorizedOffline (){
        return ResponseEntity.ok("authorized offline");
    }
}
