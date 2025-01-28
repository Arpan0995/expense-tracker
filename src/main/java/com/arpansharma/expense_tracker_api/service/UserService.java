package com.arpansharma.expense_tracker_api.service;

import com.arpansharma.expense_tracker_api.models.User;
import com.arpansharma.expense_tracker_api.models.UserModel;

public interface UserService {

    public User registerUser(UserModel userModel);

    public User readUser();

    public User updateUser(User user);

    public void deleteUser();

    public User getLoggedInuser();
}
