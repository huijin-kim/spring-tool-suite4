package com.example.testcollection;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MemoMongoRepository extends MongoRepository<MemoVO, String> {

	// 네이밍 룰이 없이 메소드이름을 자유롭게 구현
	@Aggregation({ "{$sort:{age:?0}}", "{$limit:?1}" })
	List<MemoVO> findAllAggregation(int sort, int limit);

	// 네이밍 룰에 맞춰서 구현
	Page<MemoVO> findAllByOrderByAgeDesc(PageRequest pageable);

	Page<MemoVO> findAllByOrderByNameDesc(PageRequest pageable);

	// 네이밍 룰에 맞춰서 구현
	List<MemoVO> findByNameLike(String searchWord);

	List<MemoVO> findByPhoneLike(String searchWord);

	// 네이밍 룰에 맞춰서 구현
	Page<MemoVO> findByNameContaining(String searchWord, PageRequest pageable);

	Page<MemoVO> findByPhoneContaining(String searchWord, PageRequest pageable);

	// 네이밍 룰에 맞춰서 구현
	List<MemoVO> findByAgeBetween(int age1, int age2); // age>age1 and age<age2

	// 네이밍 룰이 없이 메소드이름을 자유롭게 구현 : @Query 사용
	@Query(value = "{age : {$gte:?0 , $lte:?1}}")
	List<MemoVO> findByAgeBetweenThen(int age1, int age2);

	// 네이밍 룰이 없이 메소드이름을 자유롭게 구현 : @Query 사용
	@Query(value = "{age : {$in : [?0,?1]}}")
	List<MemoVO> findByAgeOrIn(int age1, int age2);

	
	// 네이밍 룰에 맞춰서 구현
	MemoVO findByAge(int age);

	// 네이밍 룰에 맞춰서 구현
	MemoVO findBy_id(ObjectId objectId);

	// 네이밍 룰에 맞춰서 구현
	void deleteByAge(int age);

	// 네이밍 룰이 없이 메소드이름을 자유롭게 구현 : @Query 사용
	@Query(value = "{age : {$gte : ?0}}",delete = true)
	long deleteByGteAge(int age);
	
	
	

}
