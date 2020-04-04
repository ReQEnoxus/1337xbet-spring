package com.enoxus.xbetspring.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentCreationDto {
    private Long matchId;
    private Long parentId;
    private String text;
}
