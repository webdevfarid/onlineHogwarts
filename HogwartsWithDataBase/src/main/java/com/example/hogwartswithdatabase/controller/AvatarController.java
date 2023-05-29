package com.example.hogwartswithdatabase.controller;
import com.example.hogwartswithdatabase.model.Avatar;
import com.example.hogwartswithdatabase.service.AvatarService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class AvatarController {
    final
    AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<String> uploadAvatar(@PathVariable int studentId, @RequestParam MultipartFile avatarFile) throws IOException
    {
        avatarService.uploadAvatar(studentId, avatarFile);
        return ResponseEntity.ok().build();
    }

//    @GetMapping(value = "/{id}/avatar-from-db")
//    public ResponseEntity<byte[]> downloadAvatar(@PathVariable int id) {
//        Avatar avatar = new Avatar();// получение объекта аватара
//        byte[] data = avatar.getData();
//
//        if (data != null) {
//            // Обработка данных
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_PNG);
//            headers.setContentLength(data.length);
//            return new ResponseEntity<>(data, headers, HttpStatus.OK);
//        } else {
//            // Обработка случая, когда data равно null
//            // возвращение пустого ответа или генерация ошибки
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Пример возврата ошибки 404
//        }
//    }

        @GetMapping(value = "/{id}/avatar-from-db")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable int id)
        {
        Avatar avatar = avatarService.findAvatarById(id);

        return ResponseEntity.status(HttpStatus.OK).build();

    }
    @GetMapping(value = "/{id}/avatar-from-file")
    public void downloadAvatar(@PathVariable int id, HttpServletResponse response) throws IOException{
        Avatar avatar = avatarService.findAvatarById(id);
        Path path = Path.of(avatar.getFilePath());
        try(InputStream is = Files.newInputStream(path);
            OutputStream os = response.getOutputStream();)
        {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }
}

