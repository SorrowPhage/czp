package com.sorrowphage.czp;

import com.sorrowphage.czp.mapper.UserGroupMapper;
import com.sorrowphage.czp.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class CzpApplicationTests {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    UserGroupMapper userGroupMapper;

    @Value("${file.avatar.base}")
    private  String bathPath;

    @Test
    void contextLoads() {
        // System.out.println(bathPath);
        // System.out.println(RedisCache.VER_CODE_KEY +"470565129@qq.com");
        // redisCache.setCacheObject(RedisCache.VER_CODE_KEY + "470565129@qq.com", "213", 5, TimeUnit.MILLISECONDS);
        // String s = redisCache.getCacheObject(RedisCache.VER_CODE_KEY +"470565129@qq.com");
        // System.out.println(s);

        // userGroupMapper.isExist("p1","")
    }

}
