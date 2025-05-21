package org.janggo.springbootjpacodemanager.service;

import lombok.RequiredArgsConstructor;
import org.janggo.springbootjpacodemanager.dto.CodeResultDto;
import org.janggo.springbootjpacodemanager.dto.GroupCodeDto;
import org.janggo.springbootjpacodemanager.entity.GroupCode;
import org.janggo.springbootjpacodemanager.repository.GroupCodeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupCodeServiceImpl implements GroupCodeService{

    private final GroupCodeRepository groupCodeRepository;

    @Override
    public CodeResultDto insertGroupCode(GroupCode groupCode) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try{
            groupCode.setNew(true);
            groupCodeRepository.save(groupCode);
            codeResultDto.setResult("success");
        }catch (Exception e){
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto updateGroupCode(GroupCode groupCode) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try{
            groupCodeRepository.save(groupCode);
            codeResultDto.setResult("success");
        }catch (Exception e){
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto deleteGroupCode(String groupCodeKey) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try{
            groupCodeRepository.deleteById(groupCodeKey);
            codeResultDto.setResult("success");
        }catch (Exception e){
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto listGroupCode(int pageNumber, int pageSize) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try{
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<GroupCode> all = groupCodeRepository.findAll(pageable);
            List<GroupCodeDto> groupCodeDtoList = new ArrayList<>();

            all.toList().forEach(groupCode -> groupCodeDtoList.add(GroupCodeDto.from(groupCode)));
            codeResultDto.setGroupCodeDtoList(groupCodeDtoList);

            Long count = groupCodeRepository.count();
            codeResultDto.setCount(count);
            codeResultDto.setResult("success");
        }catch (Exception e){
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto detailGroupCode(String groupCode) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try{
            Optional<GroupCode> byId = groupCodeRepository.findById(groupCode);
            byId.ifPresentOrElse(
                    optionalGroupCode -> {
                        codeResultDto.setGroupCodeDto(GroupCodeDto.from(optionalGroupCode));
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
    public CodeResultDto countGroupCode() {
        CodeResultDto codeResultDto = new CodeResultDto();
        try{
            Long count = groupCodeRepository.count();
            codeResultDto.setCount(count);
            codeResultDto.setResult("success");
        }catch(Exception e){
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }
}
