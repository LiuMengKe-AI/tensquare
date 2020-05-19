package util;

import com.github.pagehelper.PageInfo;

/**
 * @ClassName: PageUtils
 * @Author: LMK
 * @Date: 2020/5/19 10:49
 * @Version: 1.0
 **/
public class PageUtils {
    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
