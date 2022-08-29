package com.example.demo.controller;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.example.demo.exception.EarthquakeException;
import com.example.demo.demoCount.saToken.SaQuickManager;
import com.example.demo.domain.DTO.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZLJ
 * @description
 * @date 2022/1/14
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 进入登录页面
     * @param request see note
     * @return see note
     */
    @GetMapping("/saLogin")
    public String saLogin(HttpServletRequest request) {
        request.setAttribute("cfg", SaQuickManager.getConfig());
        return "login.html";
    }




    // 测试登录，浏览器访问： http://localhost:9001/user/doLogin?username=zhang&password=123456
    @PostMapping("/doLogin")
    @ResponseBody
    public Result<SaTokenInfo> doLogin(String name, String pwd) {

        // 参数完整性校验
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
            return Result.ofFail(-1, "请输入账号和密码");
        }

        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        try {
            UserDTO userDTO = userService.selectByName(name);
            if (userDTO.getName().equals(name) && userDTO.getPassword().equals(pwd)) {
                StpUtil.getLoginType();

                StpUtil.login(userDTO.getId(),new SaLoginModel().setTimeout(6000));


                return Result.ofSuccess(StpUtil.getTokenInfo(), "登陆成功");
            }
        } catch (EarthquakeException e) {
            log.error(e.getMessage());
            return Result.ofFail(-1, "登陆失败" + e.getErrorMsg());
        }
        return Result.ofFail(-1, "登陆失败,请检查账户名 或 密码是否正确");
    }

    // 查询登录状态，浏览器访问： http://localhost:9001/user/isLogin
    @RequestMapping("/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }


    // 查询 Token 信息  ---- http://localhost:9001/user/tokenInfo

    /**
     * {
     *     "code": 200,
     *     "msg": "ok",
     *     "data": {
     *         "tokenName": "satoken",           // token名称
     *         "tokenValue": "e67b99f1-3d7a-4a8d-bb2f-e888a0805633",      // token值
     *         "isLogin": true,                  // 此token是否已经登录
     *         "loginId": "10001",               // 此token对应的LoginId，未登录时为null
     *         "loginType": "login",              // 账号类型标识
     *         "tokenTimeout": 2591977,          // token剩余有效期 (单位: 秒)
     *         "sessionTimeout": 2591977,        // User-Session剩余有效时间 (单位: 秒)
     *         "tokenSessionTimeout": -2,        // Token-Session剩余有效时间 (单位: 秒)
     *         "tokenActivityTimeout": -1,       // token剩余无操作有效时间 (单位: 秒)
     *         "loginDevice": "default-device"   // 登录设备标识
     *     },
     * }
     * @return
     */
    @RequestMapping("/tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }


    // 测试注销  ---- http://localhost:9001/user/logout
    @RequestMapping("/logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

}
