package goodee.gdj58.online.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Test;

@Mapper
public interface TestMapper {
	ArrayList<Question> selectQuestionList(int testNo);
	List<Map<String, Object>> selectQuestionOne(int testNo);	
	List<Map<String, Object>> seletExampleList(int testNo);
	List<Question> seletQuestionList(Map<String, Object> paramMap);
	int cntTest (String searchWord);
	List<Test> selectTestList(Map<String, Object> paramMap);
	Test testOne(int testNo);
	int lastQuestion();
}
