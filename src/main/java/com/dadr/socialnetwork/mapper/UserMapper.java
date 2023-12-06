package com.dadr.socialnetwork.mapper;

import com.dadr.socialnetwork.dto.ReadUserDto;
import com.dadr.socialnetwork.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    User mapToEntity(ReadUserDto userDto);
    ReadUserDto mapToDto(User user);
}
