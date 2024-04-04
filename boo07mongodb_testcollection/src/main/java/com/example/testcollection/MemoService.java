package com.example.testcollection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemoService {
	
	@Autowired
	MemoDAO dao;
	
	public MemoService() {
		log.info("MemoSerivce()......");
	}

	public MemoVO insertOne(MemoVO vo) {
		// TODO Auto-generated method stub
		return dao.insertOne(vo);
	}

	public long insertMany() {
		// TODO Auto-generated method stub
		return dao.insertMany();
	}

	public List<MemoVO> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	public long updateOne(MemoVO vo) {
		// TODO Auto-generated method stub
		return dao.updateOne(vo);
	}

	


}
