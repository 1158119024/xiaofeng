package com.xiaofeng.blogs.article.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaofeng.blogs.article.bo.ArchivesBo;
import com.xiaofeng.blogs.article.bo.ArticleBo;
import com.xiaofeng.blogs.article.dto.ArchivesDto;
import com.xiaofeng.blogs.article.dto.ArchivesKeyDto;
import com.xiaofeng.blogs.article.dto.ArticleDto;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import com.xiaofeng.blogs.article.repository.ArticleRepository;
import com.xiaofeng.blogs.tags.entity.TagsEntity;
import com.xiaofeng.blogs.tags.repository.TagsRepository;
import com.xiaofeng.blogs.tags.service.TagsService;
import com.xiaofeng.blogs.user.repository.UserRepository;
import com.xiaofeng.blogs.user.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/11 20:44
 * @Description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private TagsService tagsService;

    @Override
    public Integer add(ArticleEntity articleEntity) {
        if( articleEntity.getIsTop() ){
            articleEntity.setTopGrade(1);
        }
        Integer result = articleRepository.add(articleEntity);
        if ( result > 0 ) {
            Integer userId = articleEntity.getUserId();
            String tagsId = articleEntity.getTagsId();
            // 增加标签中的文章数量
            tagsService.incrTagNum(tagsId, userId);
        }
        return result;
    }

    @Override
    public Integer delete(Integer id, Integer userId) {
        ArticleEntity articleEntity = articleRepository.getArticleById(id);
        String tagsId = articleEntity.getTagsId();
        tagsService.decrTagNum(tagsId, userId);
        return articleRepository.delete(id, userId);
    }

    @Override
    public Integer update(ArticleEntity articleEntity) {
        if( articleEntity.getIsTop() != null ){
            if( articleEntity.getIsTop() ){
                articleEntity.setTopGrade(1);
            }else{
                articleEntity.setTopGrade(0);
            }
        }
        Integer userId = articleEntity.getUserId();
        List<String> decrList; // 要删的tagList
        List<String> incrList; // 要加的tagList
        List<String> retainList; // 交集
        ArticleEntity oldArticleEntity = articleRepository.getArticleById(articleEntity.getId());
        String oldTagsId = oldArticleEntity.getTagsId();
        String newTagsId = articleEntity.getTagsId();
        if( !StringUtils.isEmpty(oldTagsId) && !StringUtils.isEmpty(newTagsId) ){
            decrList = StringToList(oldTagsId);
            incrList = StringToList(newTagsId);
            decrList.remove("");
            incrList.remove("");
            retainList = JSON.parseArray(JSON.toJSONString(decrList), String.class); // 深克隆老的
            retainList.retainAll(incrList); // 取老的与新的list中的交集
            decrList.removeAll(retainList); // 移除交集，剩下就是要删除的
            incrList.removeAll(retainList); // 移除交集，剩下就是要增加的
            tagsService.updateTagNum(decrList, userId, "decr");
            tagsService.updateTagNum(incrList, userId, "incr");
        }else{
            if( StringUtils.isEmpty(oldTagsId) ){ // 老的等于空，把新的全部加1
                tagsService.incrTagNum(newTagsId, userId);
            }else if( StringUtils.isEmpty(newTagsId) ){ // 新的等于空，把老的全部减1
                tagsService.decrTagNum(oldTagsId, userId);
            }
        }
//        articleEntity.setUpdateTime(new Date());
        return articleRepository.update(articleEntity);
    }

    /**
     *  修改一些状态字段，与整体修改分离开来
     * @param articleEntity
     * @return
     */
    @Override
    public Integer updateState(ArticleEntity articleEntity) {
        if( articleEntity.getIsTop() != null ){
            if( articleEntity.getIsTop() ){
                articleEntity.setTopGrade(1);
            }else{
                articleEntity.setTopGrade(0);
            }
        }
        return articleRepository.update(articleEntity);
    }

    @Override
    public ArticleDto getArticleById(Integer id){
        ArticleEntity updateEntity = new ArticleEntity();

        ArticleEntity articleEntity = articleRepository.getArticleById(id);
        // 浏览数加1
        articleRepository.incrBrowseNum(id);

        ArticleDto articleDto = articleEntity.entityToDto(ArticleDto.class);
        // 获取标签
        String tagsId = articleEntity.getTagsId();
        List<TagsEntity> tagList = tagsService.getTagEntityByTagsId(tagsId);
        articleDto.setTagList(tagList);
        return articleDto;
    }

    // 获取上中下三篇文章
    @Override
    public ArticleDto getArticleAndPreAndNextById(Integer id, Integer userId) {
        ArticleEntity updateEntity = new ArticleEntity();
        ArticleEntity currentEntity = new ArticleEntity();
        ArticleDto articleDto = new ArticleDto();

        List<ArticleEntity> articleEntityList = articleRepository.getArticleAndPreAndNextById(id, userId);
        for ( int i = 0, len = articleEntityList.size(); i < len; i++ ) {
            ArticleEntity entity = articleEntityList.get(i);
            if( entity.getId().equals(id) ){
                currentEntity = entity;
                articleDto = currentEntity.entityToDto(ArticleDto.class);
                if( len > 1 ){
                    if( i == 0 ){ // 下标等于0时，代表没有上一篇
                        articleDto.setNextArticleEntity(articleEntityList.get(1));
                    } else if( i == 1 ){ // 下标等于1时，代表有上一篇，但是下一篇不确定
                        if( len == 3 ){ // 总数等于3时，代表有下一篇
                            articleDto.setPrevArticleEntity(articleEntityList.get(0));
                            articleDto.setNextArticleEntity(articleEntityList.get(2));
                        } else { // 总数不等于3时，代表没有下一篇
                            articleDto.setPrevArticleEntity(articleEntityList.get(0));
                        }
                    }
                }
                break;
            }
        }
        // 浏览数加1
        articleRepository.incrBrowseNum(id);

        // 获取标签
        String tagsId = currentEntity.getTagsId();
        List<TagsEntity> tagList = tagsService.getTagEntityByTagsId(tagsId);
        articleDto.setTagList(tagList);
        return articleDto;
    }

    @Override
    public List<ArticleEntity> getArticlesByUserId(ArticleBo articleBo) {
        articleBo.setTitle(articleBo.getTitle().trim());
        Integer pageNum = articleBo.getPageNum();
        Integer pageSize = articleBo.getPageSize();
        List<ArticleEntity> list = PageHelper.startPage(pageNum, pageSize);
        articleRepository.getArticlesByUserId(articleBo);
        return list;
    }

    @Override
    public PageInfo getArticles(ArticleBo articleBo) {
        articleBo.setTitle(articleBo.getTitle().trim());
        Integer pageNum = articleBo.getPageNum();
        Integer pageSize = articleBo.getPageSize();
        List<ArticleEntity> list = PageHelper.startPage(pageNum, pageSize);
        articleRepository.getArticlesByUserId(articleBo);
        // 塞入page信息
        PageInfo pageInfo = new PageInfo(list);

        List<ArticleDto> articleList = new ArrayList<>();
        for(ArticleEntity articleEntity : list){
            ArticleDto articleDto = articleEntity.entityToDto(ArticleDto.class);
            String tagsId = articleEntity.getTagsId();
            List<TagsEntity> tagEntityByTagsId = tagsService.getTagEntityByTagsId(tagsId);
            articleDto.setTagList(tagEntityByTagsId);
            articleList.add(articleDto);
        }
        pageInfo.setList(articleList);
        return pageInfo;
    }

    /**
     * 通过创建时间进行归档查询
     * @param archivesBo
     * @return
     */
    @Override
    public List<ArchivesKeyDto> getArchivesByCreateTime(ArchivesBo archivesBo) {
        List<ArchivesDto> archivesDtoList = articleRepository.getArchivesByCreateTime(archivesBo);
        List<ArchivesKeyDto> archivesKeyDtos = new ArrayList<>();
        for (ArchivesDto item : archivesDtoList) {
            String year = item.getYear();
            ArchivesKeyDto keyDto = isExistYear(year, archivesKeyDtos);
            if ( keyDto == null ) {
                keyDto = new ArchivesKeyDto();
                archivesKeyDtos.add(keyDto);
                keyDto.setYear(year);
            }
            keyDto.getList().add(item);
        }

        return archivesKeyDtos;
    }

    public ArchivesKeyDto isExistYear(String year, List<ArchivesKeyDto> list) {
        if ( !StringUtils.isEmpty(year) ) {
            for ( ArchivesKeyDto item : list ) {
                if ( item.getYear().equals(year) ) {
                    return item;
                }
            }
        }
        return null;
    }


    // 数组去空
    public List<String> StringToList(String tagsId){
        List<String> list = new ArrayList<>();
        String[] split = tagsId.split(",");
        for( String tag : split ){
            if( !StringUtils.isEmpty(tag) ){
                list.add(tag);
            }
        }
        return list;
    }
}
