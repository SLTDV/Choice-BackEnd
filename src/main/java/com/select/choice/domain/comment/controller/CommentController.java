package com.select.choice.domain.comment.controller;

import com.select.choice.domain.comment.data.dto.CommentDto;
import com.select.choice.domain.comment.data.request.EditCommentRequest;
import com.select.choice.domain.comment.data.request.WriteCommentRequest;
import com.select.choice.domain.comment.service.CommentService;
import com.select.choice.domain.comment.util.CommentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final CommentConverter commentConverter;

    @PostMapping("/{postIdx}")
    public ResponseEntity<Void> write(@PathVariable("postIdx") Long postIdx, @RequestBody WriteCommentRequest writeCommentRequest){
        CommentDto commentDto = commentConverter.toDto(writeCommentRequest.getContent());
        commentService.write(postIdx, commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{commentIdx}")
    public ResponseEntity<Void> edit(@PathVariable("commentIdx") Long commentIdx, @RequestBody EditCommentRequest editCommentRequest){
        CommentDto commentDto = commentConverter.toDto(editCommentRequest.getContent());
        commentService.edit(commentIdx, commentDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{commentIdx}")
    public ResponseEntity<Void> delete(@PathVariable("commentIdx") Long commentIdx){
        commentService.delete(commentIdx);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
