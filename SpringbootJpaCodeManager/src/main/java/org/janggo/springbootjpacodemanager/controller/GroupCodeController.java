package org.janggo.springbootjpacodemanager.controller;

import lombok.RequiredArgsConstructor;
import org.janggo.springbootjpacodemanager.dto.CodeResultDto;
import org.janggo.springbootjpacodemanager.entity.GroupCode;
import org.janggo.springbootjpacodemanager.service.GroupCodeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GroupCodeController {
    private final GroupCodeService groupCodeService;

    @GetMapping("/groupcodes")
    public CodeResultDto listGroupCode(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize){
        return groupCodeService.listGroupCode(pageNumber, pageSize);
    }

    @GetMapping("/groupcodes/{groupCode}")
    public CodeResultDto detailGroupCode(@PathVariable("groupCode") String groupCode){
        return groupCodeService.detailGroupCode(groupCode);
    }

    @PostMapping("/groupcodes")
    public CodeResultDto insertGroupCode(GroupCode groupCode){
        return groupCodeService.insertGroupCode(groupCode);
    }

    @PutMapping("/groupcodes")
    public CodeResultDto updateGroupCode(GroupCode groupCode){
        return groupCodeService.updateGroupCode(groupCode);
    }

    @DeleteMapping("/groupcodes/{groupCode}")
    public CodeResultDto updateGroupCode(@PathVariable("groupCode") String groupCode){
        return groupCodeService.deleteGroupCode(groupCode);
    }

    @GetMapping("/groupcodes/count")
    public CodeResultDto countGroupCode(){
        return countGroupCode();
    }
}
