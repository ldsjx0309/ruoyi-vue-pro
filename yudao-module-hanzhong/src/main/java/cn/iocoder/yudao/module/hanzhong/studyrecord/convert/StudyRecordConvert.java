package cn.iocoder.yudao.module.hanzhong.studyrecord.convert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.admin.vo.StudyRecordRespVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.controller.app.vo.AppStudyRecordRespVO;
import cn.iocoder.yudao.module.hanzhong.studyrecord.dal.dataobject.StudyRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 汉中 学习记录 Convert
 *
 * @author hanzhong
 */
@Mapper
public interface StudyRecordConvert {

    StudyRecordConvert INSTANCE = Mappers.getMapper(StudyRecordConvert.class);

    StudyRecordRespVO convert(StudyRecordDO record);

    PageResult<StudyRecordRespVO> convertPage(PageResult<StudyRecordDO> pageResult);

    AppStudyRecordRespVO convertApp(StudyRecordDO record);

    PageResult<AppStudyRecordRespVO> convertAppPage(PageResult<StudyRecordDO> pageResult);

}
