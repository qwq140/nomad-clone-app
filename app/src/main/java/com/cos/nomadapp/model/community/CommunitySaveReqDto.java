package com.cos.nomadapp.model.community;

import lombok.Data;

@Data
public class CommunitySaveReqDto {
    private String title;
    private Category category;
    private String content;
}
