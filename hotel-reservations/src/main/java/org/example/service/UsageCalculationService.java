package org.example.service;

import org.junit.jupiter.api.Test;

public class UsageCalculationService {
  private UsageCalculationService service;

  // initial input [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
  @Test
  void shouldPassProvidedTestCase1() {
//● (input) Free Premium rooms: 3
//● (input) Free Economy rooms: 3
//● (output) Usage Premium: 3 (EUR 738)
//● (output) Usage Economy: 3 (EUR 167.99)
  }

  @Test
  void shouldPassProvidedTestCase2() {
//    (input) Free Premium rooms: 7
//● (input) Free Economy rooms: 5
//● (output) Usage Premium: 6 (EUR 1054)
//● (output) Usage Economy: 4 (EUR 189.99)
  }

  @Test
  void shouldPassProvidedTestCase3() {
//● (input) Free Premium rooms: 2
//● (input) Free Economy rooms: 7
//● (output) Usage Premium: 2 (EUR 583)
//● (output) Usage Economy: 4 (EUR 189.99)
  }

  @Test
  void shouldPassProvidedTestCase4() {
//● (input) Free Premium rooms: 7
//● (input) Free Economy rooms: 1
//● (output) Usage Premium: 7 (EUR 1153)
//● (output) Usage Economy: 1 (EUR 45.99)
  }

}
