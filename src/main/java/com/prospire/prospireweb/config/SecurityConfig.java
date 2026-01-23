package com.prospire.prospireweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // パスワードのハッシュ化 ※この実装は必ずしないと動作しない
    @Bean
    public PasswordEncoder passwordEncoder() {
        // DBにBCryptでハッシュ化したパスワードを保存している必要がある
        return new BCryptPasswordEncoder(); 
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 認可設定
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/admin/users/register", "/error", "/css/**", "/js/**", "/images/**").permitAll() // ログインページ、登録ページ、エラーページと静的ファイルはアクセス許可
                .anyRequest().authenticated() // その他は認証が必要
            )
            // ログイン設定
            .formLogin(form -> form
                .loginPage("/login") // カスタムログインフォームのURL
                .loginProcessingUrl("/doLogin") // 認証を処理するPOSTリクエストのURL
                .defaultSuccessUrl("/top", true) // ログイン成功時に /top に強制遷移
                .failureUrl("/login?error") // ログイン失敗時の遷移先
                .permitAll()
            )
            // ログアウト設定
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout") // ログアウト成功時の遷移先
                .permitAll()
            );

        return http.build();
    }
}