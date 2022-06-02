package uz.gullbozor.gullbozor.cotroller;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.gullbozor.gullbozor.entity.BestFlowerAttach;
import uz.gullbozor.gullbozor.entity.MainAttach;
import uz.gullbozor.gullbozor.repository.AttachRepo;
import uz.gullbozor.gullbozor.repository.BestFlowerAttachRepo;
import uz.gullbozor.gullbozor.repository.MainAttachRepo;
import uz.gullbozor.gullbozor.service.AttachService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@RestController
@RequestMapping("/attachment")
public class  AttachmentController {

    @Autowired
    private AttachRepo attachRepo;

    @Autowired
    private BestFlowerAttachRepo bestFlowerAttachRepo;

    @Autowired
    private MainAttachRepo mainAttachRepo;

    @Autowired
    private AttachService attachService;

    private static final String uploadDirectory= "yuklanganlar";
    private static final String competitionUploadDirectory= "tanlovdagirasmlar";




    @GetMapping(value = "/open/{mainAttachId}", produces = MediaType.ALL_VALUE)
    public byte[] getImageByte(@PathVariable("mainAttachId") Long  mainAttachId) {
        return attachService.open_general(mainAttachId);
    }

    @GetMapping(value = "/bestFlower/open/{bestFlowerId}", produces = MediaType.ALL_VALUE)
    public byte[] getBestFlowerImageByte(@PathVariable("bestFlowerId") Long  bestFlowerId) {
        return attachService.openBestFlower(bestFlowerId);
    }

    @PostMapping("/uploadImage")
    public String uploadToSystem(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();
        StringBuilder imageIds = new StringBuilder();

        while (fileNames.hasNext()) {

                MultipartFile file = request.getFile(fileNames.next());
                if (file != null) {
                    MainAttach mainAttach = new MainAttach();
                    mainAttach.setContentType(file.getContentType());
                    mainAttach.setSize(file.getSize());
                    mainAttach.setFileOriginalName(file.getOriginalFilename());
                    String originalFilename = file.getOriginalFilename();
                    String[] split = originalFilename.split("\\.");
                    String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
                    mainAttach.setName(name);
                    mainAttachRepo.save(mainAttach);
                    Path path = Paths.get(uploadDirectory + "/" + name);
                    Files.copy(file.getInputStream(), path);
                    imageIds.append(mainAttach.getName()+"_");
                }
            }
        String result = imageIds.substring(0, imageIds.length() - 1);

        return imageIds.toString();
    }

    @PostMapping("/bestFlower/upload")
    public ResponseEntity<?> uploadBestFlowerToSystem(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();
        Long bestFlowerAttachId;

                MultipartFile file = request.getFile(fileNames.next());
                if (file != null) {
                    BestFlowerAttach bestFlowerAttach = new BestFlowerAttach();
                    bestFlowerAttach.setContentType(file.getContentType());
                    bestFlowerAttach.setSize(file.getSize());
                    bestFlowerAttach.setFileOriginalName(file.getOriginalFilename());
                    String originalFilename = file.getOriginalFilename();
                    String[] split = originalFilename.split("\\.");
                    String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
                    bestFlowerAttach.setName(name);
                    bestFlowerAttachRepo.save(bestFlowerAttach);
                    Path path = Paths.get(competitionUploadDirectory + "/" + name);
                    Files.copy(file.getInputStream(), path);
                    bestFlowerAttachId = bestFlowerAttach.getId();
                }else {
                    return ResponseEntity.ok("No picture came");
                }
        return ResponseEntity.ok(bestFlowerAttachId);
    }






}
