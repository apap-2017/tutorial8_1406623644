package com.example.tutorial8.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tutorial8.model.CourseModel;
import com.example.tutorial8.model.StudentModel;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Many;

@Mapper
public interface CourseMapper
{
    
    @Insert("INSERT INTO student (npm, name, gpa) VALUES (#{npm}, #{name}, #{gpa})")
    void addStudent (StudentModel student);
    
    @Delete("DELETE FROM student WHERE npm = #{npm}")
    void deleteStudent (StudentModel student);
    
    @Update("UPDATE student SET name = #{name}, gpa =#{gpa} WHERE npm =#{npm}")
    void updateStudent (StudentModel student);
    
    @Select("select student.npm, name, gpa " +
    		"from studentcourse join student " +
    		"on studentcourse.npm = student.npm " +
    		"where studentcourse.id_course = #{id_course}")
    		List<StudentModel> selectStudents (@Param("id_course") String id_course);
    
    @Select("select id_course, name, credits from course where id_course = #{id_course}")
    		@Results(value = {
		    @Result(property="idCourse", column="id_course"),
		    @Result(property="name", column="name"),
		    @Result(property="credits", column="credits"),
		    @Result(property="students", column="id_course",
					    javaType = List.class,
					    many=@Many(select="selectStudents"))
    		})
    	CourseModel selectCourse (@Param("id_course") String id_course);
    
    @Select("select id_course, name, credits from course")
    		@Results(value = {
    			@Result(property="idCourse", column="id_course"),
		    @Result(property="name", column="name"),
		    @Result(property="credits", column="credits"),
		    @Result(property="students", column="id_course",
					    javaType = List.class,
					    many=@Many(select="selectStudents"))
    		})
    List<CourseModel> selectAllCourses ();
    
}