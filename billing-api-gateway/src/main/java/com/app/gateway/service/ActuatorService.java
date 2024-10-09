package com.app.gateway.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ActuatorService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${services.list}")
    private String[] services;

    public Map<String, Map<String, Object>> getServicesHealthBeansMetrics() {
        Map<String, Map<String, Object>> result = new HashMap<>();
        
        for (String serviceName : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
            if (instances != null && !instances.isEmpty()) {
                ServiceInstance instance = instances.get(0);
                String baseUrl = instance.getUri().toString() + "/actuator";

                // Fetch health, beans, and metrics
                Map<String, Object> serviceData = new HashMap<>();
                try {
                    serviceData.put("health", restTemplate.getForObject(baseUrl + "/health", Object.class));
                    serviceData.put("beans", restTemplate.getForObject(baseUrl + "/beans", Object.class));
                    serviceData.put("metrics", restTemplate.getForObject(baseUrl + "/metrics", Object.class));
                } catch (Exception e) {
                    serviceData.put("error", "Failed to fetch data from " + serviceName);
                }

                result.put(serviceName, serviceData);
            }
        }
        return result;
    }
}

