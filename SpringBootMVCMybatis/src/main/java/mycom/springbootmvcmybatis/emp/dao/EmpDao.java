package mycom.springbootmvcmybatis.emp.dao;

import mycom.springbootmvcmybatis.emp.dto.EmpDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpDao {
    // emp-mapper.xml 에 대응
    List<EmpDto> listEmp();
    EmpDto detailEmp(int employeeId);
    int insertEmp(EmpDto empDto);
    int updateEmp(EmpDto empDto);
    int deleteEmp(int employeeId);

    // emp-mapper2.xml 에 대응
    List<EmpDto> listEmpLike(String searchWord);
    List<EmpDto> listEmpMap();
    List<EmpDto> listEmpWhereIf(Map<String , String> map); // EmpDto 도 가능

}
