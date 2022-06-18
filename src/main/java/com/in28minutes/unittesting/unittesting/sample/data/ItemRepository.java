package com.in28minutes.unittesting.unittesting.sample.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.unittesting.unittesting.sample.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
