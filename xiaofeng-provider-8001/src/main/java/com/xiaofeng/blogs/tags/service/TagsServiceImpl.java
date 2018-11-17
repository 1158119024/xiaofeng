package com.xiaofeng.blogs.tags.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaofeng.blogs.tags.entity.TagsEntity;
import com.xiaofeng.blogs.tags.repository.TagsRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/3 11:04
 * @Description:
 */
@Service
public class TagsServiceImpl implements TagsService {

    @Resource
    private TagsRepository tagsRepository;

    @Override
    public Integer add(TagsEntity tagsEntity) {
        if (tagsRepository.getTagByTagName(tagsEntity.getUserId(), tagsEntity.getTagName()) == null) {
            return tagsRepository.add(tagsEntity);
        }
        return 0;
    }

    @Override
    public Integer delete(Integer id, Integer userId) {
        return tagsRepository.delete(id, userId);
    }

    @Override
    public Integer update(TagsEntity tagsEntity) {
        return tagsRepository.update(tagsEntity);
    }

    @Override
    public Integer incr(Integer id, Integer userId) {
        return tagsRepository.incr(id, userId);
    }

    @Override
    public TagsEntity getTagById(Integer id) {
        return tagsRepository.getTagById(id);
    }

    @Override
    public List<TagsEntity> getTagsByUserId(Integer userId, Integer pageNum, Integer pageSize, String tagName) {
        List<TagsEntity> list = PageHelper.startPage(pageNum, pageSize);
        tagsRepository.getTagsByUserId(userId, tagName);
        return list;
    }

    // 增加标签中的文章数
    @Override
    public void incrTagNum(String tagsId, Integer userId){
        if( !StringUtils.isEmpty(tagsId) ){
            String[] split = tagsId.split(",");
            for( String tagId : split ){
                if( !StringUtils.isEmpty(tagId) ){
                    tagsRepository.incr(Integer.parseInt(tagId), userId);
                }
            }
        }
    }
    // 减少标签中的文章数
    @Override
    public void decrTagNum(String tagsId, Integer userId){
        if( !StringUtils.isEmpty(tagsId) ){
            String[] split = tagsId.split(",");
            for( String tagId : split ){
                if( !StringUtils.isEmpty(tagId) ){
                    tagsRepository.decr(Integer.parseInt(tagId), userId);
                }
            }
        }
    }
    // 操作标签的文章数
    @Override
    public void updateTagNum(List<String> tagsIdList, Integer userId, String action){
        if( action.equals("decr") ){
            for( String tagId : tagsIdList ){
                if( !StringUtils.isEmpty(tagId) ){
                    tagsRepository.decr(Integer.parseInt(tagId), userId);
                }
            }
        } else if( action.equals("incr") ){
            for( String tagId : tagsIdList ){
                if( !StringUtils.isEmpty(tagId) ){
                    tagsRepository.incr(Integer.parseInt(tagId), userId);
                }
            }
        }
    }


    // 根据tagsId 获取对应的Tag
    @Override
    public List<TagsEntity> getTagEntityByTagsId(String tagsId){
        List<TagsEntity> tagList = new ArrayList<>();
        if( !StringUtils.isEmpty(tagsId) ){
            String[] split = tagsId.split(",");
            for( String tagId : split ){
                if( !StringUtils.isEmpty(tagId) ){
                    tagList.add(tagsRepository.getTagById(Integer.parseInt(tagId)));
                }
            }
        }
        return tagList;
    }
}
