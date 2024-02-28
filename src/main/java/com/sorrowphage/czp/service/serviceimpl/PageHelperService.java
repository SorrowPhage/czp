package com.sorrowphage.czp.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author: SorrowPhage
 * @date: 2023/11/9
 */
public class PageHelperService {

    protected void pageQuery(String currentPage, String pageSize) {
        this.pageQuery(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
    }

    private void pageQuery(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
    }

    protected PageInfo pageResult(List<?> dataList) {
        return new PageInfo(dataList);
    }

}
