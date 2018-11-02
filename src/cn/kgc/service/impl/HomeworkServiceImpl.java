package cn.kgc.service.impl;

import java.util.List;

import cn.kgc.dao.HomeworkDao;
import cn.kgc.dao.impl.HomeworkDaoImpl;
import cn.kgc.entity.Homework;
import cn.kgc.entity.HomeworkPage;
import cn.kgc.service.HomeworkService;

public class HomeworkServiceImpl implements HomeworkService {
    HomeworkDao homeworkDao=new HomeworkDaoImpl();
	@Override
	public int saveHomework(Homework homework) {
		// TODO Auto-generated method stub
		return homeworkDao.saveHomework(homework);
	}

	@Override
	public int delHomework(String id) {
		// TODO Auto-generated method stub
		return homeworkDao.delHomework(id);
	}

	@Override
	public int updateHomework(Homework homework) {
		// TODO Auto-generated method stub
		return homeworkDao.updateHomework(homework);
	}

	@Override
	public Homework getHomeworkById(String id) {
		// TODO Auto-generated method stub
		return homeworkDao.getHomeworkById(id);
	}


	@Override
	public HomeworkPage<Homework> findHomework(String pageNum, int pageSize) {
		// TODO Auto-generated method stub

		int pn=1;
		try {
			pn=Integer.parseInt(pageNum);
		} catch (Exception e) {
		}
		
		
		HomeworkPage<Homework> page = new HomeworkPage<Homework>();
		
		page.setPageNum(pn);
		page.setPageSize(pageSize);
		
		return homeworkDao.findHomework(page);
	}

	@Override
	public List<Homework> getHomeworkList(String studentName) {
		// TODO Auto-generated method stub
		return homeworkDao.getHomeworkListByStudentName(studentName);
	}

	@Override
	public List<Homework> getHomeworkList() {
		// TODO Auto-generated method stub
		return homeworkDao.getHomeworkListByStudentName();
	}

	@Override
	public HomeworkPage<Homework> lookingforHomework(String pageNum,
			int pageSize, String studentName, String HomeworkDate) {
		// TODO Auto-generated method stub
		int pn=1;
		try {
			pn=Integer.parseInt(pageNum);
		} catch (Exception e) {
		}
				
		HomeworkPage<Homework> page = new HomeworkPage<Homework>();
		
		page.setPageNum(pn);
		page.setPageSize(pageSize);
		
		return homeworkDao.lookingforHomework(page,studentName,HomeworkDate);
	}

	@Override
	public List<Homework> getHomeworkListByStudentName(String studentName) {
		// TODO Auto-generated method stub
		return homeworkDao.getHomeworkListByStudentName(studentName);
	}

}
