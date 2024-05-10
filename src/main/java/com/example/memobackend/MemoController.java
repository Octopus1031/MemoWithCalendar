package com.example.memobackend;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class MemoController {
    MemoList memoList = new MemoList();

    // 從MemoList中獲取MemoItem
    // 之後會看要不要新增ID搜尋，透過ID尋找指定的MemoList
    @RequestMapping(value = "/get_memo_item")
    public Set<MemoItem> getMemoItemSetOfMemoList() {
        return memoList.getMemoItems();
    }

    @PostMapping(value = "/add_memo_item")
    public void addMemo(@RequestParam("title") String title,
                        @RequestParam("year") String year,
                        @RequestParam("month") String month,
                        @RequestParam("day") String day,
                        @RequestParam("hour") String hour,
                        @RequestParam("minute") String minute,
                        @RequestParam("alertTimeSelection") String alertTimeSelection,
                        @RequestParam("description") String description) {
        String time = year + '/' + month + '/' + day + ' ' + hour + ':' + minute;
        Long id = memoList.generateId();
        memoList.addMemoItem(new MemoItem(title, time,
                alertTimeSelection, description, id));
    }

    // 刪除指定的MemoItem
    @DeleteMapping(value = "/delete_memo_item/{id}")
    public void deleteMemoItem(@PathVariable String id) {
        Long memoItemId = Long.parseLong(id);
        memoList.removeMemoItemById(memoItemId);
    }
}
