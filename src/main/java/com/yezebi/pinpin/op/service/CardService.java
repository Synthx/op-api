package com.yezebi.pinpin.op.service;

import com.yezebi.pinpin.op.dao.CardRepository;
import com.yezebi.pinpin.op.dto.request.SearchCardRequest;
import com.yezebi.pinpin.op.dto.response.CardResponse;
import com.yezebi.pinpin.op.dto.response.MinimalCardResponse;
import com.yezebi.pinpin.op.entity.CardDocument;
import com.yezebi.pinpin.op.model.CardColor;
import com.yezebi.pinpin.op.model.CardColorAggregationResult;
import com.yezebi.pinpin.op.model.CardRarity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<CardColor, Long> getColorsMapping(final SearchCardRequest request) {
        final MatchOperation matchOperation = request.toMatchOperation();
        final UnwindOperation unwindOperation = Aggregation.unwind("colors");
        final GroupOperation groupOperation = Aggregation.group("colors").count().as("count");
        final ProjectionOperation projectionOperation = Aggregation.project().andExpression("_id").as("color").andInclude("count");

        final Aggregation aggregation = Aggregation.newAggregation(CardDocument.class, matchOperation, unwindOperation, groupOperation, projectionOperation);
        final AggregationResults<CardColorAggregationResult> results = mongoTemplate.aggregate(aggregation, CardDocument.class, CardColorAggregationResult.class);

        return results.getMappedResults().stream().collect(Collectors.toMap(CardColorAggregationResult::color, CardColorAggregationResult::count));
    }

    @Nullable
    public CardResponse findById(final String id) {
        return cardRepository.findById(id).map(CardResponse::from).orElse(null);
    }
}
