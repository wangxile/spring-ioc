package com.wangxile.springioc.factory;

/**
 * @Author:wangqi
 * @Description:
 * @Date:Created in 2019/10/21
 * @Modified by:
 */
public interface MyBeanFactory {
    Object getBeanByName(String name) throws Exception;
}
