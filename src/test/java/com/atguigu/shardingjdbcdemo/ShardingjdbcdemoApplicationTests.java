package com.atguigu.shardingjdbcdemo;

import com.atguigu.shardingjdbcdemo.entity.Course;
import com.atguigu.shardingjdbcdemo.entity.Dict;
import com.atguigu.shardingjdbcdemo.entity.User;
import com.atguigu.shardingjdbcdemo.mapper.CourseMapper;
import com.atguigu.shardingjdbcdemo.mapper.DictMapper;
import com.atguigu.shardingjdbcdemo.mapper.UdictMapper;
import com.atguigu.shardingjdbcdemo.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingjdbcdemoApplicationTests {

    //注入mapper
    @Autowired
    private CourseMapper courseMapper;

    //注入user的mapper
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UdictMapper udictMapper;

    //======================测试公共表===================
    @Autowired
    private DictMapper dictMapper;

    @Test
    public void addDict() {
        Dict dict = new Dict();
        dict.setStatus("Normal");
        dict.setValue("启用");
        dictMapper.insert(dict);
    }

    @Test
    public void deleteDict() {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_id", 536486065947541505L);
        dictMapper.delete(wrapper);
    }

    //=======================测试水平分表===================
    //添加课程的方法
    @Test
    public void addCourse() {
        for (int i = 1; i <= 10; i++) {
            Course course = new Course();
            course.setCname("java" + i);
            course.setUserId(100L);
            course.setCstatus("Normal" + i);
            courseMapper.insert(course);
        }
    }

    //查询课程的方法
    @Test
    public void findCourse() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", 465114666322886656L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }

    //======================测试水平分库=====================
    //添加操作
    @Test
    public void addCourseDb() {
        Course course = new Course();
        course.setCid(2L);
        course.setCname("javademo1");
        //分库根据user_id
        course.setUserId(111L);
        course.setCstatus("Normal1");
        courseMapper.insert(course);
    }

    //查询操作
    @Test
    public void findCourseDb() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //设置userid值
        wrapper.eq("user_id", 111L);
        //设置cid值
        wrapper.eq("cid", 2L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }

    //======================测试垂直分库==================
    //添加操作
    @Test
    public void addUserDb() {
        User user = new User();
        user.setUsername("lucymary");
        user.setUstatus("a");
        userMapper.insert(user);
    }

    //查询操作
    @Test
    public void findUserDb() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置userid值
        wrapper.eq("user_id", 465508031619137537L);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

}
