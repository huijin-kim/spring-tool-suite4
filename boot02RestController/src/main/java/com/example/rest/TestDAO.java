package com.example.rest;

import java.util.List;

public interface TestDAO {

	public int insert(TestVO vo);

	public int update(TestVO vo);

	public int delete(TestVO vo);

	public int getTotalRows();

	public int getTotalRows(String searchKey, String searchWord);

	public TestVO selectOne(TestVO vo);

	public TestVO login(TestVO vo);

	public TestVO idCheck(TestVO vo);

	public List<TestVO> selectAll();

	public List<TestVO> selectAll(int cpage, int pageBlock);

	public List<TestVO> searchList(String searchKey, String searchWord);

	public List<TestVO> searchList(String searchKey, String searchWord, int cpage, int pageBlock);

}
