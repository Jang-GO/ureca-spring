package org.janggo.springbootjpacodemanager.service;

import org.janggo.springbootjpacodemanager.dto.CodeResultDto;
import org.janggo.springbootjpacodemanager.entity.Code;
import org.janggo.springbootjpacodemanager.entity.GroupCode;
import org.janggo.springbootjpacodemanager.entity.key.CodeKey;

public interface CodeService {
    CodeResultDto insertCode(Code code);
    CodeResultDto updateCode(Code code);
    CodeResultDto deleteCode(CodeKey codeKey);

    CodeResultDto listCode(String groupCode, int pageNumber, int pageSize);
    CodeResultDto detailCode(CodeKey codeKey);
    CodeResultDto countCode();
}
