package com.sku.codesnippetshop.domain.member.dto;

import com.sku.codesnippetshop.domain.member.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberReadDTO {

    private String username;
    private String password;
    private String name;
    private String grade;
    private UserRole role;
    private String currentAddress;
    private LocalDateTime regDt;
    private LocalDateTime modDt;


}
