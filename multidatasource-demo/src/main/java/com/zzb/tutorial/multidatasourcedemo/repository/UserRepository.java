package com.zzb.tutorial.multidatasourcedemo.repository;

import com.zzb.tutorial.multidatasourcedemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
