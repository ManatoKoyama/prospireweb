package com.prospire.prospireweb.repository;

import org.apache.ibatis.annotations.Mapper;
import com.prospire.prospireweb.model.User;

@Mapper
public interface UserMapper {
    /** ユーザー名でユーザー情報を検索 */
    User findByUsername(String username);
}