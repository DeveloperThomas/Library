package demo.map;

import demo.dto.UserDataTransfer;
import demo.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMap implements Mapper<User, UserDataTransfer> {
    @Override
    public UserDataTransfer map(User user) {
        return new UserDataTransfer(user.getId(), user.getUsername(), user.getPassword(), user.getRoles().stream().map(e -> e.getRoleName()).collect(Collectors.toSet()));
    }
}
