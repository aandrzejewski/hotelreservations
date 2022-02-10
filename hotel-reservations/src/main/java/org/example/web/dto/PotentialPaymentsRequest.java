package org.example.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class PotentialPaymentsRequest {
  private List<BigDecimal> potentialPayments;
}
