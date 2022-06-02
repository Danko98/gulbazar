package uz.gullbozor.gullbozor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.AnnounceDto;
import uz.gullbozor.gullbozor.entity.Announce;
import uz.gullbozor.gullbozor.entity.Category;
import uz.gullbozor.gullbozor.entity.UserEntity;
import uz.gullbozor.gullbozor.repository.AnnounceRepo;
import uz.gullbozor.gullbozor.repository.CategoryRepo;
import uz.gullbozor.gullbozor.repository.UserRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AnnounceService {

    @Autowired
    private AnnounceRepo announceRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepo userRepo;

    public ApiResponse addAnnounce(AnnounceDto announceDto) {

//        if (!categoryRepo.existsById(announceDto.getCategoryId())) {
//            return new ApiResponse("Not found announce",false);
//        }
//
//        if (!userRepo.existsById(announceDto.getSellerId())) {
//            return new ApiResponse("Not found Seller",false);
//        }
//
//
//        Optional<Category> optionalCategory = categoryRepo.findById(announceDto.getCategoryId());
//        Optional<UserEntity> optionalSellerEntity = userRepo.findById(announceDto.getSellerId());

        Announce announce = new Announce();






        announce.setFlowerType(announceDto.getFlowerType());
        announce.setActive(true);
//        announce.setCategory(optionalCategory.get());
//        announce.setUserEntity(optionalSellerEntity.get());
        announce.setDescription(announceDto.getDescription());
        announce.setDiameter(announceDto.getDiameter());
        announce.setHeight(announceDto.getHeight());
        announce.setPrice(announceDto.getPrice());
        announce.setTitle(announceDto.getTitle());
        announce.setWeight(announceDto.getWeight());
        announce.setWithFertilizer(announceDto.isWithFertilizer());
        announce.setWithPot(announceDto.isWithPot());

        announceRepo.save(announce);
        return new ApiResponse("Successfully saved",true);

    }

    public ApiResponse editAnnounce(AnnounceDto announceDto, Long id) {

        if (!announceRepo.existsById(id)) {
            return new ApiResponse("Not found announce",false);
        }

        if (!categoryRepo.existsById(announceDto.getCategoryId())) {
            return new ApiResponse("Not found announce",false);
        }

        if (!userRepo.existsById(announceDto.getSellerId())) {
            return new ApiResponse("Not found Serller",false);
        }


        Optional<Category> optionalCategory = categoryRepo.findById(announceDto.getCategoryId());
        Optional<UserEntity> optionalSellerEntity = userRepo.findById(announceDto.getSellerId());

        Optional<Announce> optionalAnnounce = announceRepo.findById(id);

        Announce announce = optionalAnnounce.get();


        announce.setFlowerType(announceDto.getFlowerType());
        announce.setActive(announceDto.isActive());
//        announce.setCategory(optionalCategory.get());
//        announce.setUserEntity(optionalSellerEntity.get());
        //announce.setAttachMain();
        announce.setDescription(announceDto.getDescription());
        announce.setDiameter(announceDto.getDiameter());
        announce.setHeight(announceDto.getHeight());
        announce.setPrice(announceDto.getPrice());
        announce.setTitle(announceDto.getTitle());
        announce.setWeight(announceDto.getWeight());
        announce.setWithFertilizer(announceDto.isWithFertilizer());
        announce.setWithPot(announceDto.isWithPot());

        announceRepo.save(announce);
        return new ApiResponse("Successfully edited",true);

    }

    public List<List<?>> getAnnounceListOfList() {

        List<List<?>> allAnnounceLists = new ArrayList<>();

        List<Category> categoryList = categoryService.categoryParentList();

        allAnnounceLists.add(categoryList);


        return Collections.singletonList(allAnnounceLists);

    }  // asosiy index page ga 5 turdadagi elonlarni 7 ta dan va 5 ta asosiy categoryani jo'natadi.



    public Page<Announce> getAnnounceCustomer(Integer flowerType,int page) {
        Pageable pageable = PageRequest.of(page, 40);
        Page<Announce> announcesOfCustomers = announceRepo.findAllByFlowerType(pageable, flowerType);
            return announcesOfCustomers;
    }

    public ApiResponse getAnnounceById(Long id) {
        if (!announceRepo.existsById(id)) {
            return new ApiResponse("Not found announce",false);
        }
        Optional<Announce> optionalAnnounce = announceRepo.findById(id);
        return new ApiResponse(optionalAnnounce.get());
    }

    public Page<Announce> getAnnouncePage(int page) {

        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.ASC,"create_at"));
        return announceRepo.findAll(pageable);
    }

    public List<Announce> getAnnounceList() {
        return announceRepo.findAll();
    }

    public Page<Announce> getAnnounceByShopId(Long shopId, int page) {

        Pageable pageable = PageRequest.of(page, 40);
        return announceRepo.findAllByShopId(shopId, pageable);


    }




    public Page<Announce> getAnnouncePageByFlowerType(int page, Integer flowerType) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.ASC,"create_at"));
        return announceRepo.findAllByFlowerType(pageable,flowerType);
    }

    public ApiResponse deleteAnnounceById(Long id) {
        if (!announceRepo.existsById(id)) {
            return new ApiResponse("Not found announce",false);
        }
        announceRepo.deleteById(id);
        return new ApiResponse("Successfully deleted",true);
    }


//    public Page<Announce> getAnnounceByUserId(Long userId,int page) {
//        Pageable pageable = PageRequest.of(page,10);
//        Page<Announce> announcePage = announceRepo.findAllByUserEntity_Id(userId, pageable);
//        return announcePage;
//    }
}
