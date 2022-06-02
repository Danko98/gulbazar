package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.entity.RegionEntity;
import uz.gullbozor.gullbozor.service.RegionService;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping
    public ResponseEntity<ApiResponse> addRegion(@RequestBody RegionEntity regionDto) {
        ApiResponse apiResponse = regionService.addRegion(regionDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editRegion(@RequestBody RegionEntity regionDto, @PathVariable Integer id) {
        ApiResponse apiResponse = regionService.editRegion(regionDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getRegionById(@PathVariable Integer id) {
        ApiResponse apiResponse = regionService.getRegionById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<RegionEntity>> getRegionList() {
        List<RegionEntity> regionList = regionService.getRegionList();
        return ResponseEntity.ok(regionList);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteRegionById(@PathVariable Integer id) {
        ApiResponse apiResponse = regionService.deleteById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
