package org.example.web;

import static org.example.TestConstants.GUEST_PAYMENTS;
import static org.example.web.UsageCalculationController.QUERY_PARAM_ECONOMY_ROOMS;
import static org.example.web.UsageCalculationController.QUERY_PARAM_PREMIUM_ROOMS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.example.service.PotentialGuestPaymentsProvider;
import org.example.web.dto.UsageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UsageCalculationControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private PotentialGuestPaymentsProvider provider;

  @Test
  void shouldReturnCalculatedUsage() throws Exception {
    provider.registerPotentialGuestPayments(GUEST_PAYMENTS);

    final String responseString = mockMvc.perform(get(UsageCalculationController.REQUEST_MAPPING)
            .queryParam(QUERY_PARAM_PREMIUM_ROOMS, "3")
            .queryParam(QUERY_PARAM_ECONOMY_ROOMS, "3"))
        .andExpect(status().is2xxSuccessful())
        .andReturn().getResponse().getContentAsString();

    final UsageDto usageDto = objectMapper.readValue(responseString, UsageDto.class);

    Assertions.assertThat(usageDto.getEconomyUsage()).isEqualTo("3 (EUR 167.99)");
    Assertions.assertThat(usageDto.getPremiumUsage()).isEqualTo("3 (EUR 738)");

  }
}