package com.yezebi.pinpin.op.dao;

import com.yezebi.pinpin.op.entity.SetDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends MongoRepository<SetDocument, String> {
}
