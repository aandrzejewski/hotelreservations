package org.example.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PotentialGuestPaymentsProvider {

  private List<BigDecimal> potentialGuestsPayments = new ArrayList<>();

  public void registerPotentialGuestPayments(List<BigDecimal> potentialPayments) {
    potentialGuestsPayments = new ArrayList<>(potentialPayments);
    potentialGuestsPayments.sort(BigDecimal::compareTo);
    potentialGuestsPayments.sort(Collections.reverseOrder());
  }

  public List<BigDecimal> getSortedPotentialGuestsPayments() {
    return Collections.unmodifiableList(potentialGuestsPayments);
  }

}
