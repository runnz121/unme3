package com.esanghaesee.unme3.unme3.repository.image;


import com.esanghaesee.unme3.unme3.domain.Image;
import com.esanghaesee.unme3.unme3.domain.QImage;
import com.esanghaesee.unme3.unme3.domain.QMember;
import com.esanghaesee.unme3.unme3.dto.ImageDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Transactional
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepositoryCustom {


    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    private BooleanExpression hasId(Long idCond) {
        QMember qmember = QMember.member;
        return idCond == null ? qmember.id.isNull() : qmember.id.eq(idCond);
    }


    //유저  id로 조회해서 전체 feed사진 갖고오기
    @Override
    public List<Image> getMyFeedImage(Long userId) {
        QMember qMember = QMember.member;
        QImage qImage = QImage.image;

        return queryFactory
                .select(qImage)
                .from(qImage)
                .join(qImage.member, qMember)
                .where(hasId(userId))
                .limit(30)
                .fetch();
    }
}

//    //list를 벌크 저장 하는법?
//    @Override
//    public void UploadMyFeedImages(Long userId, ImageDto imageDto){
//        QImage qImage = QImage.image;
//
//        return queryFactory
//                .select()
//    }
//}
