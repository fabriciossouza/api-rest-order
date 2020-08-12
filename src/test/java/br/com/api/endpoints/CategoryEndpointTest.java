package br.com.api.endpoints;

import br.com.api.infrastructure.util.YamlPropertyLoader;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryEndpointTest extends EndpointTest{

    @Test
    public void shouldReturnAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/rs/categories")
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/rs/categories/2001")
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldCreate() throws Exception {
        final YamlPropertyLoader scenarioLoader = new YamlPropertyLoader(
                "scenarios/categoryTest.yml", "shouldCreate");
        mvc.perform(MockMvcRequestBuilders
                .post("/rs/categories")
                .content(scenarioLoader.getInputBody())
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestWhenTryCreateWithSameNameAlreadyExisting() throws Exception {
        final YamlPropertyLoader scenarioLoader = new YamlPropertyLoader(
                "scenarios/categoryTest.yml", "shouldTryCreateWithExistingName");
        mvc.perform(MockMvcRequestBuilders
                .post("/rs/categories")
                .content(scenarioLoader.getInputBody())
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldChange() throws Exception {
        final YamlPropertyLoader scenarioLoader = new YamlPropertyLoader(
                "scenarios/categoryTest.yml", "shouldChange");
        mvc.perform(MockMvcRequestBuilders
                .put("/rs/categories/2002")
                .content(scenarioLoader.getInputBody())
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(scenarioLoader.getOutputBody()))
                .andReturn();
    }

    @Test
    public void shouldDelete() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/rs/categories/2004")
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

}
