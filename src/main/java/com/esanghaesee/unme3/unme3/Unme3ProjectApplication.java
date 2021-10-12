package com.esanghaesee.unme3.unme3;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@SpringBootApplication
public class Unme3ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Unme3ProjectApplication.class, args);
	}

	//빈으로 jpqQueryFactory생성, -> repository에서 @RequriedArgsConstrouctorh로 주입받으면 됨
	//   private final EntityManager em;
	//    private final JPAQueryFactory queryFactory;
	//같이 적어주고

	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}


}
