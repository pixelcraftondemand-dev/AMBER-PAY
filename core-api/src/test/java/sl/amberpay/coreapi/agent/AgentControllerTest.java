package sl.amberpay.coreapi.agent;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AgentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void cashInCreditsCustomerWallet() throws Exception {
        mockMvc.perform(post("/v1/agents/cash-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"user_phone\":\"+23230000002\",\"amount_minor\":5000,\"currency\":\"SLE\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("completed"))
            .andExpect(jsonPath("$.customer_wallet_id").exists());
    }

    @Test
    void cashOutDebitsCustomerWallet() throws Exception {
        mockMvc.perform(post("/v1/agents/cash-out")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"user_phone\":\"+23230000003\",\"amount_minor\":3000,\"currency\":\"SLE\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("completed"))
            .andExpect(jsonPath("$.agent_float_minor").exists());
    }

    @Test
    void floatRouteReportsBalanceAndVolume() throws Exception {
        mockMvc.perform(get("/v1/agents/float"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.float_minor").exists())
            .andExpect(jsonPath("$.today_volume_minor").exists());
    }
}
