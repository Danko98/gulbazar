package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.entity.CityEntity;
import uz.gullbozor.gullbozor.repository.CityRepo;
import uz.gullbozor.gullbozor.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<ApiResponse> addCity(@RequestBody CityEntity cityDto) {
        ApiResponse apiResponse = cityService.addCity(cityDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCity(@RequestBody CityEntity cityDto, @PathVariable Integer id) {
        ApiResponse apiResponse = cityService.editCity(cityDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCityById(@PathVariable Integer id) {
        ApiResponse apiResponse = cityService.getCityById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<CityEntity>> getCityList() {
        List<CityEntity> cityList = cityService.getCityList();
        return ResponseEntity.ok(cityList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCityById(@PathVariable Integer id) {
        ApiResponse apiResponse = cityService.deleteCity(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
