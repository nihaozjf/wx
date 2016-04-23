package com.wx.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daos.HibernateBaseDAO1;
import com.wx.model.Config;


@Service("configService")
@Repository
@Transactional
public class ConfigService extends HibernateBaseDAO1<Config>{

}
