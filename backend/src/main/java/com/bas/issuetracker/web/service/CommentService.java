package com.bas.issuetracker.web.service;


import com.bas.issuetracker.web.dao.CommentDAO;
import com.bas.issuetracker.web.dto.comment.CommentDTO;
import com.bas.issuetracker.web.dto.comment.CommentRequest;
import com.bas.issuetracker.web.dto.comment.CommentUpdateRequest;
import com.bas.issuetracker.web.exceptions.DeleteCommentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentDAO commentDAO;

    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public List<CommentDTO> showCommentsByIssueId(int issueId) {
        return commentDAO.showCommentsByIssueId(issueId);
    }

    public void createComment(int userId, CommentRequest commentRequest) {
        commentDAO.createComment(userId, commentRequest.getIssueId(), commentRequest.getContent(), true);
    }

    public void updateComment(CommentUpdateRequest commentUpdateRequest) {
//        if (commentDAO.findAuthorOfCommentById(commentUpdateRequest.getCommentId()) != userId) {
//            throw new UnsupportedOperationException("코멘트 쓴이가 아닙니다.");
//        }
        commentDAO.updateComment(commentUpdateRequest.getCommentId(), commentUpdateRequest.getContent());
    }

    public void deleteComment(int commentId) {
        int deletable = commentDAO.findDeletableOfCommentById(commentId);
        if (deletable == 0) {
            throw new DeleteCommentException("처음 코멘트 삭제 불가능");
        }
        commentDAO.deleteComment(commentId);
    }

}
