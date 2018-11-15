package com.xiaofeng.tags;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaofeng.blogs.tags.entity.TagsEntity;
import com.xiaofeng.blogs.tags.repository.TagsRepository;
import com.xiaofeng.blogs.tags.service.TagsService;
import com.xiaofeng.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/3 11:27
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagsTest {

    @Resource
    private TagsRepository tagsRepository;

    @Resource
    private TagsService tagsService;

    @Test
    public void add(){
        TagsEntity tagsEntity = new TagsEntity().setUserId(1).setTagName("11");
        Integer add = tagsRepository.add(tagsEntity);
        System.out.println(tagsEntity.getId());
    }
    @Test
    public void update(){
        TagsEntity tagsEntity = new TagsEntity().setUserId(6).setTagName("66666").setId(9);
        Integer update = tagsRepository.update(tagsEntity);
        System.out.println("return: "+ update +" ,Id: "+ tagsEntity.getId());
    }

    @Test
    public void delete(){
        Integer delete = tagsRepository.delete(8, 1);
        System.out.println("return: "+ delete);
    }

    @Test
    public void incr(){
        Integer incr = tagsRepository.incr(2, 1);
        System.out.println("return: "+incr);
    }

    @Test
    public void getTagById(){
//        TagsEntity tagById = tagsRepository.getTagById(44);
//        System.out.println("return: "+tagById);
//        String s = DateUtil.convertDate2Str(tagById.getCreateTime());
//        System.out.println("data: "+ s);
    }

    @Test
    public void getTagsByUserId(){
//        Page<TagsEntity> list = PageHelper.startPage(1, 2);
        List<TagsEntity> list = tagsRepository.getTagsByUserId(1, "sping");

        System.out.println(list);
//        PageInfo pageInfo = new PageInfo(list);
//        System.out.println(JSON.toJSONString(pageInfo));
        System.out.println("return: "+list.size());
    }

    @Test
    public void test() {
//        HashMap<String, String> map = new HashMap<>();
//        Integer page = Integer.parseInt(map.get("page") != null ? map.get("page") : "0");
//        System.out.println(page == null);
        String str = ",12,123,1245";
        String[] split = str.split(",");
        for( String s: split ){
            System.out.println(s);
        }
    }
}
