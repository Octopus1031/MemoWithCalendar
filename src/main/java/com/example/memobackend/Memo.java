package com.example.memobackend;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@RestController
public class Memo {
    // 從MemoList中獲取MemoItem
    // 之後會看要不要新增ID搜尋，透過ID尋找指定的MemoList
    @RequestMapping(value = "/get_memo_item")
    public Set<MemoItem> getMemoItemSetOfMemoList() {
        MemoList memoList = new MemoList();
        memoList.addMemoItem(new MemoItem("完成HW4作業", "2024/04/24",
                "2024/04/24", "完成design部分"));
        memoList.addMemoItem(new MemoItem("瞎搞", "2024/04/24",
                "2024/04/24", "繼續瞎搞"));
        memoList.addMemoItem(new MemoItem("瞎搞", "2024/04/24",
                "2024/04/24", "繼續瞎搞"));
        memoList.addMemoItem(new MemoItem("瞎搞", "2024/04/24",
                "2024/04/24", "繼續瞎搞"));
        memoList.addMemoItem(new MemoItem("瞎搞", "2024/04/24",
                "2024/04/24", "繼續瞎搞"));
        memoList.addMemoItem(new MemoItem("瞎搞", "2024/04/24",
                "2024/04/24", "繼續瞎搞"));
        memoList.addMemoItem(new MemoItem("瞎搞", "2024/04/24",
                "2024/04/24", "繼續瞎搞"));
        memoList.addMemoItem(new MemoItem("瞎搞", "2024/04/24",
                "2024/04/24", "繼續瞎搞"));
        memoList.addMemoItem(new MemoItem("瞎毀", "2024/04/24",
                "2024/04/24", null));
        return memoList.getMemoItems();
    }
}
