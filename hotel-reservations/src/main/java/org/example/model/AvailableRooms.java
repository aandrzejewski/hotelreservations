package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvailableRooms {

  private int premiumRooms;
  private int economyRooms;
}
