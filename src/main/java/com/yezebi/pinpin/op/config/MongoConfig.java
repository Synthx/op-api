package com.yezebi.pinpin.op.config;

import com.yezebi.pinpin.op.model.converter.CardRarityConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfig {
    @Bean
    MongoCustomConversions mongoCustomConversions() {
        final List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new CardRarityConverter());

        return new MongoCustomConversions(converters);
    }
}
