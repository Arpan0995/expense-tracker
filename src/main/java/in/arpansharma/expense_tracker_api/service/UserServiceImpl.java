package in.arpansharma.expense_tracker_api.service;

import in.arpansharma.expense_tracker_api.exception.ItemAlreadyExistsException;
import in.arpansharma.expense_tracker_api.exception.ResourceNotFoundException;
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
        if(userRepository.existsByemail(userModel.getEmail())){
            throw new ItemAlreadyExistsException("Email already associated with another user.");
        }
        User user = new User();
        BeanUtils.copyProperties(userModel,user);
        userRepository.save(user);
        return user;
    }

    @Override
    public User readUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id does not exist"));
    }

    @Override
    public User updateUser(User user, Long id) throws ResourceNotFoundException{
        User user1 = readUser(id);
        user1.setName(user.getName()!=null?user.getName():user1.getName());
        user1.setAge(user.getAge()!=null? user.getAge() : user1.getAge());
        user1.setEmail(user.getEmail()!=null? user.getEmail() : user1.getEmail());
        user1.setPassword(user.getPassword()!=null? user.getPassword() : user1.getPassword());

        userRepository.save(user1);
        return user1;
    }

    @Override
    public void deleteUser(Long id) {
        readUser(id);
        userRepository.deleteById(id);
    }
}
