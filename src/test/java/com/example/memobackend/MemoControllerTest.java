package com.example.memobackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class MemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemoList memoList;

    @Test
    @WithMockUser
    public void testGetMemoItemSetAndAddMemo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/add_memo_item")
                .param("title", "aaa")
                .param("year", "2024")
                .param("month", "04")
                .param("day", "29")
                .param("hour", "15")
                .param("minute", "00")
                .param("alertTimeSelection", "Time of Memo")
                .param("description", "654321"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/add_memo_item")
                .param("title", "bbb")
                .param("year", "2024")
                .param("month", "06")
                .param("day", "06")
                .param("hour", "12")
                .param("minute", "00")
                .param("alertTimeSelection", "1 hour ago")
                .param("description", "123456"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/get_memo_items"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{\"title\":\"aaa\",\"time\":\"2024/04/29 15:00\",\"alertTimeSelection\":\"Time of Memo\",\"alertTime\":\"2024/04/29 15:00\",\"description\":\"654321\"}," +
                        "{\"title\":\"bbb\",\"time\":\"2024/06/06 12:00\",\"alertTimeSelection\":\"1 hour ago\",\"alertTime\":\"2024/06/06 11:00\",\"description\":\"123456\"}]"));
    }

}