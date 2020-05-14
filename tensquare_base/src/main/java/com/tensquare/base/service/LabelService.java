package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName: LableService
 * @Author: LMK
 * @Date: 2020/5/13 16:14
 * @Version: 1.0
 **/
@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     * @return
     */
    public List<Label> findAll(){
        List<Label> labelDaoAll = labelDao.findAll();
        return labelDaoAll;
    }

    /**
     * 根据id查询标签
     */
    public Label findById(String id){
        Optional<Label> label = labelDao.findById(id);
        return label.get();
    }
    /**
     * 添加标签
     */
    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    /**
     * 修改标签
     */
    public void update(Label label){
        labelDao.save(label);

    }
    /**
     * 删除标签
     */
    public void delete(String id){
        labelDao.deleteById(id);
    }
}
