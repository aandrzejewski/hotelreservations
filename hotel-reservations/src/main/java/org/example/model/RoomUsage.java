package org.example.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Getter
public class RoomUsage {
  private List<BigDecimal> guests = new ArrayList<>();

  public void addGuest(BigDecimal guest) {
    guests.add(guest);
  }

  public BigDecimal getAndRemoveHighestPaidGuest() {
    guests.sort(BigDecimal::compareTo);
    return guests.remove(guests.size() - 1);
  }

  public Money getAmount() {
    Money amount = new Money(Currency.getInstance("EUR"));
    for (BigDecimal guest : guests) {
      amount = amount.add(guest);
    }
    return amount;
  }

  public int getUsedRooms() {
    return guests.size();
  }
}
