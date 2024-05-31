package com.example.memobackend;

import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class LabelController {

    Set<Label> labelList = new HashSet<>();

    @PostMapping(value = "/add_label")
    public void addLabel(@RequestParam("title") String name) {
        Label newLabel = new Label(name);
        if (!labelList.contains(newLabel)) {
            labelList.add(newLabel);
        }
    }

    @GetMapping(value = "/get_label")
    public Set<Label> getLabel() {
        return labelList;
    }


}