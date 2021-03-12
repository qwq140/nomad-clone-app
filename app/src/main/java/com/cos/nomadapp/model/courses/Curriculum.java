package com.cos.nomadapp.model.courses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curriculum {
    private String chapter;
    private List<String> contents;
}
