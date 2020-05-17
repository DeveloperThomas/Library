package demo.controller;

import demo.dto.UserDataTransfer;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDataTransfer> getAllUsers(){
        return userService.findAllUsersDataTransfer();
    }

    @GetMapping("/{id}")
    public UserDataTransfer getUserById(@PathVariable Long id){
        return userService.findUserDataTransferById(id);
    }

    @PostMapping()
    public UserDataTransfer createUser(@RequestBody UserDataTransfer userDataTransfer){
        return userService.addUserDataTransfer(userDataTransfer);
    }

    @PutMapping("/{id}")
    public UserDataTransfer updateUser(@PathVariable Long id, @RequestBody UserDataTransfer userDataTransfer){
        return userService.updateUserDataTransfer(id, userDataTransfer);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserDataTransferById(id);
    }

}
