package org.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.demo.entity.User;
import org.example.demo.model.request.UpdateProfileRequest;
import org.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    @Operation(summary="Lấy ra user bằng username")
    public ResponseEntity<User> getuser(@RequestParam("username") String username){
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    @Operation(summary="Cập nhật user")
    public ResponseEntity<User> updateProfile(@RequestBody UpdateProfileRequest request){
        User user = userService.updateUser(request);

        return ResponseEntity.ok(user);
    }

    // @PutMapping("/password")
    // public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request){
    //     userService.changePassword(request);
    //     return ResponseEntity.ok(new MessageResponse("Change Password Success!"));
    // }
}
