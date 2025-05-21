package org.janggo.springbootjpacodemanager.dto;

import lombok.Builder;
import org.janggo.springbootjpacodemanager.entity.GroupCode;

@Builder
public record GroupCodeDto(
        String groupCode,
        String groupCodeName,
        String groupCodeDesc
) {
    public static GroupCodeDto from(GroupCode groupCode) {
        return GroupCodeDto.builder()
                .groupCode(groupCode.getGroupCode())
                .groupCodeName(groupCode.getGroupCodeName())
                .groupCodeDesc(groupCode.getGroupCodeDesc())
                .build();
    }
}
