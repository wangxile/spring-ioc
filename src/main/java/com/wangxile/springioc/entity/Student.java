package com.wangxile.springioc.entity;

import com.wangxile.springioc.annotation.MyIoc;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author:wangqi
 * @Description:
 * @Date:Created in 2019/10/21
 * @Modified by:
 */
@MyIoc
@Getter
@Setter
public class Student {
    public String play(){
        return "student"+ this.toString();
    }
}
