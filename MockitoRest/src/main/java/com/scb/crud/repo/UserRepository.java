package com.scb.crud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scb.crud.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
