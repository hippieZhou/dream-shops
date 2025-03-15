package com.hippiezhou.dreamshops.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // Clear messages before each test
        // This ensures a clean state for each test
        // Alternatively, you could use a test database or mock data
        // depending on your requirements
        SampleController messagesController = new SampleController();
        messagesController.getMessages().clear();
    }

    @Test
    public void testSayHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/sample/hello"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Hello, Spring Boot!"));
    }

    @Test
    public void testGetMessages() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/sample/messages"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));
    }

    @Test
    public void testAddMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sample/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"Test Message\""))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Message added: \"Test Message\""));
    }

    @Test
    public void testUpdateMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sample/messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content("\"Initial Message\""));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/sample/messages/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"Updated Message\""))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Message updated at index 0: \"Updated Message\""));
    }

    @Test
    public void testDeleteMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sample/messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content("\"Message to Delete\""));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/sample/messages/0"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Message removed at index 0: \"Message to Delete\""));
    }
}
