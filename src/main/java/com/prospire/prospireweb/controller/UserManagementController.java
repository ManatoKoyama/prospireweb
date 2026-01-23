package com.prospire.prospireweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.prospire.prospireweb.model.User;
import com.prospire.prospireweb.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserManagementController {

    private final UserService userService;

    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 新規ユーザー登録フォーム表示
     */
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    /**
     * ユーザー登録処理
     */
    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String kanjiName1,
            @RequestParam String kanjiName2,
            @RequestParam String kanaName1,
            @RequestParam String kanaName2,
            @RequestParam String mailAddress,
            @RequestParam String organization,
            @RequestParam(defaultValue = "10") String role,
            RedirectAttributes redirectAttributes) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setKanjiName1(kanjiName1);
        user.setKanjiName2(kanjiName2);
        user.setKanaName1(kanaName1);
        user.setKanaName2(kanaName2);
        user.setMailAddress(mailAddress);
        user.setOrganization(organization);
        user.setRole(role);

        boolean success = userService.registerUser(user);

        if (success) {
            redirectAttributes.addFlashAttribute("message", "ユーザーが正常に登録されました");
            return "redirect:/admin/users/register";
        } else {
            redirectAttributes.addFlashAttribute("error", "ユーザー名が既に存在します");
            return "redirect:/admin/users/register";
        }
    }

    /**
     * ユーザー更新処理
     */
    @PostMapping("/{username}/update")
    public String updateUser(
            @PathVariable String username,
            @RequestParam String password,
            @RequestParam(defaultValue = "ROLE_USER") String role,
            RedirectAttributes redirectAttributes) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        boolean success = userService.updateUser(user);

        if (success) {
            redirectAttributes.addFlashAttribute("message", "ユーザー情報が正常に更新されました");
        } else {
            redirectAttributes.addFlashAttribute("error", "ユーザー情報の更新に失敗しました");
        }

        return "redirect:/admin/users";
    }

    /**
     * ユーザー削除処理
     */
    @PostMapping("/{username}/delete")
    public String deleteUser(
            @PathVariable String username,
            RedirectAttributes redirectAttributes) {

        boolean success = userService.deleteUser(username);

        if (success) {
            redirectAttributes.addFlashAttribute("message", "ユーザーが正常に削除されました");
        } else {
            redirectAttributes.addFlashAttribute("error", "ユーザーの削除に失敗しました");
        }

        return "redirect:/admin/users";
    }
}
