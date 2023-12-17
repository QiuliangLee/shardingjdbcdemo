package com.atguigu.shardingjdbcdemo.entity;

/**
 * @author liqiuliang
 * @create 2023-12-17 2:01
 */

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 又坏又迷人
 * 公众号: Java菜鸟程序员
 * @date 2020/11/20
 * @Description:Dict实体类
 */
@Data
@TableName("t_dict")
public class Dict {
    private Long dictId;
    private String status;
    private String value;
}