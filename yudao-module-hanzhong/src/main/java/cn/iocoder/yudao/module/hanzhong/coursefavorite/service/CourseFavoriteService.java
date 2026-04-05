package cn.iocoder.yudao.module.hanzhong.coursefavorite.service;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.coursefavorite.dal.dataobject.CourseFavoriteDO;

/**
 * 汉中 课程收藏 Service 接口
 *
 * @author hanzhong
 */
public interface CourseFavoriteService {

    /**
     * 切换收藏状态：已收藏则取消，未收藏则添加
     *
     * @return true-已收藏, false-已取消收藏
     */
    boolean toggleFavorite(Long userId, Long courseId);

    boolean isFavorited(Long userId, Long courseId);

    PageResult<CourseFavoriteDO> getMyFavoritePage(PageParam pageParam, Long userId);

}
