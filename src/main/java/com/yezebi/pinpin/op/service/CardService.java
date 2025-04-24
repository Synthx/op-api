package com.yezebi.pinpin.op.service;

import com.yezebi.pinpin.op.dao.CardRepository;
import com.yezebi.pinpin.op.dto.request.SearchCardRequest;
import com.yezebi.pinpin.op.dto.response.CardResponse;
import com.yezebi.pinpin.op.dto.response.MinimalCardResponse;
import com.yezebi.pinpin.op.entity.CardDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final MongoTemplate mongoTemplate;

    public Page<MinimalCardResponse> findAll(final Pageable pageable, final SearchCardRequest request) {
        final Query query = request.toQuery().with(pageable);
        final List<CardDocument> cardDocuments = mongoTemplate.find(query, CardDocument.class);
        final long total = mongoTemplate.count(query, CardDocument.class);

        return new PageImpl<>(cardDocuments, pageable, total).map(MinimalCardResponse::from);
    }

    @Nullable
    public CardResponse findById(final String id) {
        return cardRepository.findById(id).map(CardResponse::from).orElse(null);
    }
}
