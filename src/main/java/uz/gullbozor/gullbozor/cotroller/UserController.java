package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.EditUserDto;
import uz.gullbozor.gullbozor.dto.VerifyDto;
import uz.gullbozor.gullbozor.dto.RegisterDto;
import uz.gullbozor.gullbozor.entity.UserEntity;
import uz.gullbozor.gullbozor.service.UserService;
import uz.gullbozor.gullbozor.verifysms.PhoneVerificationService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private PhoneVerificationService phoneVerificationService;


    @PostMapping("/sendOtp")
    public ResponseEntity<?> sendOtpToUser(@RequestBody VerifyDto verifyDto) {
        ApiResponse apiResponse = userService.sendSmsToUser(verifyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }


    @PostMapping("/ddddd")   // user kiritgan raqamga sms jo'natish
    public ResponseEntity<? > verifyOtp(@RequestBody RegisterDto registerDto) {
        ApiResponse apiResponse = userService.checkUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }




    @PutMapping("/{id}")  // userni tahrirlash
    public ResponseEntity<ApiResponse> editUser(EditUserDto userDto, @PathVariable Long id) {
        ApiResponse apiResponse = userService.editUser(userDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")  // userni bittasini id orqali chaqirish
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id) {
        ApiResponse apiResponse = userService.getUserById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping      // userlar ro'yxatini olish
    public ResponseEntity<List<UserEntity>> getUserList() {
        List<UserEntity> userList = userService.getUserList();
        return ResponseEntity.ok(userList);
    }

    @DeleteMapping("/{id}") // userni id si orqali o'chirish
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        ApiResponse apiResponse = userService.deleteUserById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
