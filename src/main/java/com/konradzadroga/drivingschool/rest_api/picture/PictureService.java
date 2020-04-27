package com.konradzadroga.drivingschool.rest_api.picture;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.konradzadroga.drivingschool.config.aws.AmazonClient;
import com.konradzadroga.drivingschool.rest_api.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

@Service
public class PictureService {

    private PictureRepository pictureRepository;
    private AmazonClient amazonClient;
    private UserService userService;
    private AmazonS3 amazonS3;

    @Value("${aws.bucketname}")
    private String bucketName;
    @Value("${aws.bucketendpoint}")
    private String bucketEndpoint;

    public PictureService(PictureRepository pictureRepository, AmazonClient amazonClient, UserService userService) {
        this.pictureRepository = pictureRepository;
        this.amazonClient = amazonClient;
        this.userService = userService;
        this.amazonS3 = amazonClient.getAmazonS3();
    }

    public PictureDTO uploadPictureToS3(MultipartFile multipartFile) {
        PictureDTO pictureDTO = new PictureDTO();
        Picture picture;
        String url = "";
        try {
            File file = convertMultipartToFile(multipartFile);
            String fileName = file.getName();
            url = bucketEndpoint + "/" + fileName;
            if (userService.getCurrentUser().getPicture()!=null) {
                picture = userService.getCurrentUser().getPicture();
                picture.setUrl(url);
            } else {
                picture = new Picture(url, userService.getCurrentUser());
            }
            uploadFileToS3Bucket(fileName, file);
            pictureRepository.save(picture);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There is a problem with file");
        }
        pictureDTO.setUrl(url);

        return pictureDTO;
    }

    public void uploadFileToS3Bucket(String fileName, File file) {
        System.out.println(fileName);
        System.out.println(file);
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public PictureDTO getUserPicture(String username) {
        Picture picture = pictureRepository.findByUserUsername(username).orElseThrow(
                () -> new NoSuchElementException("Picture not found")
        );
        PictureDTO pictureDTO = new PictureDTO(picture.getUrl());

        return pictureDTO;
    }

    private File convertMultipartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(convertedFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return convertedFile;
    }


}
