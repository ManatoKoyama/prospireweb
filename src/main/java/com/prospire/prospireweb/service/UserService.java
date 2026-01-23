package com.prospire.prospireweb.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.prospire.prospireweb.model.User;
import com.prospire.prospireweb.repository.UserMapper;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 新規ユーザーを登録
     * パスワードは自動的にハッシュ化されます
     * 
     * @param user 登録するユーザー情報
     * @return 登録に成功した場合 true、失敗した場合 false
     */
    @Transactional
    public boolean registerUser(User user) {
        // ユーザー名の重複チェック
        if (userMapper.findByUsername(user.getUsername()) != null) {
            return false; // ユーザー名が既に存在
        }

        // パスワードをハッシュ化
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // ロールが未指定の場合はデフォルト値を設定
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("10"); // デフォルトは一般ユーザー
        }

        int result = userMapper.insertUser(user);
        return result > 0;
    }

    /**
     * ユーザー情報を更新
     * パスワード変更がある場合は自動的にハッシュ化されます
     * 
     * @param user 更新するユーザー情報
     * @return 更新に成功した場合 true、失敗した場合 false
     */
    @Transactional
    public boolean updateUser(User user) {
        // ユーザーが存在するか確認
        User existingUser = userMapper.findByUsername(user.getUsername());
        if (existingUser == null) {
            return false; // ユーザーが存在しない
        }

        // パスワードが変更されている場合はハッシュ化
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // パスワードが変更されていない場合は現在の値を保持
            user.setPassword(existingUser.getPassword());
        }

        int result = userMapper.updateUser(user);
        return result > 0;
    }

    /**
     * ユーザーを削除
     * 
     * @param username 削除するユーザー名
     * @return 削除に成功した場合 true、失敗した場合 false
     */
    @Transactional
    public boolean deleteUser(String username) {
        // ユーザーが存在するか確認
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return false; // ユーザーが存在しない
        }

        int result = userMapper.deleteUser(username);
        return result > 0;
    }

    /**
     * ユーザー情報を取得
     * 
     * @param username ユーザー名
     * @return ユーザー情報（存在しない場合は null）
     */
    public User getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
