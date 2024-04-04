package com.example.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TestDAOimpl implements TestDAO {

	@Override
	public int insert(TestVO vo) {
		log.info("insert()...");
		log.info("{}", vo);
		return 0;
	}

	@Override
	public int update(TestVO vo) {
		log.info("update()...");
		log.info("{}", vo);
		return 0;
	}

	@Override
	public int delete(TestVO vo) {
		log.info("delete()...");
		log.info("{}", vo);
		return 0;
	}

	@Override
	public int getTotalRows() {
		log.info("getTotalRows()...1");
		return 0;
	}

	@Override
	public int getTotalRows(String searchKey, String searchWord) {
		log.info("getTotalRows()...2");
		log.info("{}", searchKey);
		log.info("{}", searchWord);
		return 0;
	}

	@Override
	public TestVO selectOne(TestVO vo) {
		log.info("selectOne()...");
		log.info("{}", vo);

		TestVO vo2 = new TestVO();
		vo2.setNum(11);
		vo2.setName("kim11");
		vo2.setAge(111);

		return vo2;
	}

	@Override
	public TestVO login(TestVO vo) {
		log.info("login()...");
		log.info("{}", vo);
		TestVO vo2 = new TestVO();
		vo2.setNum(11);
		vo2.setName("kim11");
		vo2.setAge(111);

		return vo2;
	}

	@Override
	public TestVO idCheck(TestVO vo) {
		log.info("idCheck()...");
		log.info("{}", vo);
		TestVO vo2 = new TestVO();
		vo2.setNum(11);
		vo2.setName("kim11");
		vo2.setAge(111);

		return vo2;
	}

	@Override
	public List<TestVO> selectAll() {
		log.info("selectAll()...1");

		List<TestVO> vos = new ArrayList<>();

		TestVO vo2 = new TestVO();
		vo2.setNum(11);
		vo2.setName("kim11");
		vo2.setAge(111);

		vos.add(vo2);
		vos.add(vo2);
		vos.add(vo2);

		return vos;
	}

	@Override
	public List<TestVO> selectAll(int cpage, int pageBlock) {
		log.info("selectAll()...2");
		log.info("{}", cpage);
		log.info("{}", pageBlock);
		List<TestVO> vos = new ArrayList<>();

		TestVO vo2 = new TestVO();
		vo2.setNum(11);
		vo2.setName("kim11");
		vo2.setAge(111);

		vos.add(vo2);
		vos.add(vo2);
		vos.add(vo2);

		return vos;
	}

	@Override
	public List<TestVO> searchList(String searchKey, String searchWord) {
		log.info("searchList()...1");
		log.info("{}", searchKey);
		log.info("{}", searchWord);
		List<TestVO> vos = new ArrayList<>();

		TestVO vo2 = new TestVO();
		vo2.setNum(11);
		vo2.setName("kim11");
		vo2.setAge(111);

		vos.add(vo2);
		vos.add(vo2);
		vos.add(vo2);

		return vos;
	}

	@Override
	public List<TestVO> searchList(String searchKey, String searchWord, int cpage, int pageBlock) {
		log.info("searchList()...2");
		log.info("{}", searchKey);
		log.info("{}", searchWord);
		log.info("{}", cpage);
		log.info("{}", pageBlock);
		List<TestVO> vos = new ArrayList<>();

		TestVO vo2 = new TestVO();
		vo2.setNum(11);
		vo2.setName("kim11");
		vo2.setAge(111);

		vos.add(vo2);
		vos.add(vo2);
		vos.add(vo2);

		return vos;
	}

}
