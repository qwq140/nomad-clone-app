package com.cos.nomadapp.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tech {
    private Long id;
    private String title;
    private Timestamp createDate;
}
