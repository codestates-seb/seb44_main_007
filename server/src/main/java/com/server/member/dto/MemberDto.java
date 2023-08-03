package com.server.member.dto;

import com.server.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String name;
        @NotBlank
        private String password;
        @NotBlank
        private String phone;
        private LocalDate createdAt;
        private String address;
        private String imageURL;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        private Long memberId;
        private String name;
        private String password;
        private String phone;
        private String address;
        private String imageURL;
        private Boolean premium;

        public Patch addMemberId(Long memberId) {
            Assert.notNull(memberId, "Member Id must not be Null");
            this.memberId = memberId;
            return this;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class PatchPassword {

        private Long memberId;
        @NotBlank
        private String currentPassword;
        @NotBlank
        private String newPassword;

        public PatchPassword addMemberId(Long memberId){
            Assert.notNull(memberId, "Member Id must not beNull");
            this.memberId = memberId;
            return this;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long memberId;
        private String email;
        private String name;
        private String phone;
        private LocalDate createdAt;
        private String address;
        private String imageURL;
        private Boolean premium;

        public static Response response(Member member) {
            return Response.builder()
                    .memberId(member.getMemberId())
                    .email(member.getEmail())
                    .name(member.getName())
                    .phone(member.getPhone())
                    .createdAt(member.getCreatedAt())
                    .address(member.getAddress())
                    .imageURL(member.getImageURL())
                    .premium(member.getPremium())
                    .build();
        }
    }

}
