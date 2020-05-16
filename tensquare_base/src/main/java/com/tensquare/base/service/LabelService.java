package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
     *
     * @return
     */
    public List<Label> findAll() {
        List<Label> labelDaoAll = labelDao.findAll();
        return labelDaoAll;
    }

    /**
     * 根据id查询标签
     */
    public Label findById(String id) {
        Optional<Label> label = labelDao.findById(id);
        return label.get();
    }

    /**
     * 添加标签
     */
    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    /**
     * 修改标签
     */
    public void update(Label label) {
        labelDao.save(label);

    }

    /**
     * 删除标签
     */
    public void delete(String id) {
        labelDao.deleteById(id);
    }

    /**
     * 分页条件查询
     *
     * @param label
     * @return
     */
    public Page<Label> findSearch(Label label, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateArrayList = new ArrayList<>();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) { //标签名称
                    predicateArrayList.add(criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"));
                }
                if (label.getState() != null && !"".equals(label.getState())) { //状态
                    predicateArrayList.add(criteriaBuilder.like(root.get("state").as(String.class), "%" + label.getState() + "%"));
                }
                if (label.getRecommend() != null && !"".equals(label.getRecommend())) {  //是否推荐
                    predicateArrayList.add(criteriaBuilder.like(root.get("recommend").as(String.class), "%" + label.getRecommend() + "%"));
                }

                return criteriaBuilder.and(predicateArrayList.toArray(new Predicate[predicateArrayList.size()]));
            }
        },pageRequest);
    }

}
