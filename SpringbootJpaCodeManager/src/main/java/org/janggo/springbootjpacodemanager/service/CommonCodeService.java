package org.janggo.springbootjpacodemanager.service;

import org.janggo.springbootjpacodemanager.dto.CommonCodeResultDto;

import java.util.List;

public interface CommonCodeService {
    CommonCodeResultDto getCommonCodeList(List<String> groupCodes);
}
