package uz.gullbozor.gullbozor.cotroller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.entity.BestFlowerEntity;
import uz.gullbozor.gullbozor.service.BestFlowerService;




@RestController
@RequestMapping("/bestFlower")
public class BestFlowerController {


    @Autowired
    private BestFlowerService bestFlowerService;

    @GetMapping("/getFirstBestFlower")
    public ResponseEntity<BestFlowerEntity> getFirstBestFlower() {
        BestFlowerEntity firstBestFlower = bestFlowerService.getFirstBestFlower();
        return ResponseEntity.ok(firstBestFlower);
    }

    @GetMapping("/getSecondBestFlower/{flowerValue}")
    public ResponseEntity<BestFlowerEntity> getSecondBestFlower(@PathVariable Long flowerValue) {
        BestFlowerEntity secondBestFlower = bestFlowerService.getSecondBestFlower(flowerValue);
        return ResponseEntity.ok(secondBestFlower);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addBestFlower(@RequestBody BestFlowerEntity bestFlowerEntity) {
        ApiResponse apiResponse = bestFlowerService.addBestFlower(bestFlowerEntity);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

}
