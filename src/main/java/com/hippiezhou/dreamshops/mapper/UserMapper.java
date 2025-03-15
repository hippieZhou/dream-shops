package com.hippiezhou.dreamshops.mapper;

import com.hippiezhou.dreamshops.dto.user.UserCreateResponse;
import com.hippiezhou.dreamshops.dto.user.UserGetResponse;
import com.hippiezhou.dreamshops.dto.user.UserUpdateResponse;
import com.hippiezhou.dreamshops.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    UserCreateResponse toCreateResponse(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    UserUpdateResponse toUpdateResponse(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    UserGetResponse toGetResponse(User user);
}
