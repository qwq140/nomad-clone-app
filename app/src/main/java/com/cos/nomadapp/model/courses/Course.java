package com.cos.nomadapp.model.courses;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {
    private long id;
    private String title;
    private String subTitle;
    private String backgroundColor;
    private String textColor;
    private String level;

    private Map<String,Object> previewImage = new HashMap<>();
    private Map<String,Object> mainImage = new HashMap<>();
    private List<Map<String,Object>> simpleImage = new ArrayList<>();
    private Map<String, Object> videoInfo = new HashMap<>();
    private List<Map<String, Object>> simpleInfo = new ArrayList<>();
    private List<Map<String, Object>> levelContent = new ArrayList<>();
    private List<Map<String, Object>> concept = new ArrayList<>();
    private Map<String, Object> skill = new HashMap<>();
    private List<Map<String, Object>> lectureAfter = new ArrayList<>();

    private long videoId;
    private String price;
    private Timestamp createDate;
}
