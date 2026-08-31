package com.bhavesh16281.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import com.bhavesh16281.accounts.dto.CardsDto;

@FeignClient(name = "cards", fallback = CardsFallback.class)
public interface CardsFeignClient {
    
    @GetMapping("/api/cards/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader("MBBank-correlation-id") String correlationId, @RequestParam String mobileNumber);
}
