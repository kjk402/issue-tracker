package com.bas.issuetracker.web.controller;


import com.bas.issuetracker.web.config.annotation.CertifiedUser;
import com.bas.issuetracker.web.dto.comment.CommentRequest;
import com.bas.issuetracker.web.dto.comment.CommentUpdateRequest;
import com.bas.issuetracker.web.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Comment API"}, description = "코멘트 API")
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @ApiOperation(value = "코멘트 생성", notes = "새로운 코멘트를 생성합니다.")
    public void crateComment(@CertifiedUser int userId, @RequestBody CommentRequest commentRequest) {
        commentService.createComment(userId, commentRequest);
    }

    @PatchMapping
    @ApiOperation(value = "코멘트 업데이트", notes = "코멘트를 업데이트 합니다.")
    public void updateComment(@RequestBody CommentUpdateRequest commentUpdateRequest) {
        commentService.updateComment(commentUpdateRequest);
    }

    @DeleteMapping
    @ApiOperation(value = "코멘트 삭제", notes = "코멘트를 삭제합니다.")
    public void deleteComment(@ApiParam("코멘트 식별자") @RequestParam int commentId) {
        commentService.deleteComment(commentId);
    }

}
