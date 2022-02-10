package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.model.AvailableRooms;
import org.example.model.CalculatedUsage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class UsageCalculationServiceTest {

  private final List<BigDecimal> testData = List.of(BigDecimal.valueOf(23), BigDecimal.valueOf(45), BigDecimal.valueOf(155),
      BigDecimal.valueOf(374), BigDecimal.valueOf(22), new BigDecimal("99.99"), BigDecimal.valueOf(100), BigDecimal.valueOf(101),
      BigDecimal.valueOf(115), BigDecimal.valueOf(209));

  private UsageCalculationService service;

  @BeforeEach
  void setUp() {
    PotentialGuestPaymentsProvider provider = new PotentialGuestPaymentsProvider();
    provider.registerPotentialGuestPayments(testData);
    service = new UsageCalculationService(provider);
  }

  // initial input [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
  @Test
  void shouldPassProvidedTestCase1() {
    //given
    AvailableRooms rooms = AvailableRooms.builder().premiumRooms(3).economyRooms(3).build();
    //when
    CalculatedUsage usage = service.calculateUsage(rooms);
    //then
    Assertions.assertThat(usage.getPremiumUsage().getUsedRooms()).isEqualTo(3);
    Assertions.assertThat(usage.getPremiumUsage().getEarnedAmount().getValue()).isEqualTo(BigDecimal.valueOf(738));
    Assertions.assertThat(usage.getPremiumUsage().getEarnedAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");
    Assertions.assertThat(usage.getEconomyUsage().getUsedRooms()).isEqualTo(3);
    Assertions.assertThat(usage.getEconomyUsage().getEarnedAmount().getValue()).isEqualTo(new BigDecimal("167.99"));
    Assertions.assertThat(usage.getEconomyUsage().getEarnedAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");
  }

  @Test
  void shouldPassProvidedTestCase2() {
    //given
    AvailableRooms rooms = AvailableRooms.builder().premiumRooms(7).economyRooms(5).build();
    //when
    CalculatedUsage usage = service.calculateUsage(rooms);
    //then
    Assertions.assertThat(usage.getPremiumUsage().getUsedRooms()).isEqualTo(6);
    Assertions.assertThat(usage.getPremiumUsage().getEarnedAmount().getValue()).isEqualTo(BigDecimal.valueOf(1054));
    Assertions.assertThat(usage.getPremiumUsage().getEarnedAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");
    Assertions.assertThat(usage.getEconomyUsage().getUsedRooms()).isEqualTo(4);
    Assertions.assertThat(usage.getEconomyUsage().getEarnedAmount().getValue()).isEqualTo(new BigDecimal("189.99"));
    Assertions.assertThat(usage.getEconomyUsage().getEarnedAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");
  }

  @Test
  void shouldPassProvidedTestCase3() {
    //given
    AvailableRooms rooms = AvailableRooms.builder().premiumRooms(2).economyRooms(7).build();
    //when
    CalculatedUsage usage = service.calculateUsage(rooms);
    //then
    Assertions.assertThat(usage.getPremiumUsage().getUsedRooms()).isEqualTo(2);
    Assertions.assertThat(usage.getPremiumUsage().getEarnedAmount().getValue()).isEqualTo(BigDecimal.valueOf(583));
    Assertions.assertThat(usage.getPremiumUsage().getEarnedAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");

    Assertions.assertThat(usage.getEconomyUsage().getUsedRooms()).isEqualTo(4);
    Assertions.assertThat(usage.getEconomyUsage().getEarnedAmount().getValue()).isEqualTo(new BigDecimal("189.99"));
    Assertions.assertThat(usage.getEconomyUsage().getEarnedAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");

  }

  @Test
  void shouldPassProvidedTestCase4() {
    //given
    AvailableRooms rooms = AvailableRooms.builder().premiumRooms(7).economyRooms(1).build();
    //when
    CalculatedUsage usage = service.calculateUsage(rooms);
    //then
    Assertions.assertThat(usage.getPremiumUsage().getUsedRooms()).isEqualTo(7);
    //is there a bug in the test data? I think it should be 1153.99, default test data has 6 customers paying 100 or more which totals 1054 (test case 2)
    //in this case since we have only one economy room, next highest payer (99.99) should get a free upgrade since there is a free premium room and we have
    //more guests than available rooms. That gives us 1153.99  and not 1153
    //    Original test expectations
    //    Test 4
    //● (input) Free Premium rooms: 7
    //● (input) Free Economy rooms: 1
    //● (output) Usage Premium: 7 (EUR 1153)
    //● (output) Usage Economy: 1 (EUR 45.99)

//    Assertions.assertThat(usage.getPremiumUsage().getAmount().getValue()).isEqualTo(BigDecimal.valueOf(1153));
    Assertions.assertThat(usage.getPremiumUsage().getEarnedAmount().getValue()).isEqualTo(new BigDecimal("1153.99"));
    Assertions.assertThat(usage.getPremiumUsage().getEarnedAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");
    Assertions.assertThat(usage.getEconomyUsage().getUsedRooms()).isEqualTo(1);
    //I guess here is the missing 0.99 from the premium usage, looks a typo in the test data
//    Assertions.assertThat(usage.getEconomyUsage().getAmount().getValue()).isEqualTo(new BigDecimal("45.99"));
    Assertions.assertThat(usage.getEconomyUsage().getEarnedAmount().getValue()).isEqualTo(BigDecimal.valueOf(45));
    Assertions.assertThat(usage.getEconomyUsage().getEarnedAmount().getCurrency().getCurrencyCode()).isEqualTo("EUR");
  }

}
