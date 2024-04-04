package com.example.testcollection;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/*
 * 스프링 부트에서 몽고디비를 연동할 때 MongoRepository와 MongoTemplate는 각각 다른 방식으로 데이터베이스와 상호작용합니다.

1. **MongoRepository:**
   - MongoRepository는 Spring Data MongoDB 모듈에서 제공하는 인터페이스입니다.
   - Spring Data MongoDB는 데이터베이스와의 상호작용을 위한 고수준 추상화를 제공합니다.
   - MongoRepository를 사용하면 데이터베이스 작업에 필요한 CRUD(Create, Read, Update, Delete) 메서드를 
   		직접 구현할 필요 없이 인터페이스를 확장하여 사용할 수 있습니다.
   - 예를 들어, `CrudRepository`나 `PagingAndSortingRepository` 인터페이스를 상속하여 
   		Repository 인터페이스를 정의하고, 해당 인터페이스를 구현하면 데이터베이스에 대한 CRUD 작업을 수행할 수 있습니다.

2. **MongoTemplate:**
   - MongoTemplate은 몽고디비에 대한 저수준의 접근을 제공하는 클래스입니다.
   - 이 클래스를 사용하면 직접 쿼리를 작성하여 데이터베이스와 상호작용할 수 있습니다.
   - 데이터베이스에 대한 세부적인 제어가 필요할 때 주로 사용됩니다. 예를 들어, 복잡한 질의를 수행하거나 
   		직접 쿼리를 작성해야 하는 경우에 유용합니다.
   - MongoTemplate을 사용하면 몽고디비의 모든 기능을 활용할 수 있지만, 직접 쿼리를 작성해야 하므로 
   		개발자가 쿼리를 작성하는 부분에 대한 책임이 있습니다.

	따라서 MongoRepository는 데이터베이스 작업을 추상화하고 간단하게 사용할 수 있도록 도와주는 반면, 
	MongoTemplate은 좀 더 저수준의 접근을 제공하여 개발자가 직접 데이터베이스에 대한 제어를 할 수 있습니다. 
	선택은 프로젝트의 요구사항과 개발자의 선호도에 따라 달라집니다.
 * */
@Slf4j
@Service
public class MemoService {

	@Autowired
	MemoDAO dao;

	@Autowired
	MemoMongoRepository mongoRepo;

	public MemoService() {
		log.info("MemoService()...");
	}

	public List<MemoVO> findAll() {
//		return mongoRepo.findAll();//내장메소드,정렬없음.
		return mongoRepo.findAll(Sort.by(Sort.Direction.DESC, "age"));// 내장메소드
	}

	public List<MemoVO> findAll(int sort, int limit) {
		return mongoRepo.findAllAggregation(sort, limit);// 사용자 정의한 추상메소드로 구현 @Aggregation
	}

	public long findAllCount() {
		return mongoRepo.count();// 내장메소드
	}

	public Page<MemoVO> findAllPageLimit(int page, int limit) {
		// 주의:PageRequest에서 page=0 설정이 1페이지를 의미,따라서 -1처리
		PageRequest pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "age"));

		return mongoRepo.findAllByOrderByAgeDesc(pageable);// 네이밍룰
//		return mongoRepo.findAllByOrderByNameDesc(pageable);//네이밍룰
	}

	public List<MemoVO> searchList(String searchKey, String searchWord) {

		if (searchKey.equals("name")) {
			return mongoRepo.findByNameLike(searchWord); // 네이밍룰
		} else {
			return mongoRepo.findByPhoneLike(searchWord); // 네이밍룰
		}
	}

	public Page<MemoVO> searchListPageLimit(String searchKey, String searchWord, int page, int limit) {
		// 주의:PageRequest에서 page=0 설정이 1페이지를 의미,따라서 -1처리
		PageRequest pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "age"));
		if (searchKey.equals("name")) {
			return mongoRepo.findByNameContaining(searchWord, pageable); // 네이밍룰
		} else {
			return mongoRepo.findByPhoneContaining(searchWord, pageable); // 네이밍룰
		}
	}

	public List<MemoVO> searchListAndOrIn(String filter, int age1, int age2) {
		if (filter.equals("gtlt")) {// 크다작다
			return mongoRepo.findByAgeBetween(age1, age2); // 네이밍룰
		} else if (filter.equals("gtelte")) {// 이상이하
			return mongoRepo.findByAgeBetweenThen(age1, age2); // 사용자 정의
		} else {// 같은것이 여러개
			return mongoRepo.findByAgeOrIn(age1, age2); // 사용자 정의
		}
	}

	public MemoVO findOne(MemoVO vo) {
		if (vo.getMid() == null) {
			return mongoRepo.findByAge(vo.getAge()); // 네이밍룰
		} else {
			return mongoRepo.findBy_id(new ObjectId(vo.getMid())); // 네이밍룰
		}
	}

	public MemoVO insertOne(MemoVO vo) {
		// 주의 : 처리문이 save메소드 하나만 가지고 입력과 수정을 같이처리한다._id가 있으면 수정,없으면 입력
		return mongoRepo.save(vo);
	}

	public long insertMany() {
		List<MemoVO> vos = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			MemoVO vo = new MemoVO();
			vo.setAge(100+(i+1));
			vo.setName("yang"+(i+1));
			vo.setOffice("multi"+(i+1));
			vo.setPhone("011"+(i+1));
			vos.add(vo);
		}
		return mongoRepo.saveAll(vos).size();
	}

	public MemoVO updateOne(MemoVO vo) {
		// 주의 : 처리문이 save메소드 하나만 가지고 입력과 수정을 같이처리한다._id가 있으면 수정,없으면 입력
		vo.set_id(vo.getMid());
		return mongoRepo.save(vo);
	}

	
	//한꺼번에 여러개 수정하는 방법은 기존에 사용하던 mongoTemplate을 사용하는것이 일반적이다.
	public long updateMany(MemoVO vo) {
		return dao.updateMany(vo);
	}

	public long deleteOne(MemoVO vo) {
		if (vo.getMid() == null) {
			mongoRepo.deleteByAge(vo.getAge());// 네이밍룰
		} else {
			vo.set_id(vo.getMid());
			mongoRepo.delete(vo); // 내장 메소드
		}
		return 1;
	}

	public long deleteMany(MemoVO vo) {
		return mongoRepo.deleteByGteAge(vo.getAge());
	}

}
