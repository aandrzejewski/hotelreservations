package org.example.web;

import static org.example.TestConstants.GUEST_PAYMENTS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.example.service.PotentialGuestPaymentsProvider;
import org.example.web.dto.PotentialPaymentsRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationsControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private PotentialGuestPaymentsProvider provider;

  @Test
  void shouldRegisterPotentialPayments() throws Exception {
    //given
    PotentialPaymentsRequest request = new PotentialPaymentsRequest();
    request.setPotentialPayments(GUEST_PAYMENTS);
    List<BigDecimal> expectedDataFromProvider = new ArrayList<>(GUEST_PAYMENTS);
    expectedDataFromProvider.sort(BigDecimal::compareTo);
    expectedDataFromProvider.sort(Comparator.reverseOrder());

    //when
    mockMvc.perform(put(ReservationsController.REQUEST_MAPPING)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(request))
        )
        .andExpect(status().isOk());

    //then
    Assertions.assertThat(provider.getSortedPotentialGuestsPayments()).containsExactlyElementsOf(expectedDataFromProvider);

  }
}