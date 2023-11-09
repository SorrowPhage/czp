package com.sorrowphage.czp.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author: SorrowPhage
 * @date: 2023/11/9
 */
public class PageHelperService {

    protected void pageQuery(String currentPage, String pageNum) {
        this.pageQuery(Integer.parseInt(currentPage), Integer.parseInt(pageNum));
    }

    private void pageQuery(int currentPage, int pageNum) {
        PageHelper.startPage(currentPage, pageNum);
    }

    protected PageInfo pageResult(List<?> dataList) {
        return new PageInfo(dataList);
    }

}
