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
public class LookupControllerXMLTestCases {

  @Autowired private MockMvc mockMvc;

  // XML test cases for lookup service

  @Test
  @Order(1)
  public void verifyDirectoryOperationForLookupSuccess() throws Exception {

    String requestBody =
        "<request>\n"
            + "  <tenant>exampleTenant</tenant>\n"
            + "  <employmentStatus>Full-Time</employmentStatus>\n"
            + "  <currency>USD</currency>\n"
            + "  <skill>Java</skill>\n"
            + "  <education>Bachelor's Degree</education>\n"
            + "  <country>US</country>\n"
            + "  <state>California</state>\n"
            + "  <nationalities>American</nationalities>\n"
            + "</request>";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/lookup/add")
            .content(requestBody)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Lookup added successfully!"))
        .andExpect(MockMvcResultMatchers.xpath("/response/tenant").string("exampleTenant"))
        .andExpect(MockMvcResultMatchers.xpath("/response/employmentStatus").string("Full-Time"))
        .andExpect(MockMvcResultMatchers.xpath("/response/currency").string("USD"))
        .andExpect(MockMvcResultMatchers.xpath("/response/skill").string("Java"))
        .andExpect(MockMvcResultMatchers.xpath("/response/education").string("Bachelor's Degree"))
        .andExpect(MockMvcResultMatchers.xpath("/response/country").string("US"))
        .andExpect(MockMvcResultMatchers.xpath("/response/state").string("California"))
        .andExpect(MockMvcResultMatchers.xpath("/response/nationalities").string("American"))
        .andReturn();
  }

  @Test
  @Order(2)
  public void verifyLookupServiceUpdateTenant() throws Exception {
    String tenant = "exampleTenant";

    String requestBody =
        "<request>\n"
            + "  <tenant>exampleTenant</tenant>\n"
            + "  <employmentStatus>Part-Time</employmentStatus>\n"
            + "  <currency>EUR</currency>\n"
            + "  <skill>Python</skill>\n"
            + "  <education>Master's Degree</education>\n"
            + "  <country>DE</country>\n"
            + "  <state>Berlin</state>\n"
            + "  <nationalities>German</nationalities>\n"
            + "</request>";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/lookup/" + tenant)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Lookup updated successfully!"))
        .andExpect(MockMvcResultMatchers.xpath("/response/tenant").string("exampleTenant"))
        .andExpect(MockMvcResultMatchers.xpath("/response/employmentStatus").string("Part-Time"))
        .andExpect(MockMvcResultMatchers.xpath("/response/currency").string("EUR"))
        .andExpect(MockMvcResultMatchers.xpath("/response/skill").string("Python"))
        .andExpect(MockMvcResultMatchers.xpath("/response/education").string("Master's Degree"))
        .andExpect(MockMvcResultMatchers.xpath("/response/country").string("DE"))
        .andExpect(MockMvcResultMatchers.xpath("/response/state").string("Berlin"))
        .andExpect(MockMvcResultMatchers.xpath("/response/nationalities").string("German"))
        .andReturn();
  }

  @Test
  @Order(3)
  public void verifyLookupServiceForSearchTenant() throws Exception {
    String tenant = "exampleTenant";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/lookup/" + tenant)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Lookup found successfully!"))
        .andExpect(MockMvcResultMatchers.xpath("/response/tenant").string("exampleTenant"))
        .andExpect(MockMvcResultMatchers.xpath("/response/employmentStatus").string("Full-Time"))
        .andExpect(MockMvcResultMatchers.xpath("/response/currency").string("USD"))
        .andExpect(MockMvcResultMatchers.xpath("/response/skill").string("Java"))
        .andExpect(MockMvcResultMatchers.xpath("/response/education").string("Bachelor's Degree"))
        .andExpect(MockMvcResultMatchers.xpath("/response/country").string("US"))
        .andExpect(MockMvcResultMatchers.xpath("/response/state").string("California"))
        .andExpect(MockMvcResultMatchers.xpath("/response/nationalities").string("American"))
        .andReturn();
  }

  @Test
  @Order(4)
  public void verifyLookupServiceDelete() throws Exception {

    String tenant = "exampleTenant";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/lookup/" + tenant)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Lookup deleted successfully!"))
        .andReturn();
  }
}
