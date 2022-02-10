package org.example.web.dto;

import org.example.model.CalculatedUsage;
import org.example.model.RoomUsage;
import org.springframework.stereotype.Component;

@Component
public class UsageMapper {

  private static final String USAGE_TEMPLATE = "%s (%s %s)";

  public UsageDto map(CalculatedUsage calculatedUsage) {
    UsageDto dto = new UsageDto();
    final RoomUsage economyUsage = calculatedUsage.getEconomyUsage();
    dto.setEconomyUsage(
        String.format(USAGE_TEMPLATE, economyUsage.getUsedRooms(), economyUsage.getEarnedAmount().getCurrency().getCurrencyCode(),
            economyUsage.getEarnedAmount().getValue().toString()));
    final RoomUsage premiumUsage = calculatedUsage.getPremiumUsage();
    dto.setPremiumUsage(
        String.format(USAGE_TEMPLATE, premiumUsage.getUsedRooms(), premiumUsage.getEarnedAmount().getCurrency().getCurrencyCode(),
            premiumUsage.getEarnedAmount().getValue().toString()));
    return dto;
  }
}
