package uz.gullbozor.gullbozor.dto;

import lombok.Getter;
import lombok.Setter;
import uz.gullbozor.gullbozor.entity.AddressEntity;


@Getter
@Setter
public class ShopDto {

    private String shopName;
    private Long sellerId;
    private Long cardNumber;
    private String phoneNumber;
    private Long addressId;
}
