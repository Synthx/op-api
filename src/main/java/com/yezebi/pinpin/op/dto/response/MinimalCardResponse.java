package com.yezebi.pinpin.op.dto.response;

import com.yezebi.pinpin.op.entity.CardDocument;
import lombok.Builder;

@Builder
public record MinimalCardResponse(
        String id,
        String code,
        String name,
        String imageUrl
) {
    public static MinimalCardResponse from(final CardDocument document) {
        return MinimalCardResponse.builder()
                .id(document.getId())
                .code(document.getCode())
                .name(document.getName())
                .imageUrl(document.getImageUrl())
                .build();
    }
}
