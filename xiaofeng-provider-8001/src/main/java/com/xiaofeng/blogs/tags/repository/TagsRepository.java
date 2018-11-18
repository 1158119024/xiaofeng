package com.xiaofeng.blogs.tags.repository;

import com.xiaofeng.blogs.tags.bo.TagsBo;
import com.xiaofeng.blogs.tags.dto.TagsDto;
import com.xiaofeng.blogs.tags.entity.TagsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/3 11:03
 * @Description:
 */
@Mapper
public interface TagsRepository {

    @Insert("INSERT INTO " +
            "xiaofeng_tags(" +
                "id, " +
                "userId, " +
                "tagName, " +
                "createTime) " +
            "VALUE(" +
                "#{id}, " +
                "#{userId}, " +
                "#{tagName}, " +
                "NOW())")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    Integer add(TagsEntity tagsEntity);

    @Delete("DELETE FROM xiaofeng_tags WHERE id = #{id} and userId = #{userId}")
    Integer delete(@Param("id") Integer id, @Param("userId") Integer userId);


    @Update("update xiaofeng_tags set tagName=#{tagName} where id=#{id} and userId = #{userId}")
    Integer update(TagsEntity tagsEntity);

    @Update("update xiaofeng_tags set articleNum=articleNum+1 where id=#{id} and userId = #{userId}")
    Integer incr(@Param("id") Integer id, @Param("userId") Integer userId);

    @Update("update xiaofeng_tags set articleNum=articleNum-1 where id=#{id} and userId = #{userId}")
    Integer decr(@Param("id") Integer id, @Param("userId") Integer userId);

    @Select("select * from xiaofeng_tags where id = #{id}")
    TagsEntity getTagById(Integer id);

    @SelectProvider(type = TagsRepositoryImpl.class, method = "getTagsByUserId")
    List<TagsEntity> getTagsByUserId(@Param("userId") Integer userId,@Param("tagName") String tagName);

    @Select("select * from xiaofeng_tags where userId = #{userId} and tagName = #{tagName}")
    TagsEntity getTagByTagName(@Param("userId") Integer userId,@Param("tagName") String tagName);

    @SelectProvider(type = TagsRepositoryImpl.class, method = "getTags")
    List<TagsDto> getTags(TagsBo tagsBo);
}
