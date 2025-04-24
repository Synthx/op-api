package com.yezebi.pinpin.op.dao;

import com.yezebi.pinpin.op.entity.CardDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends MongoRepository<CardDocument, String> {
}
