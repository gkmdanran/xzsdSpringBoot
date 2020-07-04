package com.ruoyi.project.zxsd.sys.service;

import com.ruoyi.project.zxsd.sys.domain.CommentEntity;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/6/7 12:57
 */
public interface ICommentService {
    void insertComment(CommentEntity commentEntity);
    List<CommentEntity> selectComment(CommentEntity commentEntity);
}
