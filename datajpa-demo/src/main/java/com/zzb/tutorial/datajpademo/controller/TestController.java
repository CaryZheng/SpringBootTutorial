package com.zzb.tutorial.datajpademo.controller;

import com.zzb.tutorial.datajpademo.dao.UserDao;
import com.zzb.tutorial.datajpademo.dto.UserDTO;
import com.zzb.tutorial.datajpademo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test/user")
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserDao userDao;

    @GetMapping("/all")
    public List<User> findAllUser() {
        List<User> result = userDao.findAll();

        return result;
    }

    @GetMapping("/username")
    public List<User> findUserByName(@RequestParam("userName") String userName) {
        List<User> result = userDao.findByUserName(userName);

        return result;
    }

    @GetMapping("/id")
    public User findUserById(@RequestParam("userId") Integer userId) {
        Optional<User> result = userDao.findById(userId);
        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    @GetMapping("/custom")
    public List<User> findUserByCustomInfo(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        List<User> result = userDao.findByUserNameAndPassword(userName, password);

        return result;
    }

    @GetMapping("/custom2")
    public User findUserByCustomInfo2(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        User result = userDao.findByCustomInfo2(userName, password);

        return result;
    }

    @GetMapping("/custom3")
    public User findUserByCustomInfo3(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        User result = userDao.findByCustomInfo3(userName, password);

        return result;
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody User user) {
        User result = userDao.save(user);

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(result, userDTO);

        return userDTO;
    }

    @PostMapping("/update")
    public User updateUser(@RequestBody User user) {
        User result = userDao.save(user);

        return result;
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("userId") Integer userId) {
        User user = new User();
        user.setId(userId);

        userDao.delete(user);

        return "Delete success";
    }
}
