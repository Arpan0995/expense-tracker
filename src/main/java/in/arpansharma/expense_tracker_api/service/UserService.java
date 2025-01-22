package in.arpansharma.expense_tracker_api.service;

import in.arpansharma.expense_tracker_api.models.User;
import in.arpansharma.expense_tracker_api.models.UserModel;

public interface UserService {

    public User registerUser(UserModel userModel);

    public User readUser(Long id);
}
