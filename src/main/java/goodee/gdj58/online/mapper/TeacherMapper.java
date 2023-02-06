package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;

@Mapper
public interface TeacherMapper {
	int insertExample (Example example);
	int insertQuestion(Question question);
	int updateTest(Map<String, Object> paramMap);
	int insertTest(Test test);
	int lastPage(String searchWord);
	int updateTeacherPw(Map<String, Object> paramMap);
	Teacher login(Teacher teacher);
	int deleteTeacher(int teacherNo);
	int insertTeacher(Teacher teacher);	
	List<Teacher> selectTeacherList(Map<String, Object> paramMap);
}
