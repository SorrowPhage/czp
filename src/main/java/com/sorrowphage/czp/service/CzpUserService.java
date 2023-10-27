package com.sorrowphage.czp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sorrowphage.czp.entity.CzpUser;
import com.sorrowphage.czp.entity.Email;
import com.sorrowphage.czp.entity.ResultMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-09-11
 */
public interface CzpUserService extends IService<CzpUser> {

    ResultMessage login(CzpUser user);

    ResultMessage logout();

    ResultMessage register(CzpUser czpUser);

    ResultMessage sendCode(Email email);
}
