package com.mani.apps.springdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Get Data
        Course course = courseRepository.findById(1000L);
        logger.info(course.toString());

        //Update Data
        course.setName("Hibernate Ver2");
        courseRepository.save(course);
        logger.info(courseRepository.findById(1000L).toString());

        //Insert Data
        Course courseInsert =  new Course();
        courseInsert.setName("Spring in Action");
        logger.info(courseRepository.save(courseInsert).toString());

        //Delete Data
        courseRepository.deleteById(1000L);
        logger.info(courseRepository.findById(1000L)==null?"Course 1000 is Deleted":"Failed");
    }
}
