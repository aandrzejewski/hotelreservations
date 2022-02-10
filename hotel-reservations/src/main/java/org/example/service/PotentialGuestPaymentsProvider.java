package org.example.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PotentialGuestPaymentsProvider {

  private List<BigDecimal> potentialGuestsPayments =
      new ArrayList<>(List.of(BigDecimal.valueOf(23), BigDecimal.valueOf(45), BigDecimal.valueOf(155),
          BigDecimal.valueOf(374), BigDecimal.valueOf(22), new BigDecimal("99.99"), BigDecimal.valueOf(100), BigDecimal.valueOf(101),
          BigDecimal.valueOf(115), BigDecimal.valueOf(209)));

  public void registerPotentialGuestPayments(List<BigDecimal> potentialPayments) {
    potentialGuestsPayments = new ArrayList<>(potentialPayments);
    potentialGuestsPayments.sort(BigDecimal::compareTo);
    potentialGuestsPayments.sort(Collections.reverseOrder());
  }

  public List<BigDecimal> getSortedPotentialGuestsPayments() {
    return Collections.unmodifiableList(potentialGuestsPayments);
  }

}
