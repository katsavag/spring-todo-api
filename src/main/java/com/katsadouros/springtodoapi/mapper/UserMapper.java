package com.katsadouros.springtodoapi.mapper;

import com.katsadouros.springtodoapi.dto.PageDTO;
import com.katsadouros.springtodoapi.model.User;
import org.springframework.data.domain.Page;

public final class UserMapper {

    public static PageDTO<User> pageToPageDTO(Page<User> userPage){
        PageDTO<User> pageDTO = new PageDTO<User>();
        pageDTO.setData(userPage.getContent());
        pageDTO.setPage(userPage.getNumber());
        pageDTO.setSize(userPage.getSize());
        pageDTO.setTotalPages(userPage.getTotalPages());

        return pageDTO;
    }

}
