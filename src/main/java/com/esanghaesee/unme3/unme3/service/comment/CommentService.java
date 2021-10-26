package com.esanghaesee.unme3.unme3.service.comment;

import com.esanghaesee.unme3.unme3.dto.CommentDto;
import com.esanghaesee.unme3.unme3.vo.comment.ResponseComment;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface CommentService {
    ResponseComment createComments(CommentDto commentDto, Long userId) throws ChangeSetPersister.NotFoundException;

    void deleteComments(CommentDto commentDto, Long userId);
}
