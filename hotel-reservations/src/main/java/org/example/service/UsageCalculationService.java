package org.example.service;

import org.example.model.AvailableRooms;
import org.example.model.CalculatedUsage;

import java.math.BigDecimal;

public class UsageCalculationService {

  private PotentialGuestPaymentsProvider potentialGuestPaymentsProvider;

  public UsageCalculationService(PotentialGuestPaymentsProvider potentialGuestPaymentsProvider) {
    this.potentialGuestPaymentsProvider = potentialGuestPaymentsProvider;
  }

  public CalculatedUsage calculateUsage(AvailableRooms rooms) {
    CalculatedUsage usage = new CalculatedUsage(rooms);
    for (BigDecimal guest : potentialGuestPaymentsProvider.getSortedPotentialGuestsPayments()) {
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
