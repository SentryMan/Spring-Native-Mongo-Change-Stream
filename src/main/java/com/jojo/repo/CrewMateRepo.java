package com.jojo.repo;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.jojo.domain.CrewMate;

@Repository
public interface CrewMateRepo extends ReactiveMongoRepository<CrewMate, BigInteger> {}
