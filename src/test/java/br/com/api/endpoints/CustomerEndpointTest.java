package br.com.api.endpoints;

import br.com.api.infrastructure.util.YamlPropertyLoader;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerEndpointTest extends EndpointTest{

    @Test
    public void shouldReturnAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/rs/customers")
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldCreate() throws Exception {
        final YamlPropertyLoader scenarioLoader = new YamlPropertyLoader(
                "scenarios/customerTest.yml", "shouldCreate");
        mvc.perform(MockMvcRequestBuilders
                .post("/rs/customers")
                .header("Authorization", "Bearer " + getToken())
                .content(scenarioLoader.getInputBody())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestWhenTryCreateEmailAlreadyExisting() throws Exception {
        final YamlPropertyLoader scenarioLoader = new YamlPropertyLoader(
                "scenarios/customerTest.yml", "shouldTryCreateWithEmailExisting");

        mvc.perform(MockMvcRequestBuilders
                .post("/rs/customers")
                .header("Authorization", "Bearer " + getToken())
                .content(scenarioLoader.getInputBody())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldDelete() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/rs/customers/2020")
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }
}
