package com.saccess.newsservice.dto;

import java.util.List;

public record FullResponse(
        UserDto user,
        List<NewsDto> news
) {
}
