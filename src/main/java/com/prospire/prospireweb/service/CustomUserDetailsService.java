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

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // データベースに存在しない enabled と role の値を Java 側で設定
        
        // アカウント有効フラグを設定 Todo:現状、ユーザー情報の有効性チェックはしないため、強制的にtrueを設定
        user.setEnabled(true);
        
        return user;
    }
}