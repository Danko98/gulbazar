package uz.gullbozor.gullbozor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.ShopDto;
import uz.gullbozor.gullbozor.entity.AddressEntity;
import uz.gullbozor.gullbozor.entity.ShopEntity;
import uz.gullbozor.gullbozor.repository.AddressRepo;
import uz.gullbozor.gullbozor.repository.ShopRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepo shopRepo;

    @Autowired
    private AddressRepo addressRepo;


    public ApiResponse createShop(ShopDto shopDto) {

        if (shopRepo.existsByShopName(shopDto.getShopName())) {
            return new ApiResponse("This shop name already exists",false);
        }

        if (!addressRepo.existsById(shopDto.getAddressId())) {
            return new ApiResponse("Not found address",false);
        }

        Optional<AddressEntity> optionalAddress = addressRepo.findById(shopDto.getAddressId());

        ShopEntity shop = new ShopEntity();
        shop.setShopName(shopDto.getShopName());
        shop.setCardNumber(shopDto.getAddressId());
        shop.setSellerId(shopDto.getSellerId());
        shop.setPhoneNumber(shopDto.getPhoneNumber());
        shop.setAddressEntity(optionalAddress.get());
        return new ApiResponse("Successfully created",true);

    }

    public ApiResponse editShop(ShopDto shopDto, Long id) {

        if (!shopRepo.existsById(id)) {
            return new ApiResponse("Not found shop",false);
        }

        if (shopRepo.existsByShopName(shopDto.getShopName())) {
            return new ApiResponse("This shop name already exists",false);
        }

        if (!addressRepo.existsById(shopDto.getAddressId())) {
            return new ApiResponse("Not found address",false);
        }
        Optional<ShopEntity> optionalShop = shopRepo.findById(id);

        Optional<AddressEntity> optionalAddress = addressRepo.findById(shopDto.getAddressId());

        ShopEntity shop = optionalShop.get();
        shop.setShopName(shopDto.getShopName());
        shop.setCardNumber(shopDto.getAddressId());
        shop.setSellerId(shopDto.getSellerId());
        shop.setPhoneNumber(shopDto.getPhoneNumber());
        shop.setAddressEntity(optionalAddress.get());
        return new ApiResponse("Successfully created",true);

    }

    public ApiResponse getShopById(Long id) {
        if (!shopRepo.existsById(id)) {
            return new ApiResponse("Not found shop",false);
        }

        return new ApiResponse(shopRepo.findById(id).get());

    }

    public List<ShopEntity> getShopList() {
        return shopRepo.findAll();
    }

    public Page<ShopEntity> getShopPage(int page) {
        Pageable pageable = PageRequest.of(page, 12);
        return shopRepo.findAll(pageable);
    }

    public ApiResponse deleteShopById(Long id) {
        if (!shopRepo.existsById(id)) {
            return new ApiResponse("Not found shop",false);
        }
        shopRepo.deleteById(id);
        return new ApiResponse("Successfully deleted",true);
    }

}
