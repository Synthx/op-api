package com.yezebi.pinpin.op.controller;

import com.yezebi.pinpin.op.dto.request.SearchCardRequest;
import com.yezebi.pinpin.op.dto.response.CardResponse;
import com.yezebi.pinpin.op.dto.response.MinimalCardResponse;
import com.yezebi.pinpin.op.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public Page<MinimalCardResponse> findAll(final Pageable pageable, final SearchCardRequest request) {
        return cardService.findAll(pageable, request);
    }

    @Nullable
    @GetMapping("{id}")
    public CardResponse findById(@PathVariable final String id) {
        return cardService.findById(id);
    }
}
