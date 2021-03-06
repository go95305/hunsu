package com.project.hunsu.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class WearReplyDTO {
    private Long idx;
    private Long wear_idx;
    private Long uid;
    private String nickname;
    private String content;
    private Long depth;
    private LocalDateTime write_date;
    private Boolean like;
    private Long groupNum;
    private Integer count;
    private boolean flag;
}