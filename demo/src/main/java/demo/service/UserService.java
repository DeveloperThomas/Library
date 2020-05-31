package demo.service;

import demo.dao.UserRepository;
import demo.dto.UserDataTransfer;
import demo.map.UserMap;
import demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMap userMap;

    @Autowired
    public UserService(UserRepository userRepository, UserMap userMap) {
        this.userRepository = userRepository;
        this.userMap = userMap;
    }

    public List<UserDataTransfer> findAllUsersDataTransfer() {
        List<UserDataTransfer> userDataTransferList = new ArrayList<>();
        userRepository.findAll().forEach(e -> userDataTransferList.add(userMap.map(e)));
        return userDataTransferList;
    }

    public UserDataTransfer findUserDataTransferById(Long id) {
        return userMap.map(findUserById(id));
    }

    public UserDataTransfer addUserDataTransfer(UserDataTransfer userDataTransfer) {
        User user = new User();
        user.setUsername(userDataTransfer.getUsername());
        user.setPassword(userDataTransfer.getPassword());
        user.setRoles(user.getRoles());
        return userMap.map(userRepository.save(user));
    }

    public UserDataTransfer updateUserDataTransfer(Long id, UserDataTransfer userDataTransfer) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot found your author"));
        if(userDataTransfer.getUsername() != null) {
            user.setUsername(userDataTransfer.getUsername());
        }
        if(userDataTransfer.getPassword() != null) {
            user.setPassword(userDataTransfer.getPassword());
        }
//        if(userDataTransfer.getRoles().isEmpty() && user.getRoles().size()!=0) {
//            user.setRoles(user.getRoles());
//        }

        return userMap.map(userRepository.save(user));
    }

    public void deleteUserDataTransferById(Long id) {
        userRepository.deleteById(id);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find your author"));
    }

    public List<UserDataTransfer> searchByUsername(String name) {
        return userRepository
                .findByName(name)
                .stream()
                .map(user -> userMap.map(user))
                .collect(Collectors.toList());
    }
}
