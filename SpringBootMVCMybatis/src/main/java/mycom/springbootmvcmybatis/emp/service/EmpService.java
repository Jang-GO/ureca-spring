package mycom.springbootmvcmybatis.emp.service;

import mycom.springbootmvcmybatis.emp.dto.EmpDto;

import java.util.List;
import java.util.Map;

public interface EmpService {
    List<EmpDto> listEmp();
    EmpDto detailEmp(int employeeId);
    int insertEmp(EmpDto empDto);
    int updateEmp(EmpDto empDto);
    int deleteEmp(int employeeId);

    List<EmpDto> listEmpLike(String searchWord);
    List<EmpDto> listEmpMap();
    List<EmpDto> listEmpWhereIf(Map<String , String> map);

}
