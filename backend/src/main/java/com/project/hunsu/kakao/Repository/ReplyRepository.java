package com.project.hunsu.kakao.Repository;

import com.project.hunsu.Entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    Reply findAllByOotdIdx(Long idx);
}
