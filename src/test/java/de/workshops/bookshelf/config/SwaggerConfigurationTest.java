package de.workshops.bookshelf.config;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SwaggerConfigurationTest {

    @Nested
    class DuringDevelopment {

        @Autowired
        MockMvc mvc;

        @Test
        void swagger_should_be_available() throws Exception {
            mvc.perform(get("/swagger-ui/index.html"))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @ActiveProfiles("prod")
    class InProduction {

        @Autowired
        MockMvc mvc;

        @Test
        void swagger_should_not_be_available() throws Exception {
            mvc.perform(get("/swagger-ui/index.html"))
                    .andExpect(status().isNotFound());
        }
    }
}
