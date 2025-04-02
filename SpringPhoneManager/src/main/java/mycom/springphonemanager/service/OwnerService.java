package mycom.springphonemanager.service;

import lombok.RequiredArgsConstructor;
import mycom.springphonemanager.domain.Owner;
import mycom.springphonemanager.repository.mybatis.MBOwnerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final MBOwnerRepository ownerRepository;

    public Owner findByUUID(String uuid){
        return ownerRepository.findByUUID(uuid);
    }
}
