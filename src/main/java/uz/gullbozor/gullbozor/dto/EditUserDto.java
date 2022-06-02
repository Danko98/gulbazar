package uz.gullbozor.gullbozor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserDto {


    private String userName;
    private String phoneNumber;
    private String oldPassword;
    private String newPassword;


}
