package hello.phonemanager.repository;

import hello.phonemanager.domain.Phone;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhoneRepository {
    void insertPhone(Phone phone);

    void deleteAll();
}
