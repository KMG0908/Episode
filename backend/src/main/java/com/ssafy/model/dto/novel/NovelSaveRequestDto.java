package com.ssafy.model.dto.novel;

import com.ssafy.model.entity.Member;
import com.ssafy.model.entity.Novel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NovelSaveRequestDto{
    private int memberPk;
    private String novelName;
    private String novelIntro;
    private String novelImage;
    private boolean novelLimit;
    private boolean novelOpen;
    private int novelStatus;
    private boolean novelOnly;
    private List<Integer> genres;
    private List<String> hashTags;

    @Builder
    public NovelSaveRequestDto(
            int memberPk,
            String novelName,
            String novelIntro,
            String novelImage,
            boolean novelLimit,
            boolean novelOpen,
            int novelStatus,
            boolean novelOnly) {

        this.memberPk = memberPk;
        this.novelName = novelName;
        this.novelIntro = novelIntro;
        this.novelImage = novelImage;
        this.novelLimit = novelLimit;
        this.novelOpen = novelOpen;
        this.novelStatus = novelStatus;
        this.novelOnly = novelOnly;
    }

    public Novel toEntity(Member member){
        return Novel.builder()
                .member(member)
                .novelName(novelName)
                .novelIntro(novelIntro)
                .novelImage(novelImage)
                .novelLimit(novelLimit)
                .novelOpen(novelOpen)
                .novelStatus(novelStatus)
                .novelOnly(novelOnly)
                .build();
    }
}