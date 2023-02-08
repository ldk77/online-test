package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Paper;
import goodee.gdj58.online.vo.Student;


@Mapper
public interface StudentMapper {
	int answerCnt(int testNo); //정답갯수 출력
	int questionOx(int questionNo); // 질문 정답출력
	int insertPaper(Paper paper); // 답안지 작성
	int lastPage(String searchWord);
	int updateStudentPw(Map<String, Object> paramMap);
	Student login(Student student);
	int deleteStudent(int studentNo);
	int insertStudent(Student student);	
	List<Student> selectStudentList(Map<String, Object> paramMap);
}
