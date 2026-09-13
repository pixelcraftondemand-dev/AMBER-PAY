package sl.amberpay.coreapi.rails;

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
class RailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void railCallbackAccepted() throws Exception {
        mockMvc.perform(post("/v1/rails/orange-money/callbacks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\":\"success\",\"reference\":\"REF-1001\",\"amount_minor\":1500,\"currency\":\"SLE\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("accepted"));
    }

    @Test
    void railStatusReportsHealth() throws Exception {
        mockMvc.perform(get("/v1/rails/orange-money/status"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.rail").value("orange-money"))
            .andExpect(jsonPath("$.status").exists());
    }
}
