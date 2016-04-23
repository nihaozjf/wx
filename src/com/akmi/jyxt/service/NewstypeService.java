package com.akmi.jyxt.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.akmi.jyxt.model.Newstype;
import com.daos.HibernateBaseDAO;

@Service("newstypeService")
@Repository
@Transactional

public class NewstypeService extends HibernateBaseDAO<Newstype>{


	
	
	 /**
	  * 根据栏目类型获得栏目信息
	  * @param lmtype
	  * @return
	 */
	public List<Newstype> getlmlistbylmtypeandparentid(Object[] lmtype,int parentlmid)
	 {
		 String queryHql="select New Newstype(newstypeid, lmtype,newstypename) from "+Newstype.class.getSimpleName()+" t1  where t1.lmtype in (:lmtype) " ;
		 Map<String, Object> map=new HashMap<String,Object>();
		map.put("lmtype",lmtype);
		 if(parentlmid==0)
			 queryHql+="and t1.parentnewstype is null";
		 else
		 {
			 queryHql+="and t1.parentnewstype =(from Newstype where newstypeid=:parentlmid)";
			 map.put("parentlmid", parentlmid);
		 }
	     return this.getObjectListByHql(queryHql, map);
	 }
}
