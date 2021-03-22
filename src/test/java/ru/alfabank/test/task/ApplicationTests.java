package ru.alfabank.test.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.alfabank.test.task.controllers.TestTaskController;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestTaskController controller;

    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldReturnDefaultGreeting() throws Exception {
        this.mockMvc.perform(get("/"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(equalTo("Hello, world! I am OK!")));
    }

    @Test
    public void shouldReturnGiphyUrl() throws Exception {
        this.mockMvc.perform(get("/currency?currency=EUR"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(startsWith("https://giphy.com/embed/")));
    }

}
