package org.example.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CalculatedUsage {
  private RoomUsage premiumUsage = new RoomUsage();
  private RoomUsage economyUsage = new RoomUsage();
  private AvailableRooms availableRooms;

  public CalculatedUsage(AvailableRooms rooms) {
    this.availableRooms = rooms;
  }

  public boolean hasFreePremiumRoom() {
    return availableRooms.getPremiumRooms() > premiumUsage.getUsedRooms();
  }

  public void giveGuestPremiumRoom(BigDecimal guest) {
    premiumUsage.addGuest(guest);
  }

  public boolean hasFreeEconomyRoom() {
    return availableRooms.getEconomyRooms() > economyUsage.getUsedRooms();
  }

  public void giveGuestEconomyRoom(BigDecimal guest) {
    economyUsage.addGuest(guest);
  }

  public boolean canUpgradeSomeoneToPremium() {
    return hasFreePremiumRoom();
  }

  public void giveGuestEconomyRoomAndUpgradeHighestPaidEconomyGuestToPremium(BigDecimal guest) {
    BigDecimal highestPaid = economyUsage.getAndRemoveHighestPaidGuest();
    premiumUsage.addGuest(highestPaid);
    economyUsage.addGuest(guest);
  }
}
