package com.select.choice.domain.comment.presentation;

import com.select.choice.domain.comment.presentation.data.dto.CommentDto;
import com.select.choice.domain.comment.presentation.data.request.EditCommentRequest;
import com.select.choice.domain.comment.presentation.data.request.WriteCommentRequest;
import com.select.choice.domain.comment.service.DeleteCommentService;
import com.select.choice.domain.comment.service.EditCommentService;
import com.select.choice.domain.comment.service.WriteCommentService;
import com.select.choice.domain.comment.util.CommentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final WriteCommentService writeCommentService;
    private final EditCommentService editCommentService;
    private final DeleteCommentService deleteCommentService;
    private final CommentConverter commentConverter;

    /*
    기능: 댓글 작성
    담당자: 노혁
     */
    @PostMapping("/{postIdx}")
    public ResponseEntity<Void> write(@PathVariable("postIdx") Long postIdx, @RequestBody WriteCommentRequest writeCommentRequest){
        CommentDto commentDto = commentConverter.toDto(writeCommentRequest);
        writeCommentService.writeComment(postIdx, commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    기능: 댓글 수정
    담당자: 노혁
     */
    @PatchMapping("/{postIdx}/{commentIdx}")
    public ResponseEntity<Void> edit(@PathVariable("postIdx") Long postIdx, @PathVariable("commentIdx") Long commentIdx, @RequestBody EditCommentRequest editCommentRequest){
        CommentDto commentDto = commentConverter.toDto(editCommentRequest);
        editCommentService.editComment(commentIdx, commentDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    기능: 댓글 삭제
    담당자: 노혁
     */
    @DeleteMapping("/{postIdx}/{commentIdx}")
    public ResponseEntity<Void> delete(@PathVariable("postIdx") Long postIdx, @PathVariable("commentIdx") Long commentIdx){
        deleteCommentService.deleteComment(commentIdx);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
