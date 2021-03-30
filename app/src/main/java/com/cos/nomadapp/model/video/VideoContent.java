package com.cos.nomadapp.model.video;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoContent {
    private String title;
    private boolean isFree;
    private String vimeoId;
}
