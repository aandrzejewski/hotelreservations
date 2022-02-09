package org.example.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
public class Money {
  private BigDecimal value;
  private Currency currency;
}
