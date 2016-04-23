package com.zhuanpan.service;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daos.HibernateBaseDAO;
import com.zhuanpan.model.Magic_Useraddorder;
@Service("magicUserAddOrderService")
@Repository
@Transactional
public class MagicUserAddOrderService extends HibernateBaseDAO<Magic_Useraddorder> {

}
