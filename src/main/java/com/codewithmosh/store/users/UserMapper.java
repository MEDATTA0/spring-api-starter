package com.codewithmosh.store.users;

import com.codewithmosh.store.users.dto.request.UserCreateRequest;
import com.codewithmosh.store.users.dto.response.UserDto;
import com.codewithmosh.store.users.entities.User;
import org.mapstruct.Mapper;

/**
 * UserMapper
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserCreateRequest dto);
}
