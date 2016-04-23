package com.daos;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.akmi.jyxt.utils.GenericsUtils;
import com.akmi.jyxt.utils.Page;


@Repository
public class HibernateBaseDAO<T> extends HibernateDaoSupport {

	protected final static Logger log = Logger.getLogger("dao");
	 @SuppressWarnings("unchecked")
	private Class<T> pojoClass=GenericsUtils.getSuperClassGenricType(this.getClass());
	 
	/**
	 * 更新对象
	 * @param object
	 * @return 
	 */
	public Boolean updateObject(Object  object) {
	 
		Boolean	retbool=false;
		log.debug("update  "+object.getClass().getName());
			
		try
		{
			retbool=true;
		getHibernateTemplate().update(object);
		getHibernateTemplate().flush();
		}
		catch(Exception ex)
		{
			
		}
		return retbool;
	}
	
	
	/**
	 * 更新或保存
	 * @param object
	 * @return 
	 */
	public Boolean saveorupdateObject(Object  object) {
	 
		Boolean	retbool=false;
		log.debug("update  "+object.getClass().getName());
			
		try
		{
			retbool=true;
		getHibernateTemplate().saveOrUpdate(object);
		getHibernateTemplate().flush();
		}
		catch(Exception ex)
		{
			
		}
		return retbool;
	}
	
	
	/**
	 * 保存对象
	 * @param object
	 * @return 
	 */
	public Serializable saveObject(Object  object) {
		log.debug("save  "+object.getClass().getName());
		return getHibernateTemplate().save(object);
		
	}
	
	
	
	  /**
	   * 获取所有对象
	 * @param clazz
	 * @return
	 */
	public List<T> getAllObject() {         
		    String hql="from  "+pojoClass.getName();
		    @SuppressWarnings("unchecked")
			List<T> list=getHibernateTemplate().find(hql);
			return list;
			
	    }  
	  
	
	
	/**
	 * 根据参数查询对象数目
	 * @param sql
	 * @param ojbarr
	 * @return
	 */
	public  int getObjectCount(String sql,Object[] ojbarr)
	{
		
		int count = Integer.parseInt(getHibernateTemplate().find(sql,ojbarr).iterator().next().toString());
		return count;
	}
	
	/**
	 * 通过主键获得对象
	 * @param <T>
	 * @param <T>
	 * @param <T>
	 * @param clazz
	 * @param objectid
	 * @return 
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public   T getObjectByObjectid(Serializable objectid) {
		//System.out.println(pojoClass.getSimpleName());
		log.debug("getting "+pojoClass.getSimpleName()+" instance with id: " + objectid);
		
		try {
			return  (T) getHibernateTemplate().get(pojoClass, objectid);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
		
	 /**
	  * 根据Hql删除对象
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return 
	 */
	public  Object delete(final String executeHql,final Map<String, Object> map) {
	Object ret=	getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(executeHql);
				Set<String> keySet = map.keySet();  
		           for (String string : keySet) {  
		               Object obj = map.get(string);
		               if(obj instanceof Collection<?>){  
		                   query.setParameterList(string, (Collection<?>)obj);  
		               }else if(obj instanceof Object[]){  
		                   query.setParameterList(string, (Object[])obj);  
		               }else{  
		                   query.setParameter(string,obj);  
		               }  
		           } 
		           return query.executeUpdate();
			}
			});
		return ret;
	}  
	
	 /**
	  * 根据列名删除对象
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return 
	 */
	public  Object deletebycolname(String colname,final Map<String, Object> map) {

  String executeHql="delete  "+pojoClass.getSimpleName()+" where "+colname+" in (:ids)";
	return delete(executeHql, map);
	}  
	
	

    /**
     * 获得查询数目
     * @param hql
     * @return
     */
    public List<Object[]> findCount(final String hql)
    {
    	List<Object[]> content = getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
			@SuppressWarnings("unchecked")
			public List<Object[]> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
    	return content;
    }
	

    
    
    /**
     * 根据查询条件返回结果
     * @param queryHql
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
	public  List<T> getObjectListByHql(final String queryHql,final Map<String, Object> map) {
	
		List<T> content = getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
   			public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
   				Query query = session.createQuery(queryHql);
   				Set<String> keySet = map.keySet();  
   	            for (String string : keySet) {  
   	                Object obj = map.get(string);
   	                if(obj instanceof Collection<?>){  
   	                    query.setParameterList(string, (Collection<?>)obj);  
   	                }else if(obj instanceof Object[]){  
   	                    query.setParameterList(string, (Object[])obj);  
   	                }else{  
   	                    query.setParameter(string,obj);  
   	                }  
   	            }  
   				return query.list();
   			}
   		});
		return content;		
	}

	  /**
	   * 根据条件获取对象
	 * @param clazz
	 * @return
	 */
	public T getObjectByquery(String queryHql,final Map<String, Object> map) {
	
	  List<T> objlist=getObjectListByHql(queryHql,map);
	  if(objlist!=null&&objlist.size()>0)
		  return objlist.get(0);
	  else 
		  return null;
	}
    /**
     * 返回前几条查询结前几条
     * @param queryHql
     * @param map
     * @param pagesize
     * @return
     */
    @SuppressWarnings("unchecked")
  	public  List<T> getObjectListByHql(final String queryHql,final Map<String, Object> map,final int pagesize) {
  	
  		List<T> content = getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
     			public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
     				Query query = session.createQuery(queryHql);
     				Set<String> keySet = map.keySet();  
     	            for (String string : keySet) {  
     	                Object obj = map.get(string);
     	                if(obj instanceof Collection<?>){  
     	                    query.setParameterList(string, (Collection<?>)obj);  
     	                }else if(obj instanceof Object[]){  
     	                    query.setParameterList(string, (Object[])obj);  
     	                }else{  
     	                    query.setParameter(string,obj);  
     	                }  
     	            }  
     	           query.setFirstResult(1);
      				query.setMaxResults(pagesize);
     				return query.list();
     			}
     		});
  		return content;		
  	}
    
    /**
     * 根据条件返回对象分页查询结果
     * @param pagesize
     * @param curpageindex
     * @param queryHql
     * @param countHql
     * @param map
     * @param countParam
     * @return
     */
    @SuppressWarnings("unchecked")
   	public  Page<T> findObjectByPageInf(final int pagesize,final int curpageindex,final String queryHql,String countHql,final Map<String, Object> map, Object[] countParam) {
   		int count =getObjectCount(countHql, countParam);
   	
   		final int startindex=pagesize*(curpageindex-1)>count?count:pagesize*(curpageindex-1);
   		List<T> content = getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
   			public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
   				Query query = session.createQuery(queryHql);
   				if(map!=null)
   				{
	   				Set<String> keySet = map.keySet();  
	   	            for (String string : keySet) {  
	   	                Object obj = map.get(string);
	   	                if(obj instanceof Collection<?>){  
	   	                    query.setParameterList(string, (Collection<?>)obj);  
	   	                }else if(obj instanceof Object[]){  
	   	                    query.setParameterList(string, (Object[])obj);  
	   	                }else{  
	   	                    query.setParameter(string,obj);   
	   	                }  
	   	            } 
   	            } 
   	            query.setFirstResult(startindex);
   				query.setMaxResults(pagesize);
   				return query.list();
   			}
   		});
   		Page<T> newpage=new Page<T>();
   		newpage.setCurpage(curpageindex);
   		newpage.setPagesize(pagesize);
   		newpage.setResult(content);
   		newpage.setSumcount(count);
   		return newpage;
   	}
    

    
    
    /**
     * 根据条件返回对象分页查询结果
     * @param pagesize
     * @param curpageindex
     * @param queryHql
     * @param countHql
     * @param map
     * @param countParam
     * @return
     */
    public  Page<T> findObjectByPageInf( int pagesize, int pageindex,final String queryHql,String countHql) {
   		
   		return findObjectByPageInf(pagesize,pageindex,queryHql,countHql,null,null);
   	}
    
    /**
     * 根据条件返回对象分页查询结果
     * @param pagesize
     * @param curpageindex
     * @param queryHql
     * @param countHql
     * @param map
     * @param countParam
     * @return
     */
    public  Page<T> findObjectByPageInf( int pagesize, int pageindex,final String orderHql) {
   		
    	 String queryHql="from  "+pojoClass.getSimpleName()+" "+orderHql;
    	 String countHql="select count(*) from "+pojoClass.getSimpleName();
   		return findObjectByPageInf(pagesize,pageindex,queryHql,countHql,null,null);
   	}
    

    /**
     * 根据条件返回分页数组查询查询结果
     * @param pagesize
     * @param curpageindex
     * @param queryHql
     * @param countHql
     * @param map
     * @param countParam
     * @return
     */
    @SuppressWarnings("unchecked")
   	public  Page<Object[]> findObjectarrByPageInf(final int pagesize,final int curpageindex,final String queryHql,String countHql,final Map<String, Object> map, Object[] countParam) {
   		int count =getObjectCount(countHql, countParam);
   		final int startindex=pagesize*(curpageindex-1)>count?count:pagesize*(curpageindex-1);
   		List<Object[]> content = getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
   			public List<Object[]> doInHibernate(Session session) throws HibernateException, SQLException {
   				Query query = session.createQuery(queryHql);
   				if(map!=null)
   				{
	   				Set<String> keySet = map.keySet();  
	   	            for (String string : keySet) {  
	   	                Object obj = map.get(string);
	   	                if(obj instanceof Collection<?>){  
	   	                    query.setParameterList(string, (Collection<?>)obj);  
	   	                }else if(obj instanceof Object[]){  
	   	                    query.setParameterList(string, (Object[])obj);  
	   	                }else{  
	   	                    query.setParameter(string,obj);   
	   	                }  
	   	            } 
   	            } 
   	            query.setFirstResult(startindex);
   				query.setMaxResults(pagesize);
   				return query.list();
   			}
   		});
   		Page<Object[]> newpage=new Page<Object[]>();
   		newpage.setCurpage(curpageindex);
   		newpage.setPagesize(pagesize);
   		newpage.setResult(content);
   		newpage.setSumcount(count);
   		return newpage;
   	}
    



}
