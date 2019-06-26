package com.example.demo.interFace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.ItemsEntity;

public interface ItemsInterface extends JpaRepository<ItemsEntity, Integer>,JpaSpecificationExecutor<ItemsEntity>{
}
