package org.janggo.springbootjpacodemanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

// @Id 컬럼에 @GeneratedValue 사용 X (DB 에서 char(3) 형임)
// => JPA 의 key 중심의 능동적인 제어에 영향을 미친다.
// insert 시점에 key 값이 존재하더라도
// insert 시점에 우선 entityManager 의 관리 대상인지 확인 <- 첫 번째 select
// insert 시점에 Transient 체크 <- 두 번째 select
// 공통코드 자체에는 문제가 없지만 (insert 도 bulk 로 진행 할것임)
// @GeneratedValue 사용 X 인 다른 상황 처리 ????
@Entity
@Data
@Table(name="group_code")
public class GroupCode implements Persistable<String> {

    @Id
    @Column(name="group_code")
    private String groupCode;
    @Column(name="group_code_name")
    private String groupCodeName;
    @Column(name="group_code_desc")
    private String groupCodeDesc;

    @Transient
    private boolean isNew = false;

    @Override
    public String getId() {
        return groupCode;
    }
}
