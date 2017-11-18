package com.example.tutorial5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tutorial5.service.CourseService;
import com.example.tutorial5.service.StudentService;
import com.example.tutorial5.model.CourseModel;
import com.example.tutorial5.model.StudentModel;
import com.example.tutorial5.dao.*;

@Controller
public class CourseController
{
    @Autowired
    CourseService courseDAO;
    StudentService studentDAO;
    




    @RequestMapping("/student/add")
    public String add ()
    {
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa, null);
        studentDAO.addStudent (student);

        return "success-add";
    }


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


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
    	 StudentModel student = studentDAO.selectStudent (npm);

         if (student != null) {
             studentDAO.deleteStudent(npm);
             return "delete";
         } else {
            
             return "not-found";
         }
    	
    }
    
    @RequestMapping("/student/update/{npm}")
    public String update (Model model, @PathVariable(value = "npm") String npm)
    {
    	 StudentModel student = studentDAO.selectStudent (npm);

         if (student != null) {
             model.addAttribute ("student", student);
             return "form-update";
         } else {
             
             return "not-found";
         }
    	
    }
    
    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
   public String updateSubmit(StudentModel student)
    		{
    			studentDAO.updateStudent(student);
    			return "success-update";
    		}
	

}