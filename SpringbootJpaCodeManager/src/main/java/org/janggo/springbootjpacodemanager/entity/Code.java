package org.janggo.springbootjpacodemanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import org.janggo.springbootjpacodemanager.entity.key.CodeKey;

// 복합키
@Entity
@Data
public class Code {

    @EmbeddedId
    private CodeKey codekey;

    @Column(name="code_name")
    private String codeName;
    @Column(name="code_name_brief")
    private String codeNameBrief;
    @Column(name="order_no")
    private int orderNo;
}
