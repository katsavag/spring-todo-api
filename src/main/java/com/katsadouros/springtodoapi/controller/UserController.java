package com.katsadouros.springtodoapi.controller;


import com.katsadouros.springtodoapi.dto.PageDTO;
import com.katsadouros.springtodoapi.mapper.UserMapper;
import com.katsadouros.springtodoapi.model.User;
import com.katsadouros.springtodoapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin("*")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageDTO<User>> getAllUsers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);

        Page<User> userPage = userService.getAllUsers(pageable);

        return new ResponseEntity<>(UserMapper.pageToPageDTO(userPage), HttpStatus.OK);
    }

}
