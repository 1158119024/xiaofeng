package com.xiaofeng.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/23 17:04
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
@Component
public class TokenEntity {

    private Integer userId;

    private String username;
}
