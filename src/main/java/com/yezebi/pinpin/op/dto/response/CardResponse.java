package com.yezebi.pinpin.op.dto.response;

import com.yezebi.pinpin.op.entity.CardDocument;
import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record CardResponse(
        String id,
        String code,
        String name,
        String imageUrl,
        @Nullable Long power
) {
    public static CardResponse from(final CardDocument document) {
        return CardResponse.builder()
                .id(document.getId())
                .code(document.getCode())
                .name(document.getName())
                .imageUrl(document.getImageUrl())
                .build();
    }
}
