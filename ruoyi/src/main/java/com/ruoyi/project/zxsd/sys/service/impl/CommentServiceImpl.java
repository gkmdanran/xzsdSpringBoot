package com.ruoyi.project.zxsd.sys.service.impl;

import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.zxsd.sys.domain.CommentEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.mapper.CommentMapper;
import com.ruoyi.project.zxsd.sys.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/6/7 12:57
 */
@Service
@Slf4j
public class CommentServiceImpl implements ICommentService{
   @Autowired
    private final CommentMapper commentMapper;

   public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    @Transactional  //事务回滚
    public void insertComment(CommentEntity commentEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
        commentEntity.setCreateBy(loginUser.getUsername());
        commentEntity.setUpdateBy(loginUser.getUsername());
        commentEntity.setUserName(loginUser.getUsername());
        commentEntity.setUserCode(systemUserEntity.getUserId());
       commentEntity.setId(IdUtils.fastSimpleUUID());
        commentMapper.insertComment(commentEntity);
        commentMapper.editCommentStatus(commentEntity.getOrderDetailCode());
    }

    @Override
    public List<CommentEntity> selectComment(CommentEntity commentEntity) {
        return commentMapper.selectComment(commentEntity);
    }
}
