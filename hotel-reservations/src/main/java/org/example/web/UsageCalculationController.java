package org.example.web;

import lombok.RequiredArgsConstructor;
import org.example.model.AvailableRooms;
import org.example.model.CalculatedUsage;
import org.example.service.UsageCalculationService;
import org.example.web.dto.UsageDto;
import org.example.web.dto.UsageMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room-usage")
@RequiredArgsConstructor
public class UsageCalculationController {

  private final UsageMapper mapper;
  private final UsageCalculationService service;

  @GetMapping
  public UsageDto calculateUsage(@RequestParam(name = "premiumRooms") int premiumRooms,
                                 @RequestParam(name = "economyRooms") int economyRooms) {
    final CalculatedUsage calculatedUsage =
        service.calculateUsage(AvailableRooms.builder().premiumRooms(premiumRooms).economyRooms(economyRooms).build());
    return mapper.map(calculatedUsage);

  }
}
