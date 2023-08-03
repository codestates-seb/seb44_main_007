package com.server.total.repository;

import com.server.total.entity.Total;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TotalRepository extends JpaRepository<Total, Long> {
    List<Total> findByMemberId(Long memberId);
}
