package com.wangxile.springioc.entity;

import com.wangxile.springioc.annotation.MyIoc;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * @Author:wangqi
 * @Description:
 * @Date:Created in 2019/10/21
 * @Modified by:
 */
@MyIoc
@Getter
@Setter
public class User {
    private Student student;
}
