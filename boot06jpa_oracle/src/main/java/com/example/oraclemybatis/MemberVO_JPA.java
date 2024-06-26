package com.example.oraclemybatis;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

//@Entity : 자바의 객체와 DB테이블을 매칭시켜주는 기능
//@Table : DB에 테이블을 정의 해준다.

@Data
@Entity
@Table(name="MEMBER_JPA",uniqueConstraints = {
		@UniqueConstraint(
			columnNames= {"user_id"}
		)
})
public class MemberVO_JPA {

	@Id  //pk설정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_member_jpa")
	@SequenceGenerator(sequenceName = "seq_member_jpa",  allocationSize = 1,   name = "seq_member_jpa")// 시퀀스 자동생성된다.
	@Column(name="num")//컬럼이름 설정
	private int num;
	
	@Column(name="user_id",nullable = false)
	private String id;
	
	@Column(name="user_pw",nullable = false)
	private String pw;
	
	@Column(name="user_name",nullable = false)
	private String name;
	
	@Column(name="user_tel",nullable = false)
	private String tel;
	
	//날짜타입의 기본은 타임스템프
	@Temporal(TemporalType.TIMESTAMP)//연월일 시분초 밀리초
//	@Temporal(TemporalType.DATE)//연월일
	@Column(name="regdate",insertable = false) //입력시 sysdate으로 처리,수정시 널값반영(이럴때는 new Date()처리해준다.)
	@ColumnDefault(value="sysdate")
	private Date regdate;
	
}
