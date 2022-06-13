package com.foo.jwt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.foo.jwt.domain.Otp;
import com.foo.jwt.domain.User;
import com.foo.jwt.service.OtpService;
import com.foo.jwt.service.UserService;
import com.foo.jwt.service.utils.CodeUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author foo
 * @since 2022-06-05
 */
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final OtpService otpService;

    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userService.save(user);
    }

    @PostMapping("/check-credentials")
    public boolean checkCredentials(@RequestBody User user) {
        if (user == null || StringUtils.isAnyBlank(user.getUsername(), user.getPassword())) {
            return false;
        }
        User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        if (one == null) {
            return false;
        }
        if (bCryptPasswordEncoder.matches(user.getPassword(), one.getPassword())) {
            // 生成 code
            String code = CodeUtil.generateCode();
            otpService.save(new Otp().setUsername(user.getUsername()).setCode(code));
            return true;
        }
        return false;
    }

    @PostMapping("/check-otp")
    public boolean checkOtp(@RequestBody Otp otp) {
        if (otp == null || StringUtils.isAnyBlank(otp.getUsername(), otp.getCode())) {
            return false;
        }
        Otp one = otpService.getOne(new LambdaQueryWrapper<Otp>().eq(Otp::getUsername, otp.getUsername()));
        if (one == null) {
            return false;
        }
        if (otp.getCode().equals(one.getCode())) {
            return true;
        }
        return false;
    }
}
