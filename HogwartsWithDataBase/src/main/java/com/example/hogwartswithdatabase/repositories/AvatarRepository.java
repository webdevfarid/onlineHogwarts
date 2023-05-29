package com.example.hogwartswithdatabase.repositories;

import com.example.hogwartswithdatabase.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar,Integer>
{
        public Avatar getById(int studentId);
        public Avatar findAvatarById(int id);
}
