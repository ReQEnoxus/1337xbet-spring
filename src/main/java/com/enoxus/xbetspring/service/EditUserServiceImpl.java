package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.EditDto;
import com.enoxus.xbetspring.dto.UserDto;
import com.enoxus.xbetspring.entity.User;
import com.enoxus.xbetspring.repositories.FileInfoRepository;
import com.enoxus.xbetspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EditUserServiceImpl implements EditUserService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileInfoRepository fileInfoRepository;


    @Override
    public void editUser(EditDto dto) {

        Optional<UserDto> userDto = userService.getCurrentUser();

        if (userDto.isPresent()) {

            Optional<User> userCandidate = userRepository.findById(userDto.get().getId());

            if (userCandidate.isPresent()) {
                User userToEdit = userCandidate.get();

                userToEdit.setBalance(dto.getBalance());
                userToEdit.setName(dto.getName());
                userToEdit.setLastName(dto.getLastName());
                userToEdit.setAvatar(fileInfoRepository.findOneByStorageFileName(dto.getAvatarPath()));

                userRepository.save(userToEdit);
            }
        }

    }
}
