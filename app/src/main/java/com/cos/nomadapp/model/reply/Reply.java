package com.cos.nomadapp.model.reply;


import com.cos.nomadapp.model.user.User;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply implements Serializable {
    private Long id;
    private String content;
    private User user;
    private Integer like;
    private String regTime;
}
