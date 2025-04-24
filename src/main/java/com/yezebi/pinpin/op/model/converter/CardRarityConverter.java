package com.yezebi.pinpin.op.model.converter;

import com.yezebi.pinpin.op.model.CardRarity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.Arrays;

@ReadingConverter
public class CardRarityConverter implements Converter<String, CardRarity> {
    @Override
    public CardRarity convert(final String source) {
        return Arrays.stream(CardRarity.values()).filter(v -> StringUtils.equalsIgnoreCase(v.getValue(), source)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
