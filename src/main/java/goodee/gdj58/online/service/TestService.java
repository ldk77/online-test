package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import goodee.gdj58.online.mapper.TestMapper;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Test;

@Service
@Transactional
public class TestService {
	@Autowired
	private TestMapper testMapper;
	
	//질문해당 보기출력
	public List<Example> getExList(int questionNo){
		return testMapper.selectExList(questionNo);
	}
	
	// 질문 출력
	public List<Question> getQuestionList(int testNo){
		return testMapper.selectQuestionList(testNo);
	}
	
	// 문제 출력
	public List<Map<String,Object>> getExampleList(int testNo) {
		return testMapper.seletExampleList(testNo);
	}
	// 시험 상세보기
	public Test testOne(int testNo) {
		return testMapper.testOne(testNo);
	}
	// 마지막문제
	public int getLastQuestion() {
		return testMapper.lastQuestion();
	}
	 
	public List<Map<String, Object>> getQuestionOne(int testNo){
		return testMapper.selectQuestionOne(testNo);
	}		
	
	public double cntTest(String searchWord) {
		return testMapper.cntTest(searchWord);
	}
	
	// 시험리스트
	public List<Test> getTestList(int currentPage, int rowPerPage, String searchWord){
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		return testMapper.selectTestList(paramMap);
	}
}
