package com.example.testcollection;

import java.util.List;

public interface MemoDAO {

	public List<MemoVO> findAll();

	public MemoVO insertOne(MemoVO vo);

	public long insertMany();

	public MemoVO updateOne();


}
