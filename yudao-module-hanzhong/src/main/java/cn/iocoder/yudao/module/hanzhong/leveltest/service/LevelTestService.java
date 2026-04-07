package cn.iocoder.yudao.module.hanzhong.leveltest.service;

import cn.iocoder.yudao.module.hanzhong.leveltest.controller.app.vo.AppLevelTestQuestionRespVO;
import cn.iocoder.yudao.module.hanzhong.leveltest.controller.app.vo.AppLevelTestResultRespVO;
import cn.iocoder.yudao.module.hanzhong.leveltest.controller.app.vo.AppLevelTestSubmitReqVO;

import java.util.List;

public interface LevelTestService {

    List<AppLevelTestQuestionRespVO> getQuestions(String target);

    AppLevelTestResultRespVO submit(Long userId, AppLevelTestSubmitReqVO reqVO);

    AppLevelTestResultRespVO getLatestResult(Long userId);
}
