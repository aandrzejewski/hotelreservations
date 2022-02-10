package org.example.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsageDto {

  private String premiumUsage;
  private String economyUsage;
}
