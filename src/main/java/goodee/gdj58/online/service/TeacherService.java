package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import goodee.gdj58.online.mapper.TeacherMapper;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;


@Service
@Transactional
public class TeacherService {
	@Autowired
	private TeacherMapper teacherMapper;
	
	public int addExample(Example example) {
		return teacherMapper.insertExample(example);
	}
	
	public int addQuestion(Question question){
		return teacherMapper.insertQuestion(question);
	}
	
	public int updateTest(int testNo, String testTitle, String testDate) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("testNo", testNo);
		paramMap.put("testTitle", testTitle);
		paramMap.put("testDate", testDate);
		return teacherMapper.updateTest(paramMap);
	}
	
	public int addTest(Test test) {
		return teacherMapper.insertTest(test);
	}
	
	public int updateTeacherPw(int teacherNo, String oldPw, String newPw) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teacherNo", teacherNo);
		paramMap.put("oldPw", oldPw);
		paramMap.put("newPw", newPw);
		return teacherMapper.updateTeacherPw(paramMap);
	}
	
	public Teacher login(Teacher teacher) {
		return teacherMapper.login(teacher);
	}
	
	public int removeTeacher(int teacherNo) {
		return teacherMapper.deleteTeacher(teacherNo);
	}
	
	public int addTeacher(Teacher teacher) {
		return teacherMapper.insertTeacher(teacher);
	}
	
	public double lastPage(String searchWord) {
		return teacherMapper.lastPage(searchWord);
	}
	
	public List<Teacher> getTeacherList(int currentPage, int rowPerPage, String searchWord){
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		return teacherMapper.selectTeacherList(paramMap);
	}

	
}
