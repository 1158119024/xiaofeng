package com.xiaofeng.blogs.collect.service;

import com.xiaofeng.blogs.collect.bo.CollectBo;
import com.xiaofeng.blogs.collect.dto.CollectDto;
import com.xiaofeng.blogs.collect.entity.CollectEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/16 17:02
 * @Description:
 */
public interface CollectService {

    List<CollectDto> list();

    Integer add(CollectEntity collectEntity);

    Integer delete(Integer id, Integer userId);

    Integer update(CollectEntity collectEntity);

    List<CollectEntity> getCollectsByCondition(CollectBo collectBo);
}
