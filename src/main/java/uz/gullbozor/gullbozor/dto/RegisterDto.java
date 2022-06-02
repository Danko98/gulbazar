package uz.gullbozor.gullbozor.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
public class RegisterDto {

    @Size(min = 3,max = 50)
    private String userName;// ismi

    @Size(min = 9,max = 16)
    private String phoneNumber; // telefon raqami (+998901234567)


    @Size(min = 5,max = 40)
    private String password; // yangi parol

//    @NotNull
//    @Size(min = 6,max = 6)
//    private String otp; // sms code 6 xonalik (OTP)





}
