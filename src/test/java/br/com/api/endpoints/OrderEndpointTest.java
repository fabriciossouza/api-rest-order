package br.com.api.endpoints;

import br.com.api.infrastructure.util.YamlPropertyLoader;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderEndpointTest extends EndpointTest{

    @Test
    public void shouldReturnAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/rs/orders")
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/rs/orders/4001")
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldCreateOrder() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/rs/orders")
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldReturnItemByOrderId() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/rs/orders/4002/items")
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldAddItemInOrder() throws Exception {
        final YamlPropertyLoader scenarioLoader = new YamlPropertyLoader(
                "scenarios/orderTest.yml", "shouldAddItemInOrder");
        mvc.perform(MockMvcRequestBuilders
                .put("/rs/orders/4001/items")
                .header("Authorization", "Bearer " + getToken())
                .content(scenarioLoader.getInputBody())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldRemoveItemInOrder() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/rs/orders/4002/items/5002")
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldChange() throws Exception {
        final YamlPropertyLoader scenarioLoader = new YamlPropertyLoader(
                "scenarios/orderTest.yml", "shouldChange");
        mvc.perform(MockMvcRequestBuilders
                .put("/rs/orders/4001")
                .content(scenarioLoader.getInputBody())
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

}
