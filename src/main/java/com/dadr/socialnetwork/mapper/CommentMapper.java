package com.dadr.socialnetwork.mapper;

import com.dadr.socialnetwork.dto.CommentDto;
import com.dadr.socialnetwork.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper MAPPER = Mappers.getMapper(CommentMapper.class);
    Comment mapToEntity(CommentDto commentDto);
    CommentDto mapToDto(Comment comment);
}
