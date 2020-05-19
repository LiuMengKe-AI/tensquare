package com.tensquare.base.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.base.dao.LabelMapper;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import util.IdWorker;
import util.PageRequest;
import util.PageResult;
import util.PageUtils;

import java.util.List;

/**
 * @ClassName: LableService
 * @Author: LMK
 * @Date: 2020/5/13 16:14
 * @Version: 1.0
 **/
@Service
public class LabelService {
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     *
     * @return
     */
    public List<Label> findAll() {
        List<Label> labelDaoAll = labelMapper.findAll();
        return labelDaoAll;
    }

    /**
     * 根据id查询标签
     */
    public Label findById(String id) {
        Label label = labelMapper.selectByPrimaryKey(id);
        return label;
    }

    /**
     * 添加标签
     */
    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelMapper.insert(label);
    }

    /**
     * 修改标签
     */
    public void update(Label label) {
        labelMapper.updateByPrimaryKey(label);

    }

    /**
     * 删除标签
     */
    public void delete(String id) {
        labelMapper.deleteByPrimaryKey(id);
    }

    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }
    /**
     * 分页查询
     *
     * @param
     * @return
     */
    public PageInfo<Label> getPageInfo(PageRequest pageRequest) {
        int pageNumber = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNumber,pageSize);
        List<Label> pages = labelMapper.findPages();
        return new PageInfo<Label>(pages);

    }

}
