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
	List<Map<String, Object>> selectQuestionOne(int testNo);	
	List<Map<String, Object>> seletExampleList(int tetNo);
	List<Question> selectQuestionList(int testNo);
	List<Example> selectExList(int questionNo);
	int cntTest (String searchWord);
	List<Test> selectTestList(Map<String, Object> paramMap);
	Test testOne(int testNo);
	int lastQuestion();
}
