package com.ruoyi.project.zxsd.sys.mapper;

import com.ruoyi.project.zxsd.sys.domain.UserPasswordEntity;

import javax.swing.text.html.parser.Entity;
import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/6/3 11:08
 */
public interface UserPasswordMapper {
    void editPassword(UserPasswordEntity userPasswordEntity);
    List<UserPasswordEntity> selectPassword(UserPasswordEntity userPasswordEntity);
}
