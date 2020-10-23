package com.rcl.rclbackend.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rcl.rclbackend.Model.User;
import com.rcl.rclbackend.Repository.UserRepository;
import com.rcl.rclbackend.Service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;



    @Override
    public Optional<User>  findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    @Override
    public void saveUser(User user) {
        if (user.getId() == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setActive(Boolean.TRUE);
        }
        userRepository.save(user);
    }



    @Override
    public Page<User> listUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }



    @Override
    public Page<User> searchByTerm(String name, Pageable pageable) {
        return userRepository.searchByTerm(name, pageable);
    }



    @Override
    public Boolean removeAll() {
        userRepository.deleteAll();
        return Boolean.TRUE;
    }



    @Override
    public void removeById(Long id) {
        userRepository.deleteById(id);
    }



    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }



    @Override
    public List<User> searchBy(String keyword, String criteria) {
        if ("firstName".equals(criteria)) {
            return userRepository.findByFirstNameIgnoreCaseContaining(keyword);
        } else if ("lastName".equals(criteria)) {
            return userRepository.findByLastNameIgnoreCaseContaining(keyword);
        } else if ("email".equals(criteria)) {
            return userRepository.findByEmailIgnoreCaseContaining(keyword);
        }
        else if("username".equals(criteria)) {
        	return userRepository.findByUserNameIgnoreCaseContaining(keyword);
        }
        return new ArrayList<>();

    }



	@Override
	public Optional<User> findUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userRepository.findByUserName(userName);
	}

}
