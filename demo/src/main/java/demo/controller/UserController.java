package demo.controller;

import demo.dto.UserDataTransfer;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

    @GetMapping("/get/{username}")
    public UserDataTransfer getUserByUsername(@PathVariable String username){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserDataTransferByUsername(auth.getPrincipal().toString(), username);
    }

    @GetMapping("/{id}")
    public UserDataTransfer getUserById(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserDataTransferById(auth.getPrincipal().toString(), id);
    }

    @PostMapping()
    public UserDataTransfer createUser(@RequestBody UserDataTransfer userDataTransfer){
        return userService.addUserDataTransfer(userDataTransfer);
    }

    @PutMapping("/{userID}")
    public UserDataTransfer updateUser(@PathVariable Long userID, @RequestBody UserDataTransfer userDataTransfer){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.updateUserDataTransfer(auth.getPrincipal().toString(), userID, userDataTransfer);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUserDataTransferById(auth.getPrincipal().toString(), id);
    }

    @GetMapping("/search")
    public List<UserDataTransfer> searchUser(@RequestParam String name) {
        return userService.searchByUsername(name);
    }

}
