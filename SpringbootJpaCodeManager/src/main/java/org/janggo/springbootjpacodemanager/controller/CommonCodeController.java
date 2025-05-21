package org.janggo.springbootjpacodemanager.controller;

import lombok.RequiredArgsConstructor;
import org.janggo.springbootjpacodemanager.dto.CommonCodeResultDto;
import org.janggo.springbootjpacodemanager.service.CommonCodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommonCodeController {
    private final CommonCodeService commonCodeService;

    @PostMapping("/commoncodes")
    public CommonCodeResultDto getCommonCodeList(@RequestBody List<String> groupCodes) {
        return commonCodeService.getCommonCodeList(groupCodes);
    }
}
