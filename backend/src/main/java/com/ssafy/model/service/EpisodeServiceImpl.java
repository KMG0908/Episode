package com.ssafy.model.service;

import com.ssafy.model.dto.episode.EpisodeResponseDto;
import com.ssafy.model.dto.episode.EpisodeResponseNoNovelDto;
import com.ssafy.model.dto.episode.EpisodeSaveRequestDto;
import com.ssafy.model.dto.episode.EpisodeUpdateRequestDto;
import com.ssafy.model.dto.novel.NovelResponseDto;
import com.ssafy.model.entity.Episode;
import com.ssafy.model.entity.EpisodeException;
import com.ssafy.model.entity.Novel;
import com.ssafy.model.entity.NovelException;
import com.ssafy.model.repository.CommentRepository;
import com.ssafy.model.repository.EpisodeRepository;
import com.ssafy.model.repository.NovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EpisodeServiceImpl implements EpisodeService {

    @Autowired
    NovelRepository nRepo;
    @Autowired
    EpisodeRepository eRepo;
    @Autowired
    CommentRepository cRepo;

    @Transactional
    @Override
    public EpisodeResponseDto registEpisode(EpisodeSaveRequestDto requestDto) {
        Novel novel = nRepo.findById(requestDto.getNovelPk())
                .orElseThrow(() -> new NovelException(NovelException.NOT_EXIST));

        Episode episode = requestDto.toEntity(novel);
        episode.getNovel().updateUpdatedAt(); // novel updatedAt 갱신
        novel.updateUpdatedAt();

        episode = eRepo.save(episode);
        nRepo.save(novel);

        EpisodeResponseDto responseDto = new EpisodeResponseDto(episode);
        return responseDto;
    }

    @Override
    public Page<EpisodeResponseDto> getEpisodes(Pageable pageable) {
        Page<Episode> episodeEntityPage = eRepo.findAll(pageable);
        Page<EpisodeResponseDto> episodes =
                episodeEntityPage.map(episodeEntity -> new EpisodeResponseDto(episodeEntity));
        return episodes;
    }

    @Transactional
    @Override
    public EpisodeResponseDto getEpisode(int episodePk) {    // 조회수 + 1
        Episode episode = eRepo.findById(episodePk).orElseThrow(() ->
                new EpisodeException(EpisodeException.NOT_EXIST));

        episode.updateView();
        episode.getNovel().updateView();
        eRepo.save(episode);

        EpisodeResponseDto responseDto = new EpisodeResponseDto(episode);
        return responseDto;
    }

    @Override
    public EpisodeResponseDto updateEpisode(int episodePk, EpisodeUpdateRequestDto requestDto) {
        Episode episodeEntity = eRepo.findById(episodePk).orElseThrow(() ->
                new EpisodeException(EpisodeException.NOT_EXIST));

        episodeEntity.update(
                requestDto.getEpisodeTitle(),
                requestDto.getEpisodeContent(),
                requestDto.getEpisodeWriter()
        );

        EpisodeResponseDto episode = new EpisodeResponseDto(eRepo.save(episodeEntity));
        return episode;
    }

    @Transactional
    @Override
    public void deleteEpisode(int episodePk) {
        Episode episode = eRepo.findById(episodePk).orElseThrow(() ->
                new EpisodeException(EpisodeException.NOT_EXIST));
        deleteEpisode(episode);
    }

    @Override
    public Map getEpisodesByNovel(int novelPk, Pageable pageable) {
        Map data = new HashMap();

        Novel novelEntity = nRepo.findById(novelPk).orElseThrow(() ->
                new EpisodeException(EpisodeException.NOT_EXIST));
        NovelResponseDto novel = new NovelResponseDto(novelEntity);

        Page<Episode> episodeEntityPage = eRepo.findByNovel(novelEntity, pageable);
        Page<EpisodeResponseNoNovelDto> episodes =
                episodeEntityPage.map(episodeEntity -> new EpisodeResponseNoNovelDto(episodeEntity));

        data.put("novel", novel);
        data.put("episodes", episodes);
        return data;
    }

    @Transactional
    public void deleteEpisode(Episode episode){
        episode.beforeDelete();
        eRepo.save(episode);
        eRepo.delete(episode);
    }

    public void deleteAllEpisode(){
        List<Episode> episodeList = eRepo.findAll();
        episodeList.stream().forEach(episode -> deleteEpisode(episode));
    }
}