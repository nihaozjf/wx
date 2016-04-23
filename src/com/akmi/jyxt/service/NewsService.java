package com.akmi.jyxt.service;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.akmi.jyxt.model.Newsinf;
import com.daos.HibernateBaseDAO;


@Service
@Repository
@Transactional

public class NewsService extends HibernateBaseDAO<Newsinf>{

}
