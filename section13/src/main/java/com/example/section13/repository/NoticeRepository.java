package com.example.section13.repository;


import com.example.section13.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("""
            from Notice n
            where now() between n.noticBegDt and n.noticEndDt
            """)
    List<Notice> findAllActiveNotices();

}
