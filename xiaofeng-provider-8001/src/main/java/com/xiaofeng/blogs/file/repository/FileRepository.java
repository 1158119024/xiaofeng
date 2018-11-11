package com.xiaofeng.blogs.file.repository;

import com.xiaofeng.blogs.file.entity.ImageFileEntity;
import com.xiaofeng.blogs.tags.entity.TagsEntity;
import org.apache.http.entity.FileEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.propertyeditors.FileEditor;
import org.springframework.security.access.method.P;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/7 21:55
 * @Description:
 */
@Mapper
public interface FileRepository {

    @Insert("INSERT INTO " +
            "xiaofeng_file(" +
            "id, userId, domain, cosKey, eTag, fileName, type, size, quality, createTime) " +
            "VALUE(" +
            "#{id}, #{userId}, #{domain}, #{cosKey}, #{eTag}, #{fileName}, #{type}, #{size}, #{quality}, NOW())")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    Integer add(ImageFileEntity imageFileEntity);

    @Delete("DELETE FROM xiaofeng_file WHERE cosKey = #{cosKey} and userId = #{userId}")
    void delete(@Param("userId") Integer userId, @Param("cosKey") String cosKey);
}
