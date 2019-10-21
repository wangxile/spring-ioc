package com.wangxile.springioc.beandefinition;

import lombok.Data;

/**
 * @Author:wangqi
 * @Description:
 * @Date:Created in 2019/10/21
 * @Modified by:
 */
@Data
public class BeanDefinition {
    private String className;
    private String alias;
    private String superNames;
}
