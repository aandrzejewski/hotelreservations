package org.example.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AvailableRooms {

  private int premiumRooms;
  private int economyRooms;
}
