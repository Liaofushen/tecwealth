package com.lfs.web.apitest;

import com.lfs.web.beans.domain.User;
import com.lfs.web.beans.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * UserToUserDtoMapper
 *
 * @author fushenliao
 * @version 1.0.0
 * @project demo
 * @create 2021/8/10
 * @modify 2021/8/10
 */
@Mapper
public interface UserToUserDtoMapper {

    //声明成员变量INSTANCE，从而让客户端可以访问Mapper接口的实现。
    UserToUserDtoMapper INSTANCE = Mappers.getMapper(UserToUserDtoMapper.class);

    @Mappings({
            @Mapping(source = "mailbox", target = "mail"),
            @Mapping(source = "hobby", target = "hobbyDto")
    })
    UserDto userToUserDto(User user);
}
