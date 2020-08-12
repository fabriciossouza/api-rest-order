package br.com.api.endpoints;

import br.com.api.infrastructure.util.YamlPropertyLoader;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductEndpointTest extends EndpointTest{

    @Test
    public void shouldReturnAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/rs/products")
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/rs/products/3001")
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldCreate() throws Exception {
        final YamlPropertyLoader scenarioLoader = new YamlPropertyLoader(
                "scenarios/productTest.yml", "shouldCreate");
        mvc.perform(MockMvcRequestBuilders
                .post("/rs/products")
                .content(scenarioLoader.getInputBody())
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldChange() throws Exception {
        final YamlPropertyLoader scenarioLoader = new YamlPropertyLoader(
                "scenarios/productTest.yml", "shouldChange");
        mvc.perform(MockMvcRequestBuilders
                .put("/rs/products/3003")
                .content(scenarioLoader.getInputBody())
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldDelete() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/rs/products/3003")
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

}
