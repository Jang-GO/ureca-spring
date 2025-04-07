package hello.phonemanager.service;


import hello.phonemanager.domain.Owner;
import hello.phonemanager.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public void register(Owner owner) {
        ownerRepository.insertOwner(owner);
    }

    public Owner login(String username, String password) {
        Owner owner = ownerRepository.findByUsername(username);
        if (owner != null && owner.getPassword().equals(password)) {
            return owner;
        }
        return null;
    }

    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
    }
}
