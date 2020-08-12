package br.com.api.endpoints;

import br.com.api.ApplicationConfigTest;
import br.com.api.MainApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = ApplicationConfigTest.class)
@AutoConfigureMockMvc
@Sql(scripts = {"/before-test.sql", "/schema.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/clear-all.sql"}, executionPhase = AFTER_TEST_METHOD)
public abstract class EndpointTest {

    @Autowired
    protected MockMvc mvc;

    public String getToken() throws Exception {
        String body = "{\"userName\": \"user\",\"password\": \"user\"}";
        return mvc.perform(MockMvcRequestBuilders
                .post("/authenticate")
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(body))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();
    }
}
