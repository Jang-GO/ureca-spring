package org.janggo.springbootjpacodemanager.service;

import lombok.RequiredArgsConstructor;
import org.janggo.springbootjpacodemanager.dto.CodeDto;
import org.janggo.springbootjpacodemanager.dto.CodeResultDto;
import org.janggo.springbootjpacodemanager.dto.GroupCodeDto;
import org.janggo.springbootjpacodemanager.entity.Code;
import org.janggo.springbootjpacodemanager.entity.GroupCode;
import org.janggo.springbootjpacodemanager.entity.key.CodeKey;
import org.janggo.springbootjpacodemanager.repository.CodeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService{

    private final CodeRepository codeRepository;

    @Override
    public CodeResultDto insertCode(Code code) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try{
            codeRepository.save(code);
            codeResultDto.setResult("success");
        }catch (Exception e){
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto updateCode(Code code) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try{
            codeRepository.save(code);
            codeResultDto.setResult("success");
        }catch (Exception e){
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto deleteCode(CodeKey codeKey) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try{
            codeRepository.deleteById(codeKey);
            codeResultDto.setResult("success");
        }catch (Exception e){
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto listCode(String groupCode, int pageNumber, int pageSize) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try{
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<Code> all = codeRepository.findByGroupCode(groupCode,pageable);
            List<CodeDto> codeDtoList = new ArrayList<>();

            all.toList().forEach(code -> codeDtoList.add(CodeDto.from(code)));
            codeResultDto.setCodeDtoList(codeDtoList);

            long count = codeDtoList.size();
            codeResultDto.setCount(count);
            codeResultDto.setResult("success");
        }catch (Exception e){
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto detailCode(CodeKey codeKey) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try{
            Optional<Code> byId = codeRepository.findById(codeKey);
            byId.ifPresentOrElse(
                    optionalCode -> {
                        codeResultDto.setCodeDto(CodeDto.from(optionalCode));
                        codeResultDto.setResult("success");
                    },
                    () -> codeResultDto.setResult("fail")
            );
        }catch (Exception e){
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto countCode() {
        CodeResultDto codeResultDto = new CodeResultDto();
        try{
            Long count = codeRepository.count();
            codeResultDto.setCount(count);
            codeResultDto.setResult("success");
        }catch(Exception e){
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }
}
