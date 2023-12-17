package com.atguigu.shardingjdbcdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@TableName(value = "t_user")  //指定对应表
@Service
public class User {
    private Long userId;
    private String username;
    private String ustatus;
}
