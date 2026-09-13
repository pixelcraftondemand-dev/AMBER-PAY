package sl.amberpay.coreapi.auth;

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
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginEndpointReturnsTokens() throws Exception {
        mockMvc.perform(post("/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"user@example.com\",\"password\":\"secret\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.access_token").exists())
            .andExpect(jsonPath("$.refresh_token").exists());
    }

    @Test
    void pinVerifyAcceptsFourToSixDigitPin() throws Exception {
        mockMvc.perform(post("/v1/auth/pin/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"pin\":\"1234\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.pin_token").exists());
    }

    @Test
    void registerCreatesUserAndLedgerWallet() throws Exception {
        mockMvc.perform(post("/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"newuser@example.com\",\"phone\":\"+23230000000\",\"full_name\":\"New User\",\"password\":\"Password123!\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.user.email").value("newuser@example.com"))
            .andExpect(jsonPath("$.wallet.currency").value("SLE"));
    }
}
