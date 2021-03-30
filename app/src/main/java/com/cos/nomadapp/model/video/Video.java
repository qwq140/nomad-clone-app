package com.cos.nomadapp.model.video;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {
    private Long id;
    private String name;
    private String vimeoFolderId;

    private List<Map<String,Object>> contents = new ArrayList<>();
    //private List<List<Map<String, Object>>> contentList = new ArrayList<>();
    private Timestamp createDate;
}
