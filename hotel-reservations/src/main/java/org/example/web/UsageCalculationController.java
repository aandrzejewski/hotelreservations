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
@RequestMapping(UsageCalculationController.REQUEST_MAPPING)
@RequiredArgsConstructor
public class UsageCalculationController {

  static final String REQUEST_MAPPING = "/room-usage";
  static final String QUERY_PARAM_PREMIUM_ROOMS = "premiumRooms";
  static final String QUERY_PARAM_ECONOMY_ROOMS = "economyRooms";

  private final UsageMapper mapper;
  private final UsageCalculationService service;

  @GetMapping
  public UsageDto calculateUsage(@RequestParam(name = QUERY_PARAM_PREMIUM_ROOMS) int premiumRooms,
                                 @RequestParam(name = QUERY_PARAM_ECONOMY_ROOMS) int economyRooms) {
    final CalculatedUsage calculatedUsage =
        service.calculateUsage(AvailableRooms.builder().premiumRooms(premiumRooms).economyRooms(economyRooms).build());
    return mapper.map(calculatedUsage);

  }
}
