package com.prospire.prospireweb.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.prospire.prospireweb.repository.UserMapper;
import com.prospire.prospireweb.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    /**
     * コンストラクタ（コンポーネント注入）
     * @param userMapper ユーザーデータアクセスマッパー
     */
    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 指定されたユーザー名でユーザー情報をロードします。
     * Spring Security 用の `UserDetails` を返却します。
     * @param username ユーザー名
     * @return UserDetails 実装（見つからない場合は UsernameNotFoundException をスロー）
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // アカウント有効フラグを設定（現状は常に true を返す）
        user.setEnabled(true);

        return user;
    }
}