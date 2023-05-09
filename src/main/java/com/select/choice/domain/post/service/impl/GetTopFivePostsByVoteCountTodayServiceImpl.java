package com.select.choice.domain.post.service.impl;

import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.presentation.data.dto.TodayPostDto;
import com.select.choice.domain.post.presentation.data.dto.TodayPostListDto;
import com.select.choice.domain.post.service.GetTopFivePostsByVoteCountTodayService;
import com.select.choice.domain.post.util.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetTopFivePostsByVoteCountTodayServiceImpl implements GetTopFivePostsByVoteCountTodayService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;

    @Override
    public TodayPostListDto getTopFivePostsByVoteCountTodayService() {
        String today = LocalDate.now().toString();
        List<TodayPostDto> todayPosts = postRepository.findAllByCreatedAtContaining(today).stream()
                .map(postConverter::toTodayPostDto)
                .sorted(Comparator.comparing(TodayPostDto::getParticipants).reversed()).collect(Collectors.toList());

        return postConverter.toTodayPostListDto(todayPosts);
    }
}
