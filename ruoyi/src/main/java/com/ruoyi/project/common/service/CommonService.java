package com.ruoyi.project.common.service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * 通用Service处理
 *
 * @author wangdong
 * @date 2020-04-27
 */
@Component
@Slf4j
@SuppressWarnings("Duplicates")
public class CommonService {

    /**
     * token验证处理
     */
    private final TokenService tokenService;

    /**
     * 注入AuthenticationManager
     */
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 构造方法注入
     *
     * @param tokenService token验证处理
     */
    @Autowired
    public CommonService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param userType 用户类型（01：后台端用户；02：手机端用户）
     * @return 结果
     */
    public String login(String username, String password, String userType) {
        // 判断用户类型非空
        if (userType == null || userType.length() == 0) {
            // 记录登录信息
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "调用登录接口未传递用户类型"));
            // 抛出自定义异常
            throw new CustomException("请传递用户类型");
        }
        // 用户验证
        Authentication authentication;
        try {
            // 该方法会去调用 UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            e.printStackTrace();
            // 异常处理
            if (e instanceof BadCredentialsException) {
                // 记录登录信息
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                // 抛出用户密码不正确或不符合规范异常
                throw new UserPasswordNotMatchException();
            } else {
                // 记录登录信息
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                // 抛出自定义异常
                throw new CustomException(e.getMessage());
            }
        }
        // 记录登录信息
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS,
                userType.replaceAll("01", "后台端用户").replaceAll("02", "手机端用户") + MessageUtils.message("user.login.success")));
        // 获得登录用户身份权限
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 发送退出登录请求
     *
     * @param url     上下文url
     * @param request 请求
     * @return 请求结果
     */
    public AjaxResult sentPostLogout(String url, HttpServletRequest request) {
        // 定义PrintWriter
        PrintWriter out = null;
        // 定义BufferedReader
        BufferedReader in = null;
        // 实例化StringBuilder
        StringBuilder result = new StringBuilder();
        // 拼接退出登录url
        url = url.replaceAll("51120","51110") + "/logout";
        try {
            // 打log
            log.info("sendPost - {}", url);
            // 实例化URL
            URL realUrl = new URL(url);
            // 打开连接
            URLConnection conn = realUrl.openConnection();
            // 设置accept请求属性
            conn.setRequestProperty("accept", "*/*");
            // 设置connection请求属性
            conn.setRequestProperty("connection", "Keep-Alive");
            // 设置user-agent请求属性
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 设置Accept-Charset请求属性
            conn.setRequestProperty("Accept-Charset", "utf-8");
            // 设置contentType请求属性
            conn.setRequestProperty("contentType", "utf-8");
            // 设置Authorization请求头属性
            conn.setRequestProperty("Authorization", request.getHeader("Authorization"));
            // 设置DoOutput为true
            conn.setDoOutput(true);
            // 设置DoInput为true
            conn.setDoInput(true);
            // 实例化PrintWriter
            out = new PrintWriter(conn.getOutputStream());
            // 强制刷出缓冲池中的数据
            out.flush();
            // 实例化BufferedReader
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            // 定义行
            String line;
            // 循环BufferedReader
            while ((line = in.readLine()) != null) {
                // 打log
                log.info("返回当前行为：{}", line);
                // StringBuilder循环添加行
                result.append(line);
            }
            // 打log
            log.info("recv - {}", result);
        } catch (ConnectException e) {
            // 打log
            log.error("调用HttpUtils.sendPost ConnectException, url=" + url, e);
            // 返回退出登录失败
            return AjaxResult.error("退出登录失败");
        } catch (SocketTimeoutException e) {
            // 打log
            log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url, e);
            // 返回退出登录失败
            return AjaxResult.error("退出登录失败");
        } catch (IOException e) {
            // 打log
            log.error("调用HttpUtils.sendPost IOException, url=" + url, e);
            // 返回退出登录失败
            return AjaxResult.error("退出登录失败");
        } catch (Exception e) {
            // 打log
            log.error("调用HttpsUtil.sendPost Exception, url=" + url, e);
            // 返回退出登录失败
            return AjaxResult.error("退出登录失败");
        } finally {
            try {
                // 判断输出流是否关闭
                if (out != null) {
                    // 关闭输出流
                    out.close();
                }
                // 判断输入流是否关闭
                if (in != null) {
                    // 关闭输入流
                    in.close();
                }
            } catch (IOException ex) {
                // 打log
                log.error("调用in.close Exception, url=" + url, ex);
            }
        }
        // 判断返回结果是否为空
        if (StringUtils.isEmpty(result.toString())) {
            // 打log
            log.error("请求获取失败 {}", url);
            // 返回退出登录失败
            return AjaxResult.error("退出登录失败");
        }
        // 打log
        log.info("退出登录请求结果为：{}", result.toString());
        // 从结果中获取JSONObject
        JSONObject obj = JSONObject.parseObject(result.toString());
        // 获取响应code
        String code = obj.getString("code");
        // 判断响应code是否为空
        if (code == null) {
            // 打log
            log.error("请求获取失败 {}", result.toString());
            // 返回退出登录失败
            return AjaxResult.error("退出登录失败");
        }
        // 判断响应code是否为200
        if (HttpStatus.SUCCESS == Integer.valueOf(code)) {
            // 打log
            log.info("请求获取成功 {}", result.toString());
            // 返回成功
            return AjaxResult.success(obj.getString("msg"));
        } else {
            // 打log
            log.error("请求获取失败 {}", result.toString());
            // 返回退出登录失败
            return AjaxResult.error("退出登录失败");
        }
    }
}
