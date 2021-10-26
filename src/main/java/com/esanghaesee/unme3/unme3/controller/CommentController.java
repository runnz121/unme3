package com.esanghaesee.unme3.unme3.controller;


import com.esanghaesee.unme3.unme3.Security.AuthUser;
import com.esanghaesee.unme3.unme3.Security.UserPrincipal;
import com.esanghaesee.unme3.unme3.dto.CommentDto;
import com.esanghaesee.unme3.unme3.service.comment.CommentServiceImpl;
import com.esanghaesee.unme3.unme3.vo.comment.RequestComment;
import com.esanghaesee.unme3.unme3.vo.comment.ResponseComment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentServiceImpl commentService;

    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }


    //댓글 생성
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody RequestComment requestComment, @AuthUser UserPrincipal myId) throws Exception {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CommentDto commentDto = mapper.map(requestComment, CommentDto.class);
        Long userId = myId.getId();
        ResponseComment responseComment = commentService.createComments(commentDto, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseComment);
    }


    //댓글 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteComment(@RequestBody RequestComment requestComment, @AuthUser UserPrincipal myId) throws Exception {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CommentDto commentDto = mapper.map(requestComment, CommentDto.class);
        Long userId = myId.getId();
        commentService.deleteComments(commentDto, userId);

        return ResponseEntity.status(HttpStatus.OK).body("삭제완료");
    }
}
