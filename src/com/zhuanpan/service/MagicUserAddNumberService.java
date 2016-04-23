package com.zhuanpan.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daos.HibernateBaseDAO;

import com.zhuanpan.model.Magic_Useraddnumber;

@Service("magicUserAddNumberService")
@Repository
@Transactional

public class MagicUserAddNumberService extends HibernateBaseDAO<Magic_Useraddnumber>{

}
