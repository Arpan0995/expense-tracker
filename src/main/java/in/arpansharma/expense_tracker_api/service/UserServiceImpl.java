package in.arpansharma.expense_tracker_api.service;

import in.arpansharma.expense_tracker_api.models.User;
import in.arpansharma.expense_tracker_api.models.UserModel;
import in.arpansharma.expense_tracker_api.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        BeanUtils.copyProperties(userModel,user);
        userRepository.save(user);
        return user;
    }
}
