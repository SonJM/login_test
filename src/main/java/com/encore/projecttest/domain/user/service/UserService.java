package com.encore.projecttest.domain.user.service;

import com.encore.projecttest.domain.user.Role;
import com.encore.projecttest.domain.user.User;
import com.encore.projecttest.domain.user.dto.UserInfoDto;
import com.encore.projecttest.domain.user.dto.UserSignUpDto;
import com.encore.projecttest.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(UserSignUpDto userSignUpDto) throws Exception {

        if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (userRepository.findByNickname(userSignUpDto.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        User user = User.builder()
                .email(userSignUpDto.getEmail())
                .password(userSignUpDto.getPassword())
                .nickname(userSignUpDto.getNickname())
                .age(userSignUpDto.getAge())
                .city(userSignUpDto.getCity())
                .role(Role.USER)
                .build();

        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
    }

    public UserInfoDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(EntityExistsException::new);
        UserInfoDto userInfoDto = new UserInfoDto(
                user.getEmail(),
                user.getNickname(),
                user.getAge(),
                user.getCity()
        );
        return userInfoDto;
    }
}