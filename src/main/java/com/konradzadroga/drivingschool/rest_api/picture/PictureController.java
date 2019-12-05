package com.konradzadroga.drivingschool.rest_api.picture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PictureController {
    private PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @RequestMapping(method = RequestMethod.POST, value="pictures/upload", produces = "application/json")
    public ResponseEntity<PictureDTO> uploadPicture(@RequestPart(value="file") MultipartFile file) {
        PictureDTO picture = pictureService.uploadPictureToS3(file);
        return new ResponseEntity<>(picture, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value="pictures/{username}")
    public ResponseEntity<PictureDTO> getUserPicture(@PathVariable String username) {
        PictureDTO picture = pictureService.getUserPicture(username);
        return new ResponseEntity<>(picture, HttpStatus.OK);
    }

}
