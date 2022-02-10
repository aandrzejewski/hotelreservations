package org.example.web;

import lombok.RequiredArgsConstructor;
import org.example.service.PotentialGuestPaymentsProvider;
import org.example.web.dto.PotentialPaymentsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationsController {

  private final PotentialGuestPaymentsProvider provider;

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void registerPotentialPayments(@RequestBody PotentialPaymentsRequest request) {
    provider.registerPotentialGuestPayments(request.getPotentialPayments());
  }
}
