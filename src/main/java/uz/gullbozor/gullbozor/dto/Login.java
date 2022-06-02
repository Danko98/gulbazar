package uz.gullbozor.gullbozor.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Login {

    @NotNull
    private String userName; //userning takroriy passwordi

    @NotNull
    private String password; //userning passwordi


}
