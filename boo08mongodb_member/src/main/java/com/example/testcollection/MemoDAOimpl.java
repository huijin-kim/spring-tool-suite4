package com.example.testcollection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MemoDAOimpl implements MemoDAO {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public MemoDAOimpl() {
		log.info("MemoDAO()...");
	}
	

	@Override
	public List<MemoVO> findAll() {
		log.info("findAll()...");
		
		BasicQuery query = new BasicQuery("()");
		
		return mongoTemplate.find(query, MemoVO.class);
	}

	@Override
	public MemoVO insertOne(MemoVO vo) {
		log.info("insertOne()....");
		log.info("{}", vo);
		
		return mongoTemplate.insert(vo);
	}

	@Override
	public long insertMany() {
		log.info("insertMany()....");
		
		List<MemoVO> vos = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			MemoVO vo = new MemoVO();
			vo.setAge(100 + (i+1));
			vo.setName("yang" +(i+1));
			vo.setOffice("multi" +(i+1));
			vo.setPhone("010"+(i+1));
			vos.add(vo);
			
		}
		
		//int 이지만 자동으로 long 캐스팅해준다.
		return mongoTemplate.insert(vos, MemoVO.class).size();
	}


}
