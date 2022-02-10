package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@AllArgsConstructor
public class Money {
  private BigDecimal value;
  private Currency currency;

  public Money(Currency currency) {
    value = BigDecimal.ZERO;
    this.currency = currency;
  }

  public Money add(BigDecimal guest) {
    return new Money(value.add(guest), currency);
  }
}
