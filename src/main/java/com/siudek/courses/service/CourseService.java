package com.siudek.courses.service;

import com.siudek.courses.model.Course;
import com.siudek.courses.model.dto.StudentDto;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    List<Course> getCourses(Course.Status status, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader);
    Course getCourse(String code);

    Course addCourse(Course course, String containerName, MultipartFile file);

    void deleteCourse(String code);

    Course patchCourse(String code, Course course);

    Course putCourse(String code, Course course);

    Course addStudentToCourse(String code, Long id);

    List<StudentDto> getCourseMembers(String code);

    void finishEnroll(String code);
}
