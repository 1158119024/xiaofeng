package com.xiaofeng.blogs.tags.service;

import com.github.pagehelper.Page;
import com.xiaofeng.blogs.tags.bo.TagsBo;
import com.xiaofeng.blogs.tags.dto.TagsDto;
import com.xiaofeng.blogs.tags.entity.TagsEntity;

import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/3 11:04
 * @Description:
 */
public interface TagsService {

    Integer add(TagsEntity tagsEntity);

    Integer delete(Integer id, Integer userId);

    Integer update(TagsEntity tagsEntity);

    Integer incr(Integer id, Integer userId);

    TagsEntity getTagById(Integer id);

    List<TagsEntity> getTagsByUserId(Integer userId, Integer pageNum, Integer pageSize, String tagName);

    void incrTagNum(String tagsId, Integer userId);

    void decrTagNum(String tagsId, Integer userId);

    void updateTagNum(List<String> tagsIdList, Integer userId, String action);

    List<TagsEntity> getTagEntityByTagsId(String tagsId);

    List<TagsDto> getTags(TagsBo tagsBo);
}
