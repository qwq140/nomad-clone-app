package com.cos.nomadapp.model.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginRespDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String roles;
    private String provider;
    private String token;

}
