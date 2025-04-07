package hello.phonemanager.repository;

import hello.phonemanager.domain.Owner;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface OwnerRepository {

    Optional<Owner> findById(Long OwnerId);
    // 회원가입: Owner 등록
    void insertOwner(Owner owner);

    // 로그인: username으로 Owner 조회
    Owner findByUsername(String username);

    // 비밀번호 확인
    boolean checkPassword(String username, String password);

    Owner findByShopId(Long shopId);
}
