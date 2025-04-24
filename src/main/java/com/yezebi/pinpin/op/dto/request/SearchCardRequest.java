package com.yezebi.pinpin.op.dto.request;

import com.yezebi.pinpin.op.model.CardColor;
import com.yezebi.pinpin.op.model.CardRarity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.lang.Nullable;

import java.util.List;


public record SearchCardRequest(
        @Nullable List<CardColor> colors,
        @Nullable List<CardRarity> rarities
) {
    public Query toQuery() {
        Query query = new Query();
        if (ObjectUtils.isNotEmpty(colors)) {
            final Criteria colorCriteria = Criteria.where("colors").in(colors);

            query = query.addCriteria(colorCriteria);
        }
        if (ObjectUtils.isNotEmpty(rarities)) {
            final List<String> values = rarities.stream().map(CardRarity::getValue).toList();
            final Criteria rarityCriteria = Criteria.where("rarity").in(values);

            query = query.addCriteria(rarityCriteria);
        }

        return query;
    }
}
