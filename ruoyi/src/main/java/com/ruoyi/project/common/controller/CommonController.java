package com.ruoyi.project.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.SysPermissionService;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.project.common.domain.LoginBody;
import com.ruoyi.project.common.service.CommonService;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.service.ISystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.framework.web.domain.AjaxResult;

import java.util.Set;

/**
 * 通用请求处理
 *
 * @author wangdong
 * @date 2020-04-27
 */
@RestController
@RequestMapping("common")
@Slf4j
@SuppressWarnings("Duplicates")
@Api(tags = {"1 公共"}, description = "登录、获取当前登录人信息、退出登录（POST）（/logout）、上传")
public class CommonController {

    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     */
    private final ServerConfig serverConfig;

    /**
     * 登录校验方法
     */
    private final CommonService commonService;

    /**
     * 用户权限处理
     */
    private final SysPermissionService permissionService;

    /**
     * token验证处理
     */
    private final TokenService tokenService;
    /**
     * 用户
     */
    private final ISystemUserService systemUserService;

    /**
     * 构造方法注入
     *
     * @param serverConfig      获取完整的请求路径，包括：域名，端口，上下文访问路径
     * @param commonService     登录校验方法
     * @param permissionService 用户权限处理
     * @param tokenService      token验证处理
     */
    @Autowired
    public CommonController(ServerConfig serverConfig, CommonService commonService, SysPermissionService permissionService, TokenService tokenService,ISystemUserService systemUserService) {
        this.serverConfig = serverConfig;
        this.commonService = commonService;
        this.permissionService = permissionService;
        this.tokenService = tokenService;
        this.systemUserService = systemUserService;
    }

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (!FileUtils.isValidFilename(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = RuoYiConfig.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/download/resource")
    public void resourceDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 本地资源路径
        String localPath = RuoYiConfig.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(name, Constants.RESOURCE_PREFIX);
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }

    /**
     * 登录方法
     *
     * @param loginBody 用户登录对象
     * @return 结果
     */
    @Log(title = "1.1 登录", businessType = BusinessType.OTHER)
    @PostMapping("/login")
    @ApiOperation(value = "1.1 登录", notes = "登录方法")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        // 返回成功消息
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = commonService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getUserType());
        // 令牌放入map
        ajax.put(Constants.TOKEN, token);
        // 返回
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @Log(title = "1.2 获取当前登录人信息", businessType = BusinessType.OTHER)
    @GetMapping("/getLoginInfo")
    @ApiOperation(value = "1.2 获取当前登录人信息", notes = "获取用户信息")
    public AjaxResult getLoginInfo() {
        // 获取用户身份信息
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());//在redis中，查询到用户登录信息
        // 用户信息
        SystemUserEntity user = loginUser.getSystemUserEntity();
        // 查询用户基本信息
        SystemUserEntity systemUserEntity = systemUserService.getSystemUserByUserName(user.getUserName());

        // 返回成功消息
        AjaxResult ajax = AjaxResult.success();
        // 用户信息放入map
        ajax.put("user", systemUserEntity);
        return ajax;
    }

    /**
     * 退出登录方法
     *
     * @return 结果
     */
    @Log(title = "1.3 退出登录", businessType = BusinessType.OTHER)
    @PostMapping("/logout")
    @ApiOperation(value = "1.3 退出登录", notes = "退出登录方法")
    public AjaxResult logout() {
        // 发送退出登录请求，返回结果
        return commonService.sentPostLogout(serverConfig.getUrl(), ServletUtils.getRequest());
    }

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 结果
     * @throws Exception 异常
     */
    @Log(title = "1.4 上传", businessType = BusinessType.OTHER)
    @PostMapping("/upload")
    @SuppressWarnings("all")
    @ApiOperation(value = "1.4 上传", notes = "文件上传")
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 文件路径
            String fileName = FileUploadUtils.upload(filePath, file);
            // 完整的请求路径
            String url = serverConfig.getUrl() + fileName;
            // 返回成功消息
            AjaxResult ajax = AjaxResult.success();
            // 文件路径放入map
            ajax.put("fileName", fileName);
            // 完整的请求路径放入map
            ajax.put("url", url);
            // 返回
            return ajax;
        } catch (Exception e) {
            // 返回错误信息
            return AjaxResult.error(e.getMessage());
        }
    }

}
