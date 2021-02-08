package com.mani.apps.springdata;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataApplication.class)
class SpringDataApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void findById_test(){
        Course course = courseRepository.findById(1000L);
        assertEquals("Hibernate in 100 Steps",course.getName());
    }

    @Test
    @DirtiesContext  //It will restore the data after this test
    public void deleteById_test(){
        courseRepository.deleteById(1000L);
        assertNull(courseRepository.findById(1000L));
    }

    @Test
    @DirtiesContext
    public void save_test(){
        Course course = courseRepository.findById(1000L);
        assertEquals("Hibernate in 100 Steps",course.getName());

        course.setName("Hibernate Ver2");
        courseRepository.save(course);

        Course course1 = courseRepository.findById(1000L);
        assertEquals("Hibernate Ver2",course1.getName());

    }


}
