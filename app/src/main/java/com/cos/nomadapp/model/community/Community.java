package com.cos.nomadapp.model.community;

import com.cos.nomadapp.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community {
    private Long id;
    private String title;
    private String username;
    private String content;
    private Integer like;
    private String category;
    private String regTime;
}
