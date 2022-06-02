package uz.gullbozor.gullbozor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.entity.VideoLink;
import uz.gullbozor.gullbozor.repository.CategoryRepo;
import uz.gullbozor.gullbozor.repository.VideoLinkRepo;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class VideoLinkService {


    @Autowired
    private VideoLinkRepo videoLinkRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    public ApiResponse addVideoLink(@NotNull VideoLink videoLinkDto) {
        if (!categoryRepo.existsById(videoLinkDto.getCategoryId())) {
            return new ApiResponse("Not found category",false);
        }

        VideoLink videoLink = new VideoLink();
        videoLink.setVideoLink(videoLinkDto.getVideoLink());
        videoLink.setCategoryId(videoLinkDto.getCategoryId());
        videoLinkRepo.save(videoLink);
        return new ApiResponse("Successfully saved",true);
    }

    public ApiResponse editVideoLink(@NotNull VideoLink videoLinkDto, Long id) {
        if (!videoLinkRepo.existsById(id)) {
            return new ApiResponse("Not found Video Link",false);
        }
        if (!categoryRepo.existsById(videoLinkDto.getCategoryId())) {
            return new ApiResponse("Not found category",false);
        }

        Optional<VideoLink> optionalVideoLink = videoLinkRepo.findById(videoLinkDto.getId());


        VideoLink videoLink = optionalVideoLink.get();
        videoLink.setVideoLink(videoLinkDto.getVideoLink());
        videoLink.setCategoryId(videoLinkDto.getCategoryId());
        videoLinkRepo.save(videoLink);
        return new ApiResponse("Successfully edited",true);
    }

    public ApiResponse getVideoLinkById(@NotNull Long id) {
        if (!videoLinkRepo.existsById(id)) {
            return new ApiResponse("Not found Video Link",false);
        }
        Optional<VideoLink> optionalVideoLink = videoLinkRepo.findById(id);
        return new ApiResponse(optionalVideoLink.get());
    }

    // barcha video linklarni page qilib qaytarish uchun
    public Page<VideoLink> getVideoLinkPage(int page) {
        Pageable pageable = PageRequest.of(page, 30);
        Page<VideoLink> videoLinkPage = videoLinkRepo.findAll(pageable);
        return videoLinkPage;
    }

    // Categorya id si orqali videoLinklarni page qilib qaytarish uchun
    public Page<VideoLink> getVideoPageByCategoryId(int page, Long categoryId) {
        Pageable pageable = PageRequest.of(page, 30);
        Page<VideoLink> videoLinkPage = videoLinkRepo.findAllByCategoryId(categoryId, pageable);
        return videoLinkPage;
    }

    public ApiResponse deleteById(Long id) {
        if (!videoLinkRepo.existsById(id)) {
            return new ApiResponse("Not found Video Link",false);
        }
        videoLinkRepo.deleteById(id);
        return new ApiResponse("Successfully deleted",true);
    }


}