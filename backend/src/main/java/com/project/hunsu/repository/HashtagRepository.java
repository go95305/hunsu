package com.project.hunsu.repository;

import com.project.hunsu.model.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag,Long> {

    List<Hashtag> findHashtagByOotdIdx(Long idx);
    List<Hashtag> findByContentAndFlag(String hashtag, Boolean flag);
    List<Hashtag> findByContentContainingAndFlag(String hashtag, Boolean flag);

}
