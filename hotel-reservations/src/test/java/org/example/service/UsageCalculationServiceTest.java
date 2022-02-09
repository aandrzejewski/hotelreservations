package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.model.AvailableRooms;
import org.example.model.CalculatedUsage;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class UsageCalculationServiceTest {

  private UsageCalculationService service = new UsageCalculationService();

  // initial input [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
  @Test
  void shouldPassProvidedTestCase1() {
    //given
    AvailableRooms rooms = AvailableRooms.builder().premiumRooms(3).economyRooms(3).build();
    //when
    CalculatedUsage usage = service.calculateUsage(rooms);
    //then
    Assertions.assertThat(usage.getPremiumUsage().getRooms()).isEqualTo(3);
    Assertions.assertThat(usage.getPremiumUsage().getAmount().getValue()).isEqualTo(BigDecimal.valueOf(738));
    Assertions.assertThat(usage.getPremiumUsage().getAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");
    Assertions.assertThat(usage.getEconomyUsage().getRooms()).isEqualTo(3);
    Assertions.assertThat(usage.getEconomyUsage().getAmount().getValue()).isEqualTo(new BigDecimal("167.99"));
    Assertions.assertThat(usage.getEconomyUsage().getAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");
  }

  @Test
  void shouldPassProvidedTestCase2() {
    //given
    AvailableRooms rooms = AvailableRooms.builder().premiumRooms(7).economyRooms(5).build();
    //when
    CalculatedUsage usage = service.calculateUsage(rooms);
    //then
    Assertions.assertThat(usage.getPremiumUsage().getRooms()).isEqualTo(6);
    Assertions.assertThat(usage.getPremiumUsage().getAmount().getValue()).isEqualTo(BigDecimal.valueOf(1054));
    Assertions.assertThat(usage.getPremiumUsage().getAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");
    Assertions.assertThat(usage.getEconomyUsage().getRooms()).isEqualTo(5);
    Assertions.assertThat(usage.getEconomyUsage().getAmount().getValue()).isEqualTo(new BigDecimal("189.99"));
    Assertions.assertThat(usage.getEconomyUsage().getAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");
  }

  @Test
  void shouldPassProvidedTestCase3() {
    // in the task description I got this test case might be wrong? Registered number of rooms is 9, but only 6 in the expected output.
    // Number of economy rooms and amount is the same as in testcase 2, so looks like a copy-paste bug?
    //● (input) Free Premium rooms: 2
    //● (input) Free Economy rooms: 7
    //● (output) Usage Premium: 2 (EUR 583)
    //● (output) Usage Economy: 4 (EUR 189.99)

    //given
    AvailableRooms rooms = AvailableRooms.builder().premiumRooms(2).economyRooms(7).build();
    //when
    CalculatedUsage usage = service.calculateUsage(rooms);
    //then
    Assertions.assertThat(usage.getPremiumUsage().getRooms()).isEqualTo(2);
    Assertions.assertThat(usage.getPremiumUsage().getAmount().getValue()).isEqualTo(BigDecimal.valueOf(583));
    Assertions.assertThat(usage.getPremiumUsage().getAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");

    Assertions.assertThat(usage.getEconomyUsage().getRooms()).isEqualTo(4);
    Assertions.assertThat(usage.getEconomyUsage().getAmount().getValue()).isEqualTo(new BigDecimal("189.99"));
    Assertions.assertThat(usage.getEconomyUsage().getAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");

  }

  @Test
  void shouldPassProvidedTestCase4() {
    //given
    AvailableRooms rooms = AvailableRooms.builder().premiumRooms(7).economyRooms(1).build();
    //when
    CalculatedUsage usage = service.calculateUsage(rooms);
    //then
    Assertions.assertThat(usage.getPremiumUsage().getRooms()).isEqualTo(7);
    Assertions.assertThat(usage.getPremiumUsage().getAmount().getValue()).isEqualTo(BigDecimal.valueOf(1153));
    Assertions.assertThat(usage.getPremiumUsage().getAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");
    Assertions.assertThat(usage.getEconomyUsage().getRooms()).isEqualTo(1);
    Assertions.assertThat(usage.getEconomyUsage().getAmount().getValue()).isEqualTo(new BigDecimal("45.99"));
    Assertions.assertThat(usage.getEconomyUsage().getAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");
  }

}
