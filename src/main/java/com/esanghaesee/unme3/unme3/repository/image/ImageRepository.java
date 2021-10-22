package com.esanghaesee.unme3.unme3.repository.image;

import com.esanghaesee.unme3.unme3.domain.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image,Long>, ImageRepositoryCustom {
    Page<Image> findAllById(Long myId, Pageable pageable);
}
