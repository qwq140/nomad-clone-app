package com.cos.nomadapp.model.courses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Charge {
    private String title;
    private String content;
    private String price;
    private String backgroundColor;
    private String textColor;
}
