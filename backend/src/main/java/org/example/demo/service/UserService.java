package org.example.demo.service;

import org.example.demo.entity.User;
import org.example.demo.model.request.ChangePasswordRequest;
import org.example.demo.model.request.CreateUserRequest;
import org.example.demo.model.request.UpdateProfileRequest;

public interface UserService {

    void register(CreateUserRequest request);


    User getUserByUsername(String username);

    User updateUser(UpdateProfileRequest request);

    void changePassword(ChangePasswordRequest request);

}
