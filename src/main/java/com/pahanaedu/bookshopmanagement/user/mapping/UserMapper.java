package com.pahanaedu.bookshopmanagement.user.mapping;

import com.pahanaedu.bookshopmanagement.user.model.User;
import com.pahanaedu.bookshopmanagement.user.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public User toEntity(UserDTO userDTO) {
        return User.builder()
                .userId(userDTO.getUserId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }

    public List<UserDTO> toDTOList(List<User> users) {
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<User> toEntityList(List<UserDTO> userDTOs) {
        return userDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
