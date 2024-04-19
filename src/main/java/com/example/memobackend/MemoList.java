package com.example.memobackend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class MemoList {
    Set<MemoItem> memoItems;

    public MemoList() {
        memoItems = new HashSet<>();
    }

    // 添加 memoItem 到集合
    public void addMemoItem(MemoItem memoItem) {
        memoItems.add(memoItem);
    }

    // 查找集合中是否包含某個 memoItem
    public boolean containsMemoItem(MemoItem memoItem) {
        return memoItems.contains(memoItem);
    }

    // 刪除集合中的某個 memoItem
    public void removeMemoItem(MemoItem memoItem) {
        if (containsMemoItem(memoItem)) {
            memoItems.remove(memoItem);
        }
    }

    public Set<MemoItem> getMemoItems() {
        return this.memoItems;
    }
}
