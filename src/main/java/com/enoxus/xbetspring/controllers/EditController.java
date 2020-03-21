package com.enoxus.xbetspring.controllers;

import com.enoxus.xbetspring.dto.EditDto;
import com.enoxus.xbetspring.dto.UserDto;
import com.enoxus.xbetspring.service.EditUserService;
import com.enoxus.xbetspring.service.FileStorageService;
import com.enoxus.xbetspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
public class EditController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private EditUserService editUserService;

    @PostMapping("/edit")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, EditDto dto) {
        Optional<UserDto> user = userService.getCurrentUser();
        if (user.isPresent()) {
            String filePath = fileStorageService.saveFile(file); // saving user avi
            dto.setAvatarPath(filePath);
            editUserService.editUser(dto);
            return "redirect:/user";
        } else {
            return null;
        }
    }
}
