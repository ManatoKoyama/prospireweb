package com.prospire.prospireweb.repository;

import org.apache.ibatis.annotations.Mapper;
import com.prospire.prospireweb.model.User;

@Mapper
/**
 * ユーザーデータ操作用の MyBatis マッパーインタフェース。
 */
public interface UserMapper {
    /** ユーザー名でユーザー情報を検索 */
    User findByUsername(String username);
    
    /** 新規ユーザーを登録 */
    int insertUser(User user);
    
    /** ユーザー情報を更新 */
    int updateUser(User user);
    
    /** ユーザーを削除 */
    int deleteUser(String username);
}