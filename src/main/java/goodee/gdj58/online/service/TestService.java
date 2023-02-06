package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.TeacherMapper;
import goodee.gdj58.online.mapper.TestMapper;
import goodee.gdj58.online.vo.Test;

@Service
@Transactional
public class TestService {
	@Autowired
	private TestMapper testMapper;
	
	
	public List<Map<String, Object>> getQuestionOne(int testNo){
		return testMapper.selectQuestionOne(testNo);
	}		

	public double cntTest(String searchWord) {
		return testMapper.cntTest(searchWord);
	}
	
	public List<Test> getTestList(int currentPage, int rowPerPage, String searchWord){
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		return testMapper.selectTestList(paramMap);
	}
}
