package com.xiaofeng.blogs.user.repository;

import com.xiaofeng.blogs.user.dto.UserAllDetailsDto;
import com.xiaofeng.blogs.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/25 17:17
 * @Description:
 */
@Mapper
public interface UserRepository {

    @Select("select * from xiaofeng_user where id = #{userId}")
    UserEntity get(Integer userId);

    @Select("SELECT \n" +
            "\tu.id, u.aliasname, \n" +
            "\tu.createTime, \n" +
            "\tu.image, \n" +
            "\tu.isEnable, \n" +
            "\tu.password, \n" +
            "\tu.phone, \n" +
            "\tu.username, \n" +
            "\td.collectNum,\n" +
            "\td.commendNum, \n" +
            "\td.commentNum,\n" +
            "\td.details, \n" +
            "\td.articleNum, \n" +
            "\td.userId\n" +
            "FROM xiaofeng_user u , xiaofeng_user_details d WHERE u.id = #{userId} AND u.id = d.userId")
    UserAllDetailsDto getUserDetails(Integer userId);

    @Update("update xiaofeng_user SET articleNum = articleNum + 1 where userId=#{userId}")
    Integer incrArticleNum(Integer userId);

    @Update("update xiaofeng_user SET collectNum = collectNum + 1 where userId=#{userId}")
    Integer incrCollectNum(Integer userId);
}
