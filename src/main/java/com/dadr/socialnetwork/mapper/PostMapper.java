package com.dadr.socialnetwork.mapper;

import com.dadr.socialnetwork.dto.PostDto;
import com.dadr.socialnetwork.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper MAPPER = Mappers.getMapper(PostMapper.class);
    Post mapToEntity(PostDto postDto);
    PostDto mapToDto(Post post);
}
