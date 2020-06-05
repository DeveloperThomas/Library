package demo.service;

import demo.dao.RoleRepository;
import demo.dao.UserRepository;
import demo.dto.UserDataTransfer;
import demo.map.UserMap;
import demo.model.Role;
import demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMap userMap;
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMap userMap, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMap = userMap;
        this.roleRepository = roleRepository;
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
        if(!checkUsername(userDataTransfer.getUsername())){
            throw new RuntimeException("That username is taken");
        }
        else {
            User user = new User();
            user.setUsername(userDataTransfer.getUsername());
//          user.setPassword(userDataTransfer.getPassword());
            user.setPassword(passwordEncoder.encode(userDataTransfer.getPassword()));
            Set<Role> roles = new HashSet<>();
            userDataTransfer.getRoles().forEach(r -> roles
                    .add(roleRepository.findByRoleName(r)
                            .orElseThrow(() -> new RuntimeException("Role doesn't exists"))));
            user.setRoles(roles);
            return userMap.map(userRepository.save(user));
        }
    }

    public UserDataTransfer updateUserDataTransfer(String principal, UserDataTransfer userDataTransfer) {
        String[] tmp = principal.split(";");
        int first = tmp[0].indexOf(":"); int second = tmp[0].indexOf(":", first + 1);
        String username = tmp[0].substring(second+2);

        Collection<User> users = userRepository.findByUsername(username);
        if(users==null || users.size()==0) throw new RuntimeException("User doesn't exist!");
        User user = users.stream().findFirst().get();

        if(userDataTransfer.getUsername() != null) {
            user.setUsername(userDataTransfer.getUsername());
        }
        if(userDataTransfer.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDataTransfer.getPassword()));
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

    public List<UserDataTransfer> searchByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .stream()
                .map(user -> userMap.map(user))
                .collect(Collectors.toList());
    }

    private boolean checkUsername(String username){
        return userRepository.findAll().stream().noneMatch(u -> u.getUsername().equals(username));
    }
}
