/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.spring_mvc.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author AnjaNali
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        HibernateDao dao = (HibernateDao)ctx.getBean("hibernateDAO");
        
        ((AbstractApplicationContext)ctx).close();
    }
    
}
