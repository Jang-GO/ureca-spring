package org.janggo.springbootjpacodemanager.dto;

import lombok.Builder;
import org.janggo.springbootjpacodemanager.entity.Code;
import org.janggo.springbootjpacodemanager.entity.GroupCode;

@Builder
public record CodeDto(
        String groupCode,
        String code,
        String codeName,
        String codeNameBrief,
        int orderNo
) {
    public static CodeDto from(Code code) {
        return CodeDto.builder()
                .groupCode(code.getCodekey().getGroupCode())
                .code(code.getCodekey().getCode())
                .codeName(code.getCodeName())
                .codeNameBrief(code.getCodeNameBrief())
                .orderNo(code.getOrderNo())
                .build();
    }
}
