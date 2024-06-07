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
class CalendarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemoList memoList;

    @Test
    @WithMockUser
    public void apiCalendarTest() throws Exception {
        int year = 2024;
        int month = 4;
        int day = 25;
        String api = "/calendar?" + "year=" + year + "&month=" + month + "&day=" + day;
        mockMvc.perform(MockMvcRequestBuilders.get(api))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void apiRefreshHighlightTest() throws Exception {
        memoList.addMemoItem(new MemoItem("title", "2000/10/05 12:00", "5 minutes ago", "description", 1L));
        memoList.addMemoItem(new MemoItem("title", "2000/10/10 12:00", "5 minutes ago", "description", 2L));
        memoList.addMemoItem(new MemoItem("title", "2000/10/15 12:00", "5 minutes ago", "description", 3L));
        memoList.addMemoItem(new MemoItem("title", "2000/07/15 12:00", "5 minutes ago", "description", 4L));
        memoList.addMemoItem(new MemoItem("title", "2000/05/15 12:00", "5 minutes ago", "description", 5L));

        Calendar calendar = memoList.getCalendar();
        calendar.setShowingYear(2000);
        calendar.setShowingMonth(10);

        mockMvc.perform(MockMvcRequestBuilders.get("/refresh_highlight"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[5, 10, 15]"));
    }
}
