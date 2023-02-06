package goodee.gdj58.online.mapper;

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
	List<Example> seletExampleList(Map<String, Object> paramMap);
	List<Question> seletQuestionList(Map<String, Object> paramMap);
	int cntTest (String searchWord);
	List<Test> selectTestList(Map<String, Object> paramMap);
}
