package util;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: PageResult
 * @Author: LMK
 * @Date: 2020/5/19 10:45
 * @Version: 1.0
 **/
@Data
public class PageResult {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private int totalPages;
    /**
     * 数据模型
     */
    private List<?> content;

}
