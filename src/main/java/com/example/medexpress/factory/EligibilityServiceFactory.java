package com.example.medexpress.factory;

import com.example.medexpress.model.EligibilityResult;
import com.example.medexpress.service.AllergyEligibilityService;
import com.example.medexpress.service.EligibilityService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EligibilityServiceFactory {

    private final Map<Long, EligibilityService> eligibilityServiceMap = new HashMap<>();

    public EligibilityServiceFactory(List<EligibilityService> services) {
        eligibilityServiceMap.put(1L, services.stream()
                .filter(s -> s instanceof AllergyEligibilityService)
                .findFirst()
                .orElseThrow());
    }

    public EligibilityService getService(Long conditionId) {
        return eligibilityServiceMap.getOrDefault(conditionId,
                (conditionId1, answers) -> new EligibilityResult(conditionId1, false, "Consultation unavailable for this condition"));
    }
}
