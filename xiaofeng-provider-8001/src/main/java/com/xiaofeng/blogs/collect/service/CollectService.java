package com.xiaofeng.blogs.collect.service;

import com.xiaofeng.blogs.collect.dto.CollectDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/16 17:02
 * @Description:
 */
public interface CollectService {

    List<CollectDto> list();
}
