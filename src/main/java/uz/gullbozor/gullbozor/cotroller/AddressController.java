package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.AddressDto;
import uz.gullbozor.gullbozor.entity.AddressEntity;
import uz.gullbozor.gullbozor.service.AddressService;

import java.util.List;


@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @PostMapping
    public ResponseEntity<ApiResponse> addAddress(@RequestBody AddressDto addressDto) {
        ApiResponse apiResponse = addressService.addAddress(addressDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editAddress(@RequestBody AddressDto addressDto, @PathVariable Long id) {
        ApiResponse apiResponse = addressService.editAddress(addressDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAddressById(@PathVariable Long id) {
        ApiResponse apiResponse = addressService.getAddressById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<AddressEntity>> getAddressPage() {
        List<AddressEntity> addressList = addressService.getAddressList();
        return ResponseEntity.ok(addressList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAddressById(@PathVariable Long id) {
        ApiResponse apiResponse = addressService.deleteAddressById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
