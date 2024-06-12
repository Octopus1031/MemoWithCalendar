package com.example.memobackend;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemoList memoList;

    @BeforeEach
    public void setup() {
        memoList.getMemoItems().clear(); // 在每个测试前清空 MemoList
    }

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

    @Test
    @WithMockUser
    public void testEditMemo() throws Exception {
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
        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get("/get_memo_items"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = getResult.getResponse().getContentAsString();
        JSONArray memoItemsArray = new JSONArray(jsonResponse);
        JSONObject memoItemObject = memoItemsArray.getJSONObject(0);
        Long id = memoItemObject.getLong("id");

        mockMvc.perform(MockMvcRequestBuilders.put("/edit_memo_item/" + id)
                        .param("title", "qqq")
                        .param("year", "2024")
                        .param("month", "04")
                        .param("day", "29")
                        .param("hour", "15")
                        .param("minute", "00")
                        .param("alertTimeSelection", "Time of Memo")
                        .param("description", "654321"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/get_memo_items"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{\"title\":\"qqq\",\"time\":\"2024/04/29 15:00\",\"alertTimeSelection\":\"Time of Memo\",\"alertTime\":\"2024/04/29 15:00\",\"description\":\"654321\"}]"));
    }

    @Test
    @WithMockUser
    public void testDeleteMemo() throws Exception {
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
        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get("/get_memo_items"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = getResult.getResponse().getContentAsString();
        JSONArray memoItemsArray = new JSONArray(jsonResponse);
        JSONObject memoItemObject = memoItemsArray.getJSONObject(0);
        Long id = memoItemObject.getLong("id");

        mockMvc.perform(MockMvcRequestBuilders.delete("/delete_memo_item/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/get_memo_items"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    @WithMockUser
    public void testGetMemoItemSetAndQueryMemo() throws Exception {
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
        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get("/get_memo_items"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = getResult.getResponse().getContentAsString();
        JSONArray memoItemsArray = new JSONArray(jsonResponse);
        JSONObject memoItemObject = memoItemsArray.getJSONObject(0);
        Long id = memoItemObject.getLong("id");

        mockMvc.perform(MockMvcRequestBuilders.get("/get_memo_item/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"title\":\"aaa\",\"time\":\"2024/04/29 15:00\",\"alertTimeSelection\":\"Time of Memo\",\"alertTime\":\"2024/04/29 15:00\",\"description\":\"654321\"}"));
    }

    @Test
    @WithMockUser
    public void testLabelClick() throws Exception {
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
        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get("/get_memo_items"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = getResult.getResponse().getContentAsString();
        JSONArray memoItemsArray = new JSONArray(jsonResponse);
        JSONObject memoItemObject = memoItemsArray.getJSONObject(0);
        Long id = memoItemObject.getLong("id");

        JSONObject payload = new JSONObject();
        payload.put("id", id);
        payload.put("labels", new JSONArray(List.of("label1", "label2")));


        mockMvc.perform(MockMvcRequestBuilders.post("/click_label")
                        .content(payload.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void testGetMemoItemsByLabel() throws Exception {
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
        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get("/get_memo_items"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String jsonResponse = getResult.getResponse().getContentAsString();
        JSONArray memoItemsArray = new JSONArray(jsonResponse);
        JSONObject memoItemObject = memoItemsArray.getJSONObject(0);
        Long id = memoItemObject.getLong("id");

        JSONObject payload = new JSONObject();
        payload.put("id", id);
        payload.put("labels", new JSONArray(List.of("label1", "label2")));

        memoItemObject = memoItemsArray.getJSONObject(0);
        id = memoItemObject.getLong("id");

        payload = new JSONObject();
        payload.put("id", id);
        payload.put("labels", new JSONArray(List.of("label2")));

        mockMvc.perform(MockMvcRequestBuilders.get("/get_memo_items_by_label")
                        .param("label", "label1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}