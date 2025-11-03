package com.prospire.prospireweb.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
public class User implements UserDetails {

    private String username;
    private String password; // ハッシュ化されたパスワード
    private boolean enabled;
    private String role; // 取得した権限情報を保持するフィールド


    // ----------------------------------------------------
    // UserDetails インターフェースの実装
    // ----------------------------------------------------
    // Spring Security の権限オブジェクトに変換
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 取得した権限情報をSimpleGrantedAuthorityに変換して返す
        // role が null でないことを確認
        if (this.role == null) {
            return Collections.emptyList();
        }
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }
    
    // 以下、利用しないので、強制的にtrueを返す
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

}