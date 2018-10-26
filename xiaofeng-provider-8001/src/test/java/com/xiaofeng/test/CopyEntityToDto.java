package com.xiaofeng.test;

import com.alibaba.fastjson.JSON;
import com.xiaofeng.blogs.collect.dto.CollectDto;
import com.xiaofeng.blogs.collect.entity.CollectEntity;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/16 21:58
 * @Description:
 */
public class CopyEntityToDto {
    public static void main(String[] args) {
        List<CollectEntity> collectEntities = new ArrayList<>();
        CollectEntity collectEntity = new CollectEntity();
        collectEntity.setId(1);
        collectEntities.add(collectEntity);
        List<CollectDto> collectDtos = new ArrayList<>();
        CollectDto collectDto = new CollectDto();
        try {
            BeanUtils.copyProperties(collectEntity, collectDto);
            collectDtos = JSON.parseArray(JSON.toJSONString(collectEntities), CollectDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(collectDtos.size());
        System.out.println(collectEntities.size());
        System.out.println(collectEntity.getId());
        System.out.println(collectDto.getId());
    }

    /**
     * 测试copy对象
     */
    @Test
    public void entityToDto(){
        CollectEntity collectEntity = new CollectEntity().setId(1);
//        CollectDto collectDto = new CollectDto();
        CollectDto collectDto = null;
        collectDto = collectEntity.entityToDto(CollectDto.class);
        System.out.println(collectDto);
    }
    /**
     * 测试copyList
     */
    @Test
    public void entitysToDtos(){
        CollectEntity collectEntity = new CollectEntity().setId(1);
        CollectEntity collectEntity1 = new CollectEntity().setId(2);
        CollectEntity collectEntity2 = new CollectEntity().setId(3);
        List<CollectEntity> collectEntities = new ArrayList<>();
        collectEntities.add(collectEntity);
        collectEntities.add(collectEntity1);
        collectEntities.add(collectEntity2);

        List<CollectDto> collectDtos = collectEntities.get(0).entitysToDtos(collectEntities, CollectDto.class);
        System.out.println(collectDtos);
    }
}
