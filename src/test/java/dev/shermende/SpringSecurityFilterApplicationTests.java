package dev.shermende;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringSecurityFilterApplicationTests {
    private static final String OK = "ok";
    private static final String HEADER = "X-TOKEN";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ok() throws Exception {
        this.mockMvc.perform(get("/").header(HEADER, OK))
            .andExpect(status().isOk())
            .andExpect(jsonPath("name").exists())
            .andExpect(jsonPath("name").value(OK))
            .andExpect(jsonPath("principal").exists())
            .andExpect(jsonPath("principal").value(OK));
    }

    @Test
    public void notFound() throws Exception {
        this.mockMvc.perform(get("/not-found").header(HEADER, OK))
            .andExpect(status().isNotFound());
    }

    @Test
    public void unauthorized() throws Exception {
        this.mockMvc.perform(get("/"))
            .andExpect(status().isUnauthorized());
    }

}
