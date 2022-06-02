package uz.gullbozor.gullbozor.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.entity.BestFlowerEntity;
import uz.gullbozor.gullbozor.repository.AttachRepo;
import uz.gullbozor.gullbozor.repository.BestFlowerRepo;


@Service
public class BestFlowerService {

    int count;
    int count2;
    boolean aBoolean;
    @Autowired
    private BestFlowerRepo bestFlowerRepo;

    @Autowired
    private AttachRepo attachRepo;



    public ApiResponse addBestFlower(BestFlowerEntity bestFlowerEntityDto) {

                bestFlowerRepo.save(bestFlowerEntityDto);
                return new ApiResponse("Successfully saved",true);
    }

//    public ApiResponse editBestFlower(BestFlowerEntity bestFlowerEntityDto) {
//        if (!attachRepo.existsById(bestFlowerEntityDto.getId())) {
//            return new ApiResponse("Not found attachment",false);
//        }
//        Optional<BestFlowerEntity> optionalBestFlowerEntity = bestFlowerRepo.findById(bestFlowerEntityDto.getId());
//        if (optionalBestFlowerEntity.isPresent()) {
//            BestFlowerEntity bestFlowerEntity = optionalBestFlowerEntity.get();
//
//            bestFlowerEntity.setFlowerValue(((bestFlowerEntityDto.getLike()/bestFlowerEntity.getView()) + (bestFlowerEntity.getLike() % bestFlowerEntity.getView())) * 100);
//            bestFlowerRepo.save(bestFlowerEntityDto);
//            return new ApiResponse("Successfully edited",true);
//
//        }else return new ApiResponse("Not found Flower picture",false);
//
//    }

    public Long getRandomNumber(int max, int min) {
        return (Long) (long)(Math.random() * (max - min + 1) + min);
    }

    public BestFlowerEntity getFirstBestFlower() {

        while(true) {

        if (count > 9) {
            count = 0;
        }

        out:
        switch (count) {


            case 0:
            case 1:
            case 2:

                Long randomNumber2 = getRandomNumber(100, 80);
                BestFlowerEntity byFlowerValue2 = bestFlowerRepo.findByFlowerValue(randomNumber2);
                if (byFlowerValue2 == null) {
                    count++;
                    break out;
                }
                count++;
                return byFlowerValue2;


            case 3:
            case 4:

                Long randomNumber4 = getRandomNumber(80, 60);
                BestFlowerEntity byFlowerValue4 = bestFlowerRepo.findByFlowerValue(randomNumber4);
                if (byFlowerValue4 == null) {
                    count++;
                    break out;
                }
                count++;
                return byFlowerValue4;


            case 5:

                Long randomNumber5 = getRandomNumber(60, 40);
                BestFlowerEntity byFlowerValue5 = bestFlowerRepo.findByFlowerValue(randomNumber5);
                if (byFlowerValue5 == null) {
                    count++;
                    break out;
                }
                count++;
                return byFlowerValue5;


            case 6:
                if (aBoolean) {

                    Long randomNumber6 = getRandomNumber(60, 40);
                    BestFlowerEntity byFlowerValue6 = bestFlowerRepo.findByFlowerValue(randomNumber6);
                    if (byFlowerValue6 == null) {
                        count++;
                        break out;
                    }
                    count++;
                    aBoolean = false;
                    return byFlowerValue6;

                } else {

                    Long randomNumber6 = getRandomNumber(40, 20);
                    BestFlowerEntity byFlowerValue6 = bestFlowerRepo.findByFlowerValue(randomNumber6);
                    if (byFlowerValue6 == null) {
                        count++;
                        break out;
                    }
                    count++;
                    aBoolean = true;
                    return byFlowerValue6;


                }


            case 7:
                if (count2 > 9) {
                    count2 = 0;
                }

                switch (count2) {

                    case 0:
                    case 1:
                    case 2:
                        Long randomNumber7 = getRandomNumber(40, 20);
                        BestFlowerEntity byFlowerValue7 = bestFlowerRepo.findByFlowerValue(randomNumber7);
                        if (byFlowerValue7 == null) {
                            count++;
                            break out;
                        }
                        aBoolean = false;
                        count++;
                        return byFlowerValue7;


                    case 3:
                    case 4:
                    case 5:
                        Long randomNumber8 = getRandomNumber(20, 10);
                        BestFlowerEntity byFlowerValue8 = bestFlowerRepo.findByFlowerValue(randomNumber8);
                        if (byFlowerValue8 == null) {
                            count++;
                            break out;
                        }
                        count++;
                        return byFlowerValue8;


                    case 6:

                        Long randomNumber9 = getRandomNumber(20, 10);
                        BestFlowerEntity byFlowerValue9 = bestFlowerRepo.findByFlowerValue(randomNumber9);
                        if (byFlowerValue9 == null) {
                            count++;
                            break out;
                        }
                        count++;
                        return byFlowerValue9;


                    case 7:
                    case 8:
                    case 9:
                        BestFlowerEntity byViewIsBetween = bestFlowerRepo.findByFlowerValueIsBetween(30L, -1L);
                        if (byViewIsBetween == null) {
                            count++;
                            break out;
                        }
                        count++;
                        return byViewIsBetween;


                }
                break;


            case 8:
            case 9:
                   BestFlowerEntity byView2 = bestFlowerRepo.getLastStudentDetails();
                Long view = byView2.getView();
                byView2.setView(view+1);
                count++;
                bestFlowerRepo.save(byView2);
                return byView2;


            default:
                throw new IllegalStateException("Unexpected value: " + count);
        }


    }


    }

    public BestFlowerEntity getSecondBestFlower(Long flowerValue) {
            Long a = flowerValue, b = flowerValue;

        do{
            a++;
            b--;

            BestFlowerEntity byViewIsBetween = bestFlowerRepo.findByFlowerValueIsBetween(a, b);

            if (byViewIsBetween != null) {
                return byViewIsBetween;
            }

        }while (true);



    }



}
