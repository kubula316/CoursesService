package com.siudek.courses.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class Lecture {

    @Id
    private Long id;

    @NotBlank
    private String title;

    private String videoUrl;

    private String description;

    private List<String> materialsUrls = new ArrayList<>();
}
