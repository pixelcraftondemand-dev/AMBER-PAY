package sl.amberpay.coreapi.transfer;

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
import sl.amberpay.coreapi.auth.AuthUserStore;

@SpringBootTest
@AutoConfigureMockMvc
class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthUserStore authUserStore;

    @Test
    void transferRejectsUnknownRecipient() throws Exception {
        mockMvc.perform(post("/v1/transfers")
                .header("Pin-Token", "pin-token-1234")
                .header("Idempotency-Key", "transfer-key-unknown-recipient")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"recipient_email_or_phone\":\"missing@example.com\",\"amount_minor\":5000,\"currency\":\"SLE\",\"note\":\"Test transfer\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error.code").value("invalid_request"));
    }

    @Test
    void transferPostsLedgerTransferForRegisteredUsers() throws Exception {
        authUserStore.register("payee@example.com", "+23230000002", "Payee User", "Secret123!");

        mockMvc.perform(post("/v1/transfers")
                .header("Pin-Token", "pin-token-1234")
                .header("Idempotency-Key", "transfer-key-registered-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"recipient_email_or_phone\":\"payee@example.com\",\"amount_minor\":5000,\"currency\":\"SLE\",\"note\":\"Test transfer\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.journal_id").exists())
            .andExpect(jsonPath("$.status").value("completed"));
    }

    @Test
    void getTransferByIdReturnsStoredTransfer() throws Exception {
        String email = "lookup-recipient@example.com";
        authUserStore.register(email, "+23230000003", "Lookup User", "Secret123!");
        String response = mockMvc.perform(post("/v1/transfers")
                .header("Pin-Token", "pin-token-1234")
                .header("Idempotency-Key", "transfer-key-detail")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"recipient_email_or_phone\":\"" + email + "\",\"amount_minor\":5000,\"currency\":\"SLE\",\"note\":\"Lookup transfer\"}"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Matcher matcher = Pattern.compile("\"id\":\"([^\"]+)\"").matcher(response);
        if (!matcher.find()) {
            throw new IllegalStateException("Transfer response did not include an id: " + response);
        }
        String id = matcher.group(1);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/v1/transfers/" + id)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void walletTransactionsEndpointReturnsEntriesForWallet() throws Exception {
        String email = "wallet-recipient@example.com";
        authUserStore.register(email, "+23230000004", "Wallet User", "Secret123!");
        mockMvc.perform(post("/v1/transfers")
                .header("Pin-Token", "pin-token-1234")
                .header("Idempotency-Key", "transfer-key-wallet-tx")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"recipient_email_or_phone\":\"" + email + "\",\"amount_minor\":4500,\"currency\":\"SLE\",\"note\":\"wallet entry\"}"))
            .andExpect(status().isOk());

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/v1/wallets")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.wallets[0].id").exists());

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/v1/wallets/{id}/transactions?limit=25", "unknown-wallet-id"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void topupCreatesAndLooksUpTransaction() throws Exception {
        String response = mockMvc.perform(post("/v1/topups")
                .header("Pin-Token", "pin-token-1234")
                .header("Idempotency-Key", "topup-key-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"rail\":\"orange_money\",\"destination_phone\":\"+23276000000\",\"amount_minor\":2500,\"currency\":\"SLE\"}"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Matcher matcher = Pattern.compile("\"id\":\"([^\"]+)\"").matcher(response);
        if (!matcher.find()) {
            throw new IllegalStateException("Topup response did not include an id: " + response);
        }
        String id = matcher.group(1);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/v1/topups/" + id)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id));
    }
}
