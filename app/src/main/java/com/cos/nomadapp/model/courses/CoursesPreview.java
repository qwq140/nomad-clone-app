package com.cos.nomadapp.model.courses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoursesPreview {
    private long id;
    private String title;
    private String subTitle;
    private String level;

    private PreviewImage previewImage;
}
