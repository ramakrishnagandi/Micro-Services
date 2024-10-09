package com.app.gateway.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.gateway.service.ActuatorService;

@RestController
public class ActuatorController {
	
	@Autowired
    private ActuatorService actuatorService;

    @GetMapping("/services/actuator")
    public Map<String, Map<String, Object>> getServicesActuatorData() {
        return actuatorService.getServicesHealthBeansMetrics();
    }

}
