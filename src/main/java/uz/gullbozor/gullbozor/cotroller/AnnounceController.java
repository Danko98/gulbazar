package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.AnnounceDto;
import uz.gullbozor.gullbozor.entity.Announce;
import uz.gullbozor.gullbozor.service.AnnounceService;

import java.util.List;

@RestController
@RequestMapping("/announce")
public class AnnounceController {

    @Autowired
    private AnnounceService announceService;

    @GetMapping("/indexPage")
    public ResponseEntity<?> getAnnounceForIndexPage() {

        List<List<?>> announceListOfList = announceService.getAnnounceListOfList();
        return ResponseEntity.ok(announceListOfList);
    }

    @GetMapping("/byShop/{shopId}")
    public ResponseEntity<?> getAnnouncesByShopId(@PathVariable Long shopId, @RequestParam int page) {

        Page<Announce> announceListByShopId = announceService.getAnnounceByShopId(shopId, page);
        return ResponseEntity.ok(announceListByShopId);
    }

    @GetMapping("/announceOfCustomer")
    public ResponseEntity<?> getAnnouncesByShopId(@RequestParam int page) {

        Page<Announce> announceListByShopId = announceService.getAnnounceCustomer(10,page);
        return ResponseEntity.ok(announceListByShopId);
    }

//    @GetMapping("/byUserId/{userId}")
//    public ResponseEntity<?> getAnnounceByUserId(@PathVariable Long userId, @RequestParam int page) {
//        Page<Announce> announcePage = announceService.getAnnounceByUserId(userId, page);
//        return ResponseEntity.ok(announcePage);
//    }

    @PostMapping
    public ResponseEntity<?> addAnnounce(@RequestBody AnnounceDto announceDto) {
        ApiResponse apiResponse = announceService.addAnnounce(announceDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editAnnounce(@RequestBody AnnounceDto announceDto, @PathVariable Long id) {
        ApiResponse apiResponse = announceService.editAnnounce(announceDto,id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnnounceById(@PathVariable Long id) {
        ApiResponse apiResponse = announceService.getAnnounceById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/announceList")
    public List<Announce> getAnnounceList() {
        return announceService.getAnnounceList();
    }

    @GetMapping
    public ResponseEntity<Page<Announce>> getPageAnnounce(@RequestParam int page) {
        Page<Announce> announcePage = announceService.getAnnouncePage(page);
        return ResponseEntity.ok(announcePage);
    }

    @GetMapping("/{flowerType}")
    public ResponseEntity<Page<Announce>> getPageAnnounce(@RequestParam int page, @RequestParam Integer flowerType) {
        Page<Announce> announcePage = announceService.getAnnouncePageByFlowerType(page, flowerType);
        return ResponseEntity.ok(announcePage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnounceById(@PathVariable Long id) {
        ApiResponse apiResponse = announceService.deleteAnnounceById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}

