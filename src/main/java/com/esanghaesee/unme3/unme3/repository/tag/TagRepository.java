package com.esanghaesee.unme3.unme3.repository.tag;

import com.esanghaesee.unme3.unme3.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long>, TagRepositoryCustom {
}
