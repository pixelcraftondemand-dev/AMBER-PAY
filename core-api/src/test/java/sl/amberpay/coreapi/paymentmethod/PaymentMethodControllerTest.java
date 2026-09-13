package sl.amberpay.coreapi.paymentmethod;

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
class PaymentMethodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void saveCardReturnsSavedMethod() throws Exception {
        mockMvc.perform(post("/v1/payment-methods/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"psp_token\":\"psp_tok_123\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.type").value("card"));
    }

    @Test
    void listPaymentMethodsReturnsCards() throws Exception {
        mockMvc.perform(post("/v1/payment-methods/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"psp_token\":\"psp_tok_list\"}"))
            .andExpect(status().isOk());

        mockMvc.perform(get("/v1/payment-methods"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.methods[0].type").exists());
    }

    @Test
    void completeThreeDsChallengeReturnsRedirectUrl() throws Exception {
        String createResponse = mockMvc.perform(post("/v1/payment-methods/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"psp_token\":\"psp_tok_456\"}"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        String id = createResponse.replaceAll(".*\"id\":\"([^\"]+)\".*", "$1");

        mockMvc.perform(post("/v1/payment-methods/cards/" + id + "/3ds-challenge")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"challenge_response\":\"approved\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.redirect_url").exists());
    }
}
