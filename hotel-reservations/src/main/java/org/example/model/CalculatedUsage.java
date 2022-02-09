package org.example.model;

public class CalculatedUsage {
  private RoomUsage premiumUsage;
  private RoomUsage economyUsage;

  public RoomUsage getPremiumUsage() {
    return premiumUsage;
  }

  public RoomUsage getEconomyUsage() {
    return economyUsage;
  }
}
