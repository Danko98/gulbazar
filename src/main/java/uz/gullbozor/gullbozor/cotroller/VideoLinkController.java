package uz.gullbozor.gullbozor.cotroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.entity.VideoLink;
import uz.gullbozor.gullbozor.service.VideoLinkService;

@RestController
@RequestMapping("/videoLink")
public class VideoLinkController {

    @Autowired
    private VideoLinkService videoLinkService;

    @PostMapping
    public ResponseEntity<ApiResponse> addVideoLink(@RequestBody VideoLink videoLink ) {
        ApiResponse apiResponse = videoLinkService.addVideoLink(videoLink);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> editVideoLink(@RequestBody VideoLink videoLink, @PathVariable Long id) {
        ApiResponse apiResponse = videoLinkService.editVideoLink(videoLink, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getVideoLinkById(@PathVariable Long id) {
        ApiResponse apiResponse = videoLinkService.getVideoLinkById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getVideoLinkPage(@RequestParam int page) {
        Page<VideoLink> videoLinkPage = videoLinkService.getVideoLinkPage(page);
        return ResponseEntity.ok(videoLinkPage);
    }

    @GetMapping("/byCategoryId/{categoryId}")
    public ResponseEntity<?> getVideoByCategoryId(@PathVariable Long categoryId, @RequestParam int page) {
        Page<VideoLink> videoPageByCategoryId = videoLinkService.getVideoPageByCategoryId(page, categoryId);
        return ResponseEntity.ok(videoPageByCategoryId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        ApiResponse apiResponse = videoLinkService.deleteById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

}
