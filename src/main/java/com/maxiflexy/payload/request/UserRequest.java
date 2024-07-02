package com.maxiflexy.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "Firstname must not be blank")
    @Size(min = 2, max = 125, message = "Firstname must be at least 2 characters long")
    private String firstName;

    private String lastName;

    private String otherName;

    private String gender;

    private String email;

    private String password;

    private String address;

    private String stateOfOrigin;

    private String bvn;

    private String phoneNumber;

    private String pin;
}
