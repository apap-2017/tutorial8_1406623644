package com.example.tutorial8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tutorial8.service.CourseService;
import com.example.tutorial8.service.StudentService;
import com.example.tutorial8.model.CourseModel;
import com.example.tutorial8.model.StudentModel;
import com.example.tutorial8.dao.*;

@Controller
public class CourseController
{
    @Autowired
    CourseService courseDAO;
    StudentService studentDAO;
    



  


    @RequestMapping("/course/view")
    public String view (Model model,
            @RequestParam(value = "id_course", required = false) String id_course)
    {
        CourseModel course = courseDAO.selectCourse (id_course);

        if (course != null) {
            model.addAttribute ("course", course);
            return "view-course";
        } else {
            model.addAttribute ("id_course", id_course);
            return "not-found-course";
        }
    }


    @RequestMapping("/course/view/{id_course}")
    public String viewPath (Model model,
            @PathVariable(value = "id_course") String id_course)
    {
        CourseModel course = courseDAO.selectCourse (id_course);

        if (course != null) {
            model.addAttribute ("course", course);
            return "view-course";
        } else {
            model.addAttribute ("course", course);
            return "not-found-course";
        }
    }


    @RequestMapping("/course/viewall")
    public String view (Model model)
    {
        List<CourseModel> courses = courseDAO.selectAllCourses();
        model.addAttribute ("courses", courses);

        return "viewall-course";
    }


    
    
    
	

}