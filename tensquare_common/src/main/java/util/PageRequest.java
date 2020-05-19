package util;

import lombok.Data;

/**
 * @ClassName: PageRequest
 * @Author: LMK
 * @Date: 2020/5/19 10:41
 * @Version: 1.0
 **/
@Data
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;

}
