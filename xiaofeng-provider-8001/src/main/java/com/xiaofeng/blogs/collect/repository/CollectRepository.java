package com.xiaofeng.blogs.collect.repository;

import com.xiaofeng.blogs.article.repository.ArticleRepositoryImpl;
import com.xiaofeng.blogs.collect.bo.CollectBo;
import com.xiaofeng.blogs.collect.entity.CollectEntity;
import jdk.internal.org.objectweb.asm.tree.IincInsnNode;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/16 17:01
 * @Description:
 */
@Mapper
public interface CollectRepository {

    @Select("select * from xiaofeng_collect")
    List<CollectEntity> list();

    @InsertProvider(type = CollectRepositoryImpl.class, method = "add")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    Integer add(CollectEntity collectEntity);

    @Delete("DELETE FROM xiaofeng_collect WHERE id = #{id} and userId = #{userId}")
    Integer delete(@Param("id") Integer id,@Param("userId") Integer userId);

    @UpdateProvider(type = CollectRepositoryImpl.class, method = "update")
    Integer update(CollectEntity collectEntity);

    @SelectProvider(type = CollectRepositoryImpl.class, method = "getCollectsByCondition")
    List<CollectEntity> getCollectsByCondition(CollectBo collectBo);
}
