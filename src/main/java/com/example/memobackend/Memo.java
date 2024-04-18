package com.example.memobackend;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@RestController
public class Memo {
    MemoList memoList = new MemoList();

    // 從MemoList中獲取MemoItem
    // 之後會看要不要新增ID搜尋，透過ID尋找指定的MemoList
    @RequestMapping(value = "/get_memo_item")
    public Set<MemoItem> getMemoItemSetOfMemoList() {
        return memoList.getMemoItems();
    }

    @PostMapping(value = "/add_memo_item")
    public void addMemo(@RequestParam("title") String title,
                        @RequestParam("time") String time,
                        @RequestParam("alert_time") String alert_time,
                        @RequestParam("description") String description) {
        memoList.addMemoItem(new MemoItem(title, time,
                alert_time, description));
    }
}
