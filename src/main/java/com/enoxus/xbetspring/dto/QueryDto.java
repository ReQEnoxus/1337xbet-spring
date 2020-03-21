package com.enoxus.xbetspring.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class QueryDto {
    int limit;
    int offset;
    String query;
    int date;
}
