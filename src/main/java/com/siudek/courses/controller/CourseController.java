package com.siudek.courses.controller;


import com.siudek.courses.model.Course;
import com.siudek.courses.model.dto.StudentDto;
import com.siudek.courses.storage.ImageStorageClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.siudek.courses.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {


    private final CourseService courseService;
    private final ImageStorageClient imageStorageClient;

    public CourseController(CourseService courseService, ImageStorageClient imageStorageClient) {
        this.courseService = courseService;
        this.imageStorageClient = imageStorageClient;
    }

    @GetMapping
    public List<Course> getCourses(@RequestParam(required = false) Course.Status status) {
        return courseService.getCourses(status);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Course addCourse(@Valid @RequestPart Course course, @RequestParam String containerName, @RequestPart MultipartFile file) {
        return courseService.addCourse(course, containerName, file);
    }

    @GetMapping("/{code}")
    public Course getCourse(@PathVariable String code) {
        return courseService.getCourse(code);
    }

    @PostMapping("/{code}/student/{id}")
    public Course addCourseMember(@PathVariable String code, @PathVariable Long id){
        return courseService.addStudentToCourse(code, id);
    }

    @GetMapping("/{code}/members")
    public List<StudentDto> showCourseMembersInformation(@PathVariable String code){
        return courseService.getCourseMembers(code);
    }

    @PostMapping("/{code}/finish-enroll")
    public ResponseEntity<?> finishEnroll(@PathVariable String code){
        courseService.finishEnroll(code);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/images")
    public String uploadImage(@RequestParam String containerName, @RequestParam MultipartFile file)throws IOException{
        try(InputStream inputStream = file.getInputStream()) {
            return this.imageStorageClient.uploadImage(containerName, file.getOriginalFilename(), inputStream, file.getSize());
        }
    }
}
