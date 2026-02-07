package com.graduation.member2.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/api/risk")
@CrossOrigin
public class RiskController {

    @PostMapping("/analyze")
    public ResponseEntity<Map<String, Object>> analyzeRisk(
            @RequestBody Map<String, Object> intelligenceReport) {

        List<Map<String, Object>> indicators =
                (List<Map<String, Object>>) intelligenceReport.get("aggregated_indicators");

        int riskScore = indicators == null ? 0 : indicators.size() * 20;

        String riskLevel =
                riskScore >= 70 ? "HIGH" :
                riskScore >= 40 ? "MEDIUM" : "LOW";

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "risk_score", riskScore,
                "risk_level", riskLevel
        ));
    }
}
