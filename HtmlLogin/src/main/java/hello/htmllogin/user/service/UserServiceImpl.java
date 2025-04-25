package hello.htmllogin.user.service;

import hello.htmllogin.user.dto.UserResultDto;
import hello.htmllogin.user.entity.User;
import hello.htmllogin.user.entity.UserRole;
import hello.htmllogin.user.repository.UserRepository;
import hello.htmllogin.user.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Set;

// Register 단계
// UserRepository - save
// UserRoleRepository - find, save(2가지)
@Service
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserResultDto insertUser(User user) {
        UserResultDto userResultDto = new UserResultDto();
        try {
            // #1. 기존 UserRole 을 find, name = ROLE_CUSTOMER
//        UserRole userRole = userRoleRepository.findByName("ROLE_CUSTOMER");
//        Set<UserRole> userRoles = Set.of(userRole);
//        user.setUserRoles(userRoles);
//        User save = userRepository.save(user);

            // #2. 새로운 UserRole 생성

            // #2-1. userRole 객체 save X -> 영속화 X
//        UserRole userRole = new UserRole();
//        userRole.setName("role_test");
//        Set<UserRole> userRoles = Set.of(userRole);
//        user.setUserRoles(userRoles);
//        User save = userRepository.save(user);

            // #2-2. userRole 객체 save O -> 영속화 O
        UserRole userRole = new UserRole();
        userRole.setName("role_test");
        userRoleRepository.save(userRole);

        Set<UserRole> userRoles = Set.of(userRole);
        user.setUserRoles(userRoles);
        User save = userRepository.save(user);

        userRepository.flush();
        userRoleRepository.flush();
            // #3. transaction + #1 상황
//        UserRole userRole = userRoleRepository.findByName("ROLE_CUSTOMER");
//        Set<UserRole> userRoles = Set.of(userRole);
//        user.setUserRoles(userRoles);
//        User save = userRepository.save(user);
//
//        String s = null;
//        s.length();

            // #4. #2-1 오류 상황을 OneToMany 의 연관관계로 해결
//            UserRole userRole = new UserRole();
//            userRole.setName("role_test");
//            Set<UserRole> userRoles = Set.of(userRole);
//            user.setUserRoles(userRoles);
//            User save = userRepository.save(user);


            // @Transactional 상황에서는 TransactionAspect 가 관여하고, Proxy 객체를 통해 우리의 코드를
            // 대신 호출하고 최종적인 예외가 발생하지 않으면 TransactionAspect 가 commit() 을 수행
            // 이 과정에서 예외가 발생하므로 우리 코드 밖에서 생기는 예외를 우리 코드에서 catch 하지 못하는 상황
//            UserRole userRole = new UserRole();
//            userRole.setName("role_test");
//            Set<UserRole> userRoles = Set.of(userRole);
//            user.setUserRoles(userRolersaaress);
//            User save = userRepository.save(user);
//            userRepository.flush();

            // 사실 트랜잭션이 필요한 부분은 save, flush 부분임 따라서 따로 메서드로 빼버리는거임
//            UserRole userRole = new UserRole();
//            userRole.setName("role_test");
//            Set<UserRole> userRoles = Set.of(userRole);
//            user.setUserRoles(userRoles);
//            txStart(user);


            userResultDto.setResult("success");
        }catch (Exception e){
            userResultDto.setResult("fail");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return userResultDto;
    }

    @Transactional
    public void txStart(User user) {
        User save = userRepository.save(user);
        userRepository.flush();
    }
}
