package com.wxsk.platform.game.dao.param;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class BaseParam implements Pageable {

    /**
     * 页码
     * */
    protected int pageNumber;

    /**
     * 分页大小
     * */
    protected int pageSize;

    /**
     * 排序
     * */
    protected Sort sort;



    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public long getOffset() {
        return pageSize * (pageNumber < 0 ? 0 : pageNumber);
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public Pageable next() {
        return new BaseParam(getPageNumber() + 1, getPageSize(), getSort());
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }
    public Pageable previous() {
        return getPageNumber() == 0 ? this : new BaseParam(getPageNumber() - 1, getPageSize(), getSort());
    }
    @Override
    public Pageable first() {
        return new BaseParam(0, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    public BaseParam() {}

    public BaseParam(int pageNumber, int pageSize, Sort sort) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }
}
