package com.wangxile.springioc;

import com.wangxile.springioc.annotation.MyIoc;
import com.wangxile.springioc.beandefinition.BeanDefinition;
import com.wangxile.springioc.entity.Student;
import com.wangxile.springioc.entity.User;
import com.wangxile.springioc.factory.MyBeanFactory;
import com.wangxile.springioc.factory.MyBeanFactoryImpl;
import org.reflections.Reflections;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@SpringBootApplication
public class SpringIocApplication implements CommandLineRunner {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(SpringIocApplication.class, args);
        MyBeanFactory beanFactory = new MyBeanFactoryImpl();
        User user1 = (User) beanFactory.getBeanByName("com.wangxile.springioc.entity.User");
        User user2 = (User) beanFactory.getBeanByName("com.wangxile.springioc.entity.User");
        Student student1 = user1.getStudent();
        Student student2 = user1.getStudent();
        Student student3 = (Student)beanFactory.getBeanByName("com.wangxile.springioc.entity.Student");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);
    }

    @Override
    public void run(String... args){
        ConcurrentHashMap<String,BeanDefinition> concurrentHashMap = new ConcurrentHashMap<>();
         Set<String> beanNameSet = Collections.synchronizedSet(new HashSet());
        Reflections reflections = new Reflections("com.wangxile");
        //获得项目中所有被MyIoc标记得类
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(MyIoc.class);
        //将其信息初始进自定义容器MyBeanFactory中
        for (Class clazz : typesAnnotatedWith){
            BeanDefinition beanDefinition = new BeanDefinition();
            String className = clazz.getName();
            String superclassName = clazz.getSuperclass().getName();
            beanDefinition.setClassName(className);
            beanDefinition.setSuperNames(superclassName);
            beanDefinition.setAlias(getClassName(className));
            concurrentHashMap.put(className, beanDefinition);
            beanNameSet.add(className);

        }
        MyBeanFactoryImpl.setBeanDineMap(concurrentHashMap);
        MyBeanFactoryImpl.setBeanNameSet(beanNameSet);
    }

    private String getClassName(String beanClassName) {
        String className = beanClassName.substring(beanClassName.lastIndexOf(".") + 1);
        className = className.substring(0, 1).toLowerCase() + className.substring(1);
        return className;
    }

}