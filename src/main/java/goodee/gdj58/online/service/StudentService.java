package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.StudentMapper;
import goodee.gdj58.online.vo.Paper;
import goodee.gdj58.online.vo.Student;






@Service
@Transactional
public class StudentService {
	@Autowired
	private StudentMapper studentMapper;
	
	public int updateStudentPw(int studentNo, String oldPw, String newPw) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("studentNo", studentNo);
		paramMap.put("oldPw", oldPw);
		paramMap.put("newPw", newPw);
		return studentMapper.updateStudentPw(paramMap);
	}
	
	// 테스트별 정답개수
	public int answerCnt(int testNo) {
		return studentMapper.answerCnt(testNo);
	}
	
	// 답안과 정답비교용
	public int getQuestionOx(int questionNo) {
		return studentMapper.questionOx(questionNo);
	}
	// 답안등록
	public int addPaper(Paper paper) {
		return studentMapper.insertPaper(paper);
	}
	
	public Student login(Student student) {
		return studentMapper.login(student);
	}
	
	public int removeStudent(int studentNo) {
		return studentMapper.deleteStudent(studentNo);
	}
	
	public int addStudent(Student student) {
		return studentMapper.insertStudent(student);
	}
	
	public double lastPage(String searchWord) {
		return studentMapper.lastPage(searchWord);
	}
	
	public List<Student> getStudentList(int currentPage, int rowPerPage, String searchWord){
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		return studentMapper.selectStudentList(paramMap);
	}

	
}
