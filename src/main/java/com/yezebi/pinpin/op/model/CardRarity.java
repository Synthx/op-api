package com.yezebi.pinpin.op.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardRarity {
    COMMON("C"),
    UNCOMMON("UC"),
    RARE("R"),
    SUPER_RARE("SR"),
    SPECIAL_RARE("SP"),
    SECRET_RARE("SEC"),
    LEADER("L"),
    PROMO("P"),
    UNKNOWN("U");

    @JsonValue
    private final String value;
}
