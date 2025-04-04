package mycom.springbootmvcmybatis.emp.controller;

import mycom.springbootmvcmybatis.emp.dto.EmpDto;
import mycom.springbootmvcmybatis.emp.service.EmpService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emps")
public class EmpController {
    private final EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping("/list")
    public List<EmpDto> listEmp(){
        return empService.listEmp();
    }

    @GetMapping("/detail/{employeeId}")
    public EmpDto detailEmp(@PathVariable("employeeId") int employeeId){
        return empService.detailEmp(employeeId);
    }

    @PostMapping("/insert")
    public Map<String, String> empInsert(EmpDto empDto){
        int ret = empService.insertEmp(empDto);
        Map<String, String> map = new HashMap<>();
        if(ret == 1) map.put("result", "success");
        else map.put("result", "fail");
        return map;
    }

    @PostMapping("/update")
    public Map<String, String> empUpdate(EmpDto empDto) {
        int ret = empService.updateEmp(empDto);
        Map<String, String> map = new HashMap<>();
        if (ret == 1) map.put("result", "success");
        else map.put("result", "fail");
        return map;
    }

    @GetMapping("/delete/{employeeId}")
    public Map<String, String> empDelete(@PathVariable("employeeId") int employeeId){
        int ret = empService.deleteEmp(employeeId);
        Map<String, String> map = new HashMap<>();
        if (ret == 1) map.put("result", "success");
        else map.put("result", "fail");
        return map;
    }

    // emp-mapper2.xml에 대응
    @GetMapping("/listEmpLike")
    public List<EmpDto> listEmpLike(@RequestParam("searchWord") String searchWord){
        return empService.listEmpLike(searchWord);
    }

    @GetMapping("/listEmpMap")
    public List<EmpDto> listEmpMap(){
        return empService.listEmpMap();
    }

    @GetMapping("/listEmpWhereIf")
    public List<EmpDto> listEmpWhereIf(@RequestParam Map<String, String> map){
        return empService.listEmpWhereIf(map);
    }
}
