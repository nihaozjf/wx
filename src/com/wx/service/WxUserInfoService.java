package com.wx.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daos.HibernateBaseDAO;
import com.wx.model.WxUserInfo;

@Service("wxUserInfoService")
@Repository
@Transactional
public class WxUserInfoService extends HibernateBaseDAO<WxUserInfo> {

}
