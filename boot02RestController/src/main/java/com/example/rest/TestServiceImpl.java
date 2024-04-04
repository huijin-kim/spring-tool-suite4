package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDAO dao;

	@Override
	public int insert(TestVO vo) {
		return dao.insert(vo);
	}

	@Override
	public int update(TestVO vo) {
		return dao.update(vo);
	}

	@Override
	public int delete(TestVO vo) {
		return dao.delete(vo);
	}

	@Override
	public int getTotalRows() {

		return dao.getTotalRows();
	}

	@Override
	public int getTotalRows(String searchKey, String searchWord) {
		return dao.getTotalRows(searchKey, searchWord);
	}

	@Override
	public TestVO selectOne(TestVO vo) {
		return dao.selectOne(vo);
	}

	@Override
	public TestVO login(TestVO vo) {
		return dao.login(vo);
	}

	@Override
	public TestVO idCheck(TestVO vo) {
		return dao.idCheck(vo);
	}

	@Override
	public List<TestVO> selectAll() {
		return dao.selectAll();
	}

	@Override
	public List<TestVO> selectAll(int cpage, int pageBlock) {
		return dao.selectAll(cpage, pageBlock);
	}

	@Override
	public List<TestVO> searchList(String searchKey, String searchWord) {
		return dao.searchList(searchKey, searchWord);
	}

	@Override
	public List<TestVO> searchList(String searchKey, String searchWord, int cpage, int pageBlock) {
		return dao.searchList(searchKey, searchWord, cpage, pageBlock);
	}

}
