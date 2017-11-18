package com.example.tutorial8.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tutorial8.dao.CourseMapper;
import com.example.tutorial8.model.CourseModel;

import lombok.extern.slf4j.Slf4j;


@Service
public class CourseServiceDatabase implements CourseService
{
    @Autowired
    private CourseMapper courseMapper;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CourseServiceDatabase.class);
    @Override
    public CourseModel selectCourse (String id_course)
    {
        log.info ("select cours with id_course {}", id_course);
        return courseMapper.selectCourse (id_course);
    }


    @Override
    public List<CourseModel> selectAllCourses ()
    {
        log.info ("select all course");
        return courseMapper.selectAllCourses ();
    }

}