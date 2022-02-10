package org.example.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PotentialGuestPaymentsProvider {

  private List<BigDecimal> potentialGuestsPayments = new ArrayList<>();

  public void registerPotentialGuestPayments(List<BigDecimal> potentialPayments) {
    potentialGuestsPayments = new ArrayList<>(potentialPayments);
    potentialGuestsPayments.sort(reversedComparator());
  }

  private Comparator<BigDecimal> reversedComparator() {
    return (o1, o2) -> -o1.compareTo(o2);
  }

  public List<BigDecimal> getSortedPotentialGuestsPayments() {
    return Collections.unmodifiableList(potentialGuestsPayments);
  }

}
