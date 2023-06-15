package com.select.choice.domain.post.service.impl;

import com.select.choice.domain.post.domain.entity.Declaration;
import com.select.choice.domain.post.domain.entity.Post;
import com.select.choice.domain.post.domain.repository.DeclarationRepository;
import com.select.choice.domain.post.domain.repository.PostRepository;
import com.select.choice.domain.post.exception.AlreadyReportPostException;
import com.select.choice.domain.post.exception.PostNotFoundException;
import com.select.choice.domain.post.service.ReportPostService;
import com.select.choice.domain.post.util.PostConverter;
import com.select.choice.domain.user.domain.entity.User;
import com.select.choice.domain.user.util.UserUtil;
import com.select.choice.global.error.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportPostServiceImpl implements ReportPostService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final UserUtil userUtil;
    private final DeclarationRepository declarationRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void report(Long postIdx) {
        User user = userUtil.currentUser();
        Post post = postRepository.findById(postIdx)
                .orElseThrow(() -> new PostNotFoundException(ErrorCode.POST_NOT_FOUND));
        if(declarationRepository.existsByPostAndUser(post, user)){
            throw new AlreadyReportPostException(ErrorCode.ALREADY_REPORT_POST);
        }

        Declaration declaration = postConverter.toDeclarationEntity(user, post);
        declarationRepository.save(declaration);
        post.updateDeclarationCount();
    }
}

