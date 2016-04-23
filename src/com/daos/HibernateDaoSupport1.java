/**
 * 
 */
package com.daos;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author 
 *
 */

public class HibernateDaoSupport1 {
	
	public HibernateTemplate hibernateTemplate;
	public HibernateDaoSupport1()
	{
		ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:/resource/applicationContext-hibernate.xml");
		  hibernateTemplate = (HibernateTemplate)factory.getBean("hibernateTemplate");
		
	}
	

	public HibernateTemplate getHibernateTemplate() {
    	return hibernateTemplate;
      
	}
}
