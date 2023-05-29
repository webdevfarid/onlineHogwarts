package com.example.hogwartswithdatabase.service;


import com.example.hogwartswithdatabase.model.Avatar;
import com.example.hogwartswithdatabase.model.Student;
import com.example.hogwartswithdatabase.repositories.AvatarRepository;
import com.example.hogwartswithdatabase.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;


@Service
public class AvatarService
{
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
    final StudentRepository studentRepository;
    final AvatarRepository avatarRepository;


    public AvatarService(StudentRepository studentRepository, AvatarRepository avatarRepository)
    {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;

    }



    public void uploadAvatar( int studentId, MultipartFile avatarFile) throws  IOException
    {
        Student student=studentRepository.getById(studentId);
        Path filepath=Path.of(avatarsDir,student+"."+getExtensions(avatarFile.getOriginalFilename()));

            Files.createDirectories(filepath);
            Files.deleteIfExists(filepath);
            try(
                    InputStream is= avatarFile.getInputStream();
                    OutputStream os=Files.newOutputStream(filepath,CREATE_NEW);
                    BufferedInputStream bis=new BufferedInputStream(is,1024);
                    BufferedOutputStream bos=new BufferedOutputStream(os,1024);
                    )
            {
                bis.transferTo(bos);
            }

            Avatar avatar=new Avatar();
            findAvatarById(studentId);
            avatar.setStudent(student);
            avatar.setFilePath(filepath.toString());
            avatar.setFileSize((int) avatarFile.getSize());
            avatar.setMediaType(avatarFile.getContentType());
            avatar.setData(avatar.getData());
            avatarRepository.save(avatar);
    }
    public Avatar findAvatarById(int studentId)
    {
        return avatarRepository.findAvatarById(studentId);
    }
    private String getExtensions(String fileName)
    {
        return  fileName.substring(fileName.lastIndexOf(".")+1);
    }

}
