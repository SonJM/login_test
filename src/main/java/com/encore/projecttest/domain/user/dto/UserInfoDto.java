package com.encore.projecttest.domain.user.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserInfoDto {
    private String email;
    private String nickname;
    private int age;
    private String city;
}
