package com.akmi.jyxt.service;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.akmi.jyxt.model.Teacherinf;
import com.daos.HibernateBaseDAO;

@Service("teacherinfService")
@Repository
@Transactional

public class TeacherInfService extends HibernateBaseDAO<Teacherinf>{


}
