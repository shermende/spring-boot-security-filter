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
    private static final String HEADER = "X-TOKEN";
    private static final String TOKEN = "access-token";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ok() throws Exception {
        this.mockMvc.perform(get("/").header(HEADER, TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value(TOKEN))
                .andExpect(jsonPath("principal").exists())
                .andExpect(jsonPath("principal").value(TOKEN));
    }

    @Test
    public void notFound() throws Exception {
        this.mockMvc.perform(get("/not-found").header(HEADER, TOKEN))
                .andExpect(status().isNotFound());
    }

    @Test
    public void unauthorized() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

}
