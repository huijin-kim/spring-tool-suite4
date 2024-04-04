package com.example.testcollection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MemoDAOimpl implements MemoDAO {

	@Autowired
	private MongoTemplate mongoTemplate;

	public MemoDAOimpl() {
		log.info("MemoDAOimpl()...");
	}

	@Override
	public long updateMany(MemoVO vo) {
		log.info("updateMany()....");
		log.info("{}",vo);
		
		//filter : where age >=101
		String filter = String.format("{age:{$gte:%d}}", vo.getAge());
		BasicQuery query = new BasicQuery(filter);
		
		Update update = new Update();
		update.set("name", vo.getName());
		update.set("office", vo.getOffice());
		update.set("phone", vo.getPhone());
		return mongoTemplate.updateMulti(query, update, MemoVO.class).getModifiedCount();
	}

}
