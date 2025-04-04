package mycom.springbootmvcmybatis.emp.service;

import mycom.springbootmvcmybatis.emp.dao.EmpDao;
import mycom.springbootmvcmybatis.emp.dto.EmpDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService{

    private final EmpDao empDao;

    public EmpServiceImpl(EmpDao empDao) {
        this.empDao = empDao;
    }

    @Override
    public List<EmpDto> listEmp() {
        return empDao.listEmp();
    }

    @Override
    public EmpDto detailEmp(int employeeId) {
        return empDao.detailEmp(employeeId);
    }

    @Override
    public int insertEmp(EmpDto empDto) {
        return empDao.insertEmp(empDto);
    }

    @Override
    public int updateEmp(EmpDto empDto) {
        return empDao.updateEmp(empDto);
    }

    @Override
    public int deleteEmp(int employeeId) {
        return empDao.deleteEmp(employeeId);
    }

    @Override
    public List<EmpDto> listEmpLike(String searchWord) {
        return empDao.listEmpLike(searchWord);
    }

    @Override
    public List<EmpDto> listEmpMap() {
        return empDao.listEmpMap();
    }

    @Override
    public List<EmpDto> listEmpWhereIf(Map<String, String> map) {
        return empDao.listEmpWhereIf(map);
    }
}
