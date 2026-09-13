package sl.amberpay.coreapi.checkout;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createCheckoutReturnsIdAndUrl() throws Exception {
        mockMvc.perform(post("/v1/checkouts")
                .header("X-API-Key", "merchant-demo-key")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount_minor\":1500,\"currency\":\"SLE\",\"payment_method\":\"wallet\",\"buyer\":{\"email\":\"buyer@example.com\",\"phone\":\"+23276000000\"},\"payment_code\":\"CHK-1001\",\"return_url\":\"https://merchant.example.com/return\",\"metadata\":{\"order_id\":\"OID-1\"}}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.checkout_url").exists());
    }

    @Test
    void confirmCheckoutMarksSucceeded() throws Exception {
        String response = mockMvc.perform(post("/v1/checkouts")
                .header("X-API-Key", "merchant-demo-key")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount_minor\":2000,\"currency\":\"SLE\",\"payment_method\":\"wallet\",\"buyer\":{\"email\":\"buyer2@example.com\",\"phone\":\"+23276000001\"},\"payment_code\":\"CHK-1002\",\"return_url\":\"https://merchant.example.com/return\",\"metadata\":{\"order_id\":\"OID-2\"}}"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Matcher matcher = Pattern.compile("\"id\":\"([^\"]+)\"").matcher(response);
        if (!matcher.find()) {
            throw new IllegalStateException("Checkout response did not include an id: " + response);
        }
        String id = matcher.group(1);

        mockMvc.perform(post("/v1/checkouts/" + id + "/confirm")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"pin_token\":\"pin-token-1234\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("succeeded"));
    }

    @Test
    void cancelCheckoutMarksCancelled() throws Exception {
        String response = mockMvc.perform(post("/v1/checkouts")
                .header("X-API-Key", "merchant-demo-key")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount_minor\":3000,\"currency\":\"SLE\",\"payment_method\":\"wallet\",\"buyer\":{\"email\":\"buyer3@example.com\",\"phone\":\"+23276000002\"},\"payment_code\":\"CHK-1003\",\"return_url\":\"https://merchant.example.com/return\",\"metadata\":{\"order_id\":\"OID-3\"}}"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Matcher matcher = Pattern.compile("\"id\":\"([^\"]+)\"").matcher(response);
        if (!matcher.find()) {
            throw new IllegalStateException("Checkout response did not include an id: " + response);
        }
        String id = matcher.group(1);

        mockMvc.perform(post("/v1/checkouts/" + id + "/cancel"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("cancelled"));
    }

    @Test
    void refundCheckoutMarksRefunded() throws Exception {
        String response = mockMvc.perform(post("/v1/checkouts")
                .header("X-API-Key", "merchant-demo-key")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount_minor\":4000,\"currency\":\"SLE\",\"payment_method\":\"wallet\",\"buyer\":{\"email\":\"buyer4@example.com\",\"phone\":\"+23276000003\"},\"payment_code\":\"CHK-1004\",\"return_url\":\"https://merchant.example.com/return\",\"metadata\":{\"order_id\":\"OID-4\"}}"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Matcher matcher = Pattern.compile("\"id\":\"([^\"]+)\"").matcher(response);
        if (!matcher.find()) {
            throw new IllegalStateException("Checkout response did not include an id: " + response);
        }
        String id = matcher.group(1);

        mockMvc.perform(post("/v1/checkouts/" + id + "/refund")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount_minor\":4000}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("refunded"));
    }
}
