package com.yezebi.pinpin.op.entity;

import com.yezebi.pinpin.op.model.CardCategory;
import com.yezebi.pinpin.op.model.CardColor;
import com.yezebi.pinpin.op.model.CardRarity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "cards")
public class CardDocument {
    @Id
    private String internalId;
    @Field("id")
    private String id;
    private String code;
    private String name;
    private String imageUrl;
    private CardCategory category;
    private CardRarity rarity;
    private List<CardColor> colors;
    private List<String> effect;
    private List<String> types;
    @Nullable
    private Long cost;
    @Nullable
    private Long counter;
    @Nullable
    private Long power;
    @Nullable
    private String trigger;
}
