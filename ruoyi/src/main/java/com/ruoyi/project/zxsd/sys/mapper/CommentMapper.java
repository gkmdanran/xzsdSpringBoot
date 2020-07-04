package com.ruoyi.project.zxsd.sys.mapper;

import com.ruoyi.project.zxsd.sys.domain.CommentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/6/7 12:58
 */
public interface CommentMapper {
    void insertComment(CommentEntity commentEntity);
    List<CommentEntity> selectComment(CommentEntity commentEntity);
    void editCommentStatus(@Param("orderDetailCode") String orderDetailCode);
}
