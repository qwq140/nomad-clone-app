package com.cos.nomadapp.model.community;

import com.cos.nomadapp.model.reply.Reply;
import com.cos.nomadapp.model.user.User;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community implements Serializable {
    private Long id;
    private String title;
    private User user;
    private List<Reply> reply;

    private String content;
    private Integer like;
    private String category;
    private String regTime;


    public Community(Long id, String title, String content, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
