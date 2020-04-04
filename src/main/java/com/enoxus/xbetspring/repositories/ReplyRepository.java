package com.enoxus.xbetspring.repositories;

import com.enoxus.xbetspring.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> getAllByParentId(Long id);
}
