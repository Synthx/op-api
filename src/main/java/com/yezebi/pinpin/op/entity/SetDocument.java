package com.yezebi.pinpin.op.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(collection = "sets")
public class SetDocument {
    private String id;
    private String name;
}
