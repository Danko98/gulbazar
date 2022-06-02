package uz.gullbozor.gullbozor.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.Login;
import uz.gullbozor.gullbozor.dto.RegisterDto;
import uz.gullbozor.gullbozor.entity.UserEntity;
import uz.gullbozor.gullbozor.entity.enums.RoleName;
import uz.gullbozor.gullbozor.repository.RoleRepo;
import uz.gullbozor.gullbozor.repository.UserRepo;
import uz.gullbozor.gullbozor.security.JwtProvider;
import uz.gullbozor.gullbozor.verifysms.PhoneNumberDto;
import uz.gullbozor.gullbozor.verifysms.PhoneVerificationService;
import uz.gullbozor.gullbozor.verifysms.VerificationResult;

import java.util.Collections;


@Service
public class AuthService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    PhoneVerificationService phoneVerificationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    public ApiResponse login(Login login) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    login.getUserName(),
                    login.getPassword()
            ));
            System.out.println(authenticate);
            UserEntity user = (UserEntity) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(login.getUserName(), user.getRoles());
            return new ApiResponse(token);
        } catch (BadCredentialsException badCredentialsException) {
            return new ApiResponse("Parol yoki login hato",false);
        }
    }



    public ApiResponse checkSMSCode(String code, String phoneNumber) {
        VerificationResult result = phoneVerificationService.checkVerification(phoneNumber, code);
        if (result.isValid()) {
            return new ApiResponse("sms code is valid",true);
        }else {
            return new ApiResponse("sms code is not valid",false);
        }
    }

    public ApiResponse registerUser(RegisterDto registerDto) {

      if (userRepo.existsByPhoneNumber(registerDto.getPhoneNumber())) {
            return new ApiResponse("This phone number is registered",false);
        }

              UserEntity user = new UserEntity();
              user.setPhoneNumber(registerDto.getPhoneNumber());
              user.setUserName(registerDto.getUserName());
              user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
              user.setRoles(Collections.singleton(roleRepo.findByName(RoleName.ROLE_USER)));
              user.setEnabled(true);

              userRepo.save(user);

          return new ApiResponse("Successfully create account",true);

    }

    public ApiResponse sendSmsCode(PhoneNumberDto phoneNumberDto) {
        VerificationResult result = phoneVerificationService.startVerification(phoneNumberDto.getPhoneNumber());
        if (result.isValid()) {
            return new ApiResponse("Otp sent",true);
        }else {
            return new ApiResponse("Otp was not sent",false);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String  username) throws UsernameNotFoundException {

//        Optional<UserEntity> optionalUserEntity = userRepo.findByUserName(username);
//        if (optionalUserEntity.isPresent()) {
//            return optionalUserEntity.get();
//        }
//        else {
//            throw new UsernameNotFoundException(username + "Not found");
//        }

        return userRepo.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username + "Not found"));

    }


}
