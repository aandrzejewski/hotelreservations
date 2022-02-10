package org.example;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestConstants {

  public static final List<BigDecimal> GUEST_PAYMENTS = List.of(BigDecimal.valueOf(23), BigDecimal.valueOf(45), BigDecimal.valueOf(155),
      BigDecimal.valueOf(374), BigDecimal.valueOf(22), new BigDecimal("99.99"), BigDecimal.valueOf(100), BigDecimal.valueOf(101),
      BigDecimal.valueOf(115), BigDecimal.valueOf(209));

}
