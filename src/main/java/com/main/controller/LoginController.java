package com.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.main.entity.Account;
import com.main.entity.Nhan_Vien;
import com.main.entity.User;
import com.main.service.AccountService;
import com.main.service.NhanVienService;
import com.main.service.UserService;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    AccountService accountService;

    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "/authentication/login";
    }

    @GetMapping("/showRegisterPage")
    public String showRegisterPage(Model model, HttpServletRequest request) {
        User newUser = new User();
        Account newAccount = new Account();
        model.addAttribute("newUser", newUser);
        model.addAttribute("newAccount", newAccount);

        // này làm đăng kí account nếu người dùng đã đăng kí vé rồi
        Account tempAccount = (Account) request.getSession().getAttribute("tempAccount");
        User tempUser = (User) request.getSession().getAttribute("tempUser");
        if (tempAccount != null && tempUser != null) {
            model.addAttribute("tempAccount", tempAccount);
            model.addAttribute("tempUser", tempUser);
        }

        return "/authentication/register";
    }

    @PostMapping("/saveNewUser")
    public String saveNewUser(HttpServletRequest request, @ModelAttribute("newUser") User user,
            @ModelAttribute("newAccount") Account account, Model model) {
        Account tempAccount = accountService.getAccountFromUsername(account.getUsername());
        User tempUser = userService.getUserFromUsername(account.getUsername());

        // nếu số điện thoại chưa từng đăng kí vé và chưa tạo tài khoản thì tạo tài
        // khoản mới + add thông tin user
        if (tempAccount != null) { // nếu account đã tồn tại thì trả về là số điện thoại đã tồn tại
            return "redirect:/showRegisterPage?tontaiTK";
        } else if (tempUser != null && tempAccount == null) { // số điện thoại từng đăng kí vé nhưng chưa tạo tài khoản
            request.getSession().setAttribute("tempAccount", account);
            request.getSession().setAttribute("tempUser", tempUser);
            return "redirect:/showRegisterPage?tontaiUser";
        } else {
            userService.saveUser(user, account);
            return "redirect:/user";
        }
    }

    @PostMapping("/saveNewAccount")
    public String saveNewAccount(HttpServletRequest request) {
        Account tempAccount = (Account) request.getSession().getAttribute("tempAccount");
        User tempUser = userService.getUserFromUsername(tempAccount.getUsername());
        userService.updateUserWithAccount(tempUser, tempAccount);
        request.getSession().removeAttribute("tempAccount");
        request.getSession().removeAttribute("tempUser");
        return "redirect:/user";
    }

    @GetMapping("/loginSuccessful")
    public String loginSuccessfulPage(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPricipalName = authentication.getName();

        User tempUser = userService.getUserFromUsername(currentPricipalName);
        if (tempUser != null) {
            request.getSession().setAttribute("user", tempUser);
        } else {
            Nhan_Vien tempNV = nhanVienService.getNhanVienFromUsername(currentPricipalName);
            request.getSession().setAttribute("nhanVien", tempNV);
        }

        return "authentication/loginSuccessful";
    }
}
