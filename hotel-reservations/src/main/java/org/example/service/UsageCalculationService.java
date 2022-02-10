package org.example.service;

import org.example.model.AvailableRooms;
import org.example.model.CalculatedUsage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsageCalculationService {

  private List<BigDecimal> potentialGuestsPayments =
      new ArrayList<>(List.of(BigDecimal.valueOf(23), BigDecimal.valueOf(45), BigDecimal.valueOf(155),
          BigDecimal.valueOf(374), BigDecimal.valueOf(22), new BigDecimal("99.99"), BigDecimal.valueOf(100), BigDecimal.valueOf(101),
          BigDecimal.valueOf(115), BigDecimal.valueOf(209)));

  public UsageCalculationService() {
    potentialGuestsPayments.sort(BigDecimal::compareTo);
    potentialGuestsPayments.sort(Collections.reverseOrder());
  }

  public CalculatedUsage calculateUsage(AvailableRooms rooms) {
    CalculatedUsage usage = new CalculatedUsage(rooms);
    for (BigDecimal guest : potentialGuestsPayments) {
      if (pays100OrMore(guest)) {
        if (usage.hasFreePremiumRoom()) {
          usage.giveGuestPremiumRoom(guest);
        }
      } else {
        if (usage.hasFreeEconomyRoom()) {
          usage.giveGuestEconomyRoom(guest);
        } else if (usage.canUpgradeSomeoneToPremium()) {
          usage.giveGuestEconomyRoomAndUpgradeHighestPaidEconomyGuestToPremium(guest);
        }
      }
    }
    return usage;
  }

  private boolean pays100OrMore(BigDecimal guest) {
    return BigDecimal.valueOf(100).compareTo(guest) <= 0;
  }
}
