package mycom.springphonemanager.repository.mybatis;

import mycom.springphonemanager.domain.Owner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MBOwnerRepository {

    @Select("SELECT * FROM owner WHERE owner_uuid = #{ownerUuid}")
    Owner findByUUID(@Param("ownerUuid") String ownerUuid);
}
