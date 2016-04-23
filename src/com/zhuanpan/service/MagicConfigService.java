package com.zhuanpan.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daos.HibernateBaseDAO;
import com.zhuanpan.model.Magic_Config;

@Service("magicConfigService")
@Repository
@Transactional
public class MagicConfigService extends HibernateBaseDAO<Magic_Config>{

}
