package org.janggo.springbootjpacodemanager.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.janggo.springbootjpacodemanager.dto.CodeDto;
import org.janggo.springbootjpacodemanager.dto.CommonCodeResultDto;
import org.janggo.springbootjpacodemanager.entity.Code;
import org.janggo.springbootjpacodemanager.repository.CommonCodeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommonCodeServiceImpl implements CommonCodeService{
    private final CommonCodeRepository commonCodeRepository;

    @Override
    public CommonCodeResultDto getCommonCodeList(List<String> groupCodes) {
        CommonCodeResultDto commonCodeResultDto = new CommonCodeResultDto();
        try {
            List<Code> codeList = commonCodeRepository.findByGroupCodes(groupCodes);
            Map<String, List<CodeDto>> commonCodeListMap = new HashMap<>();
            String currGroupCode = "";
            List<CodeDto> codeDtoList = null;

            for (Code code : codeList) {
                String groupCode = code.getCodekey().getGroupCode();

                if(! currGroupCode.equals(groupCode)) { // 두 그룹코드가 다르면

                    // 최초가 아닌 currGroupCode 가 유효한 상황에서 변경되었다면
                    // 현재까지의 codeDtoList 를 commonCodeListMap 에 담는다.
                    if( Strings.isNotEmpty(currGroupCode) ) {
                        commonCodeListMap.put(currGroupCode, codeDtoList);
                    }
                    // 새로운 코드를 받을 준비를 한다.
                    currGroupCode = groupCode;
                    codeDtoList = new ArrayList<>();
                }

                codeDtoList.add(CodeDto.from(code));
            }

            commonCodeListMap.put(currGroupCode, codeDtoList); // 마지막 currGroupCode


            commonCodeResultDto.setCommonCodeDtoListMap(commonCodeListMap);
            commonCodeResultDto.setResult("success");
        }catch(Exception e) {
            e.printStackTrace();
            commonCodeResultDto.setResult("fail");
        }
        return commonCodeResultDto;
    }
}
