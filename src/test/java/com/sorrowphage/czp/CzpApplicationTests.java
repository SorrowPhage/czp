package com.sorrowphage.czp;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sorrowphage.czp.entity.UserGroup;
import com.sorrowphage.czp.mapper.UserGroupMapper;
import com.sorrowphage.czp.utils.CollectionUtils;
import com.sorrowphage.czp.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@SpringBootTest
class CzpApplicationTests {


    @Autowired
    UserGroupMapper userGroupMapper;


    @Test
    void contextLoads() {
        // System.out.println(bathPath);
        // System.out.println(RedisCache.VER_CODE_KEY +"470565129@qq.com");
        // redisCache.setCacheObject(RedisCache.VER_CODE_KEY + "470565129@qq.com", "213", 5, TimeUnit.MILLISECONDS);
        // String s = redisCache.getCacheObject(RedisCache.VER_CODE_KEY +"470565129@qq.com");
        // System.out.println(s);

        // userGroupMapper.isExist("p1","")
        // System.out.println(userGroupMapper.selectCount());
        // userGroupMapper.selectList()
        // LambdaUpdateWrapper<UserGroup> wrapper = new LambdaUpdateWrapper<>();
        // List<UserGroup> groupList = userGroupMapper.selectList(wrapper);
        Function<UserGroup, List<?>> userGroupListFunction = CollectionUtils.combinationKey(UserGroup::getGroupId, UserGroup::getParentId);
        System.out.println(userGroupListFunction);
    }

}
