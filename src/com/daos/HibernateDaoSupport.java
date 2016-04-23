/**
 * 
 */
package com.daos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 
 *
 */
@Component
public class HibernateDaoSupport {
	@Autowired
	public HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
    	return hibernateTemplate;
      
	}
}
