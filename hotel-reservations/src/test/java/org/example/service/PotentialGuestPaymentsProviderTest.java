package org.example.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class PotentialGuestPaymentsProviderTest {

  private PotentialGuestPaymentsProvider provider = new PotentialGuestPaymentsProvider();

  @Test
  void shouldReturnSortValuesOnRegistration() {
    //given
    provider.registerPotentialGuestPayments(List.of(BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.TEN));
    //when
    final List<BigDecimal> data = provider.getSortedPotentialGuestsPayments();
    //then
    Assertions.assertThat(data).containsExactly(BigDecimal.TEN, BigDecimal.ONE, BigDecimal.ZERO);
  }

}