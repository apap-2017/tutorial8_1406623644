package com.example.tutorial8.service;

import java.util.List;

import com.example.tutorial8.model.CourseModel;
import com.example.tutorial8.model.StudentModel;

public interface CourseService
{
    CourseModel selectCourse (String id_course  );
    List<CourseModel> selectAllCourses ();


}