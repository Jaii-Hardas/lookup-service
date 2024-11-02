package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dnyanyog.LookupServiceMain;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = LookupServiceMain.class)
public class LookupControllerJSONTestCases {

  @Autowired private MockMvc mockMvc;

  // JSON test cases..
  @Test
  @Order(1)
  public void verifyDirectoryOperationForLookupSuccess() throws Exception {

    String requestBody =
        "{\n"
            + "  \"tenant\": \"exampleTenant\",\n"
            + "  \"employmentStatus\": \"Full-Time\",\n"
            + "  \"currency\": \"USD\",\n"
            + "  \"skill\": \"Java\",\n"
            + "  \"education\": \"Bachelor's Degree\",\n"
            + "  \"country\": \"US\",\n"
            + "  \"state\": \"California\",\n"
            + "  \"nationalities\": \"American\"\n"
            + "}";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/lookup/add")
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Case added successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.tenant").value("exampleTenant"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.employmentStatus").value("Full-Time"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("USD"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.skill").value("Java"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.education").value("Bachelor's Degree"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.country").value("US"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.state").value("California"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nationalities").value("American"))
        .andReturn();
  }

  @Test
  @Order(2)
  public void verfiyLookupServiceUpdateTenant() throws Exception {
    String tenant = "exampleTenant";

    String requestBody =
        "{\n"
            + "  \"tenant\": \"exampleTenant\",\n"
            + "  \"employmentStatus\": \"Part-Time\",\n"
            + "  \"currency\": \"EUR\",\n"
            + "  \"skill\": \"Python\",\n"
            + "  \"education\": \"Master's Degree\",\n"
            + "  \"country\": \"DE\",\n"
            + "  \"state\": \"Berlin\",\n"
            + "  \"nationalities\": \"German\"\n"
            + "}";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/lookup/" + tenant)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value("Lookup updated successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.tenant").value("exampleTenant"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.employmentStatus").value("Part-Time"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.skill").value("Python"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.education").value("Master's Degree"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.country").value("DE"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.state").value("Berlin"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nationalities").value("German"))
        .andReturn();
  }

  @Test
  @Order(3)
  public void verifyLookupServiceForSearchTenant() throws Exception {
    String tenant = "exampleTenant";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/lookup/" + tenant)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Lookup found successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.tenant").value("exampleTenant"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.employmentStatus").value("Full-Time"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("USD"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.skill").value("Java"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.education").value("Bachelor's Degree"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.country").value("US"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.state").value("California"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nationalities").value("American"))
        .andReturn();
  }

  @Test
  @Order(4)
  public void verifyLookupServiceDelete() throws Exception {

    String tenant = "exampleTenant";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/lookup/" + tenant)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value("Lookup deleted successfully!"))
        .andReturn();
  }
}
