package in.arpansharma.expense_tracker_api.service;

import in.arpansharma.expense_tracker_api.models.User;
import in.arpansharma.expense_tracker_api.models.UserModel;

public interface UserService {

    public User registerUser(UserModel userModel);

    public User readUser(Long id);

    public User updateUser(User user,Long id);

    public void deleteUser(Long id);
}
