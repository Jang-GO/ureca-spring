package org.janggo.springbootjpacodemanager.service;

import org.janggo.springbootjpacodemanager.dto.CodeResultDto;
import org.janggo.springbootjpacodemanager.entity.GroupCode;

public interface GroupCodeService {
    CodeResultDto insertGroupCode(GroupCode groupCode);
    CodeResultDto updateGroupCode(GroupCode groupCode);
    CodeResultDto deleteGroupCode(String groupCodeKey);

    CodeResultDto listGroupCode(int pageNumber, int pageSize);
    CodeResultDto detailGroupCode(String groupCode);
    CodeResultDto countGroupCode();
}
