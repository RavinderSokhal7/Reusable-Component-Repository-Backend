package com.rcl.rclbackend.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rcl.rclbackend.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
	Optional<User> findUserByEmail(String email);

	Optional<User> findUserByUserName(String username);
    
    void saveUser(User user);

    Boolean removeAll();

    void removeById(Long id);

    Optional<User> findById(Long id);

    Page<User> searchByTerm(String name, Pageable pageable);

    Page<User> listUsers(Pageable pageable);

    List<User> searchBy(String keyword, String criteria);
}
