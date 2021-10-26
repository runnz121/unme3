package com.esanghaesee.unme3.unme3.service.comment;

import com.esanghaesee.unme3.unme3.domain.Comment;
import com.esanghaesee.unme3.unme3.domain.Member;
import com.esanghaesee.unme3.unme3.dto.CommentDto;
import com.esanghaesee.unme3.unme3.repository.comment.CommentRepository;
import com.esanghaesee.unme3.unme3.repository.member.MemberRepository;
import com.esanghaesee.unme3.unme3.vo.comment.ResponseComment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CommentServiceImpl implements CommentService {


    private MemberRepository memberRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(MemberRepository memberRepository, CommentRepository commentRepository) {
        this.memberRepository = memberRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public ResponseComment createComments(CommentDto commentDto, Long userId) {
        Member writer= memberRepository.findById(userId).orElseThrow(null);
        Comment comment = commentRepository.save(
                Comment.createComment(
                        writer,
                        commentDto.getContent(),
                        commentDto.getStatus(),
                        commentDto.getParentId() != null?
                            commentRepository.findById(commentDto.getParentId()).orElseThrow(null) : null

                )
        );

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(comment, ResponseComment.class);
    }

    @Override
    public void deleteComments(CommentDto commentDto, Long userId) {

    }

}
