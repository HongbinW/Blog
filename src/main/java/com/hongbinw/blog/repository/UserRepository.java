package com.hongbinw.blog.repository;

import com.hongbinw.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

}
