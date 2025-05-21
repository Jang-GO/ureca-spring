package org.janggo.springbootjpacodemanager.entity.key;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// Serializable, 기본생성자
@Getter
@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeKey implements Serializable {

    private String groupCode;
    private String code;

    public CodeKey(String groupCode, String code) {
        this.groupCode = groupCode;
        this.code = code;
    }
}
