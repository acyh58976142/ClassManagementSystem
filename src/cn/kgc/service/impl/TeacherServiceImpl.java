package cn.kgc.service.impl;

import java.util.List;

import cn.kgc.dao.TeacherDao;
import cn.kgc.dao.impl.TeacherDaoImpl;
import cn.kgc.entity.Page;
import cn.kgc.entity.Teacher;
import cn.kgc.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {
	
	TeacherDao dao=new TeacherDaoImpl();

	@Override
	public int saveTeacher(Teacher teacher) {
	
		return dao.saveTeacher(teacher);
	}

	@Override
	public int delTeacher(String teacherId) {
		
		return dao.delTeacher(teacherId);
	}

	@Override
	public int updateTeacher(Teacher teacher) {
		
		return dao.updateTeacher(teacher);
	}

	@Override
	public Teacher getTeacherById(String TeacherId) {
		
		return dao.getTeacherById(TeacherId);
	}

	@Override
	public List<Teacher> getTeacherList() {
		
		return dao.getTeacherList();
	}

	/*
	 * 分页带索引查询方法
	 */
	@Override
	public Page<Teacher> findTeacher(String pageNum, int pageSize, String teacherName,
			String jobNumber) {
		int pn=1;
		try {
			pn=Integer.parseInt(pageNum);
		} catch (Exception e) {
		}
				
		Page<Teacher> page = new Page<Teacher>();
		
		page.setPageNum(pn);
		page.setPageSize(pageSize);
		
		return dao.findTeacher(page,teacherName,jobNumber);
	}

	@Override
	public Page<Teacher> findAll(String pageNum, int pageSize) {
		int pn=1;
		try {
			pn=Integer.parseInt(pageNum);
		} catch (Exception e) {
		}
		
		Page<Teacher> page = new Page<Teacher>();
		
		page.setPageNum(pn);
		page.setPageSize(pageSize);
		
		return dao.findAll(page);
	}

	@Override
	public Teacher teahcerInfo(Teacher teacher) {
		
		return dao.teahcerInfo(teacher);
	}

}
