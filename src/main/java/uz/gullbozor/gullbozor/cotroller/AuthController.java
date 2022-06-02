package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.RegisterDto;
import uz.gullbozor.gullbozor.dto.Login;
import uz.gullbozor.gullbozor.dto.VerifyDto;
import uz.gullbozor.gullbozor.service.AuthService;
import uz.gullbozor.gullbozor.verifysms.PhoneNumberDto;
import uz.gullbozor.gullbozor.verifysms.PhoneVerificationService;
import uz.gullbozor.gullbozor.verifysms.VerificationResult;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private PhoneVerificationService phoneVerificationService;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody Login login) {
        ApiResponse apiResponse = authService.login(login);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PostMapping("/sendSMSToPhone")
    public HttpEntity<ApiResponse> sendSmsToPhone(@RequestBody PhoneNumberDto phoneNumberDto) {
        ApiResponse apiResponse = authService.sendSmsCode(phoneNumberDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

   @PostMapping("/checkSMSCode")
    public HttpEntity<?> checkSMSCode(@RequestBody VerifyDto verifyDto) {
       ApiResponse apiResponse = authService.checkSMSCode(verifyDto.getSmsCode(), verifyDto.getPhoneNumber());
       return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
   }

   @PostMapping("/registerUser")
    public HttpEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
       ApiResponse apiResponse = authService.registerUser(registerDto);
       return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
   }


}
