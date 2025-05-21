package org.janggo.springbootjpacodemanager.repository;

import org.janggo.springbootjpacodemanager.entity.Code;
import org.janggo.springbootjpacodemanager.entity.key.CodeKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CodeRepository extends JpaRepository<Code, CodeKey> {
    // Code 의 CRUD 외에
    // groupCode 기준 코드 조회 <- JPQL
    @Query("select c from Code c where c.codekey.groupCode = :groupCode order by c.orderNo")
    Page<Code> findByGroupCode(@Param("groupCode") String groupCode, Pageable pageable);
}
