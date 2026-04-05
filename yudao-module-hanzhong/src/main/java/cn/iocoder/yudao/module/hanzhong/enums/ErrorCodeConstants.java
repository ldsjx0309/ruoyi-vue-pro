package cn.iocoder.yudao.module.hanzhong.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Hanzhong 错误码枚举类
 *
 * hanzhong 系统，使用 1-020-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== Banner 相关 1-020-001-000 ============
    ErrorCode BANNER_NOT_EXISTS = new ErrorCode(1_020_001_000, "Banner 不存在");

    // ========== 课程分类 1-020-002-000 ============
    ErrorCode COURSE_CATEGORY_NOT_EXISTS = new ErrorCode(1_020_002_000, "课程分类不存在");

    // ========== 课程 1-020-003-000 ============
    ErrorCode COURSE_NOT_EXISTS = new ErrorCode(1_020_003_000, "课程不存在");

    // ========== 职位 1-020-004-000 ============
    ErrorCode JOB_NOT_EXISTS = new ErrorCode(1_020_004_000, "职位不存在");

    // ========== 消息 1-020-005-000 ============
    ErrorCode MESSAGE_NOT_EXISTS = new ErrorCode(1_020_005_000, "消息不存在");

    // ========== 用户档案 1-020-006-000 ============
    ErrorCode USER_PROFILE_NOT_EXISTS = new ErrorCode(1_020_006_000, "用户档案不存在");

    // ========== 名片 1-020-007-000 ============
    ErrorCode CARD_NOT_EXISTS = new ErrorCode(1_020_007_000, "名片不存在");

    // ========== 简历 1-020-008-000 ============
    ErrorCode RESUME_NOT_EXISTS = new ErrorCode(1_020_008_000, "简历不存在");

    // ========== 社区帖子 1-020-009-000 ============
    ErrorCode COMMUNITY_POST_NOT_EXISTS = new ErrorCode(1_020_009_000, "社区帖子不存在");

    // ========== 课程订单 1-020-010-000 ============
    ErrorCode COURSE_ORDER_NOT_EXISTS = new ErrorCode(1_020_010_000, "课程订单不存在");
    ErrorCode COURSE_ALREADY_ORDERED = new ErrorCode(1_020_010_001, "您已购买该课程，请勿重复下单");

    // ========== 学习记录 1-020-011-000 ============
    ErrorCode STUDY_RECORD_NOT_EXISTS = new ErrorCode(1_020_011_000, "学习记录不存在");

    // ========== 职位申请 1-020-012-000 ============
    ErrorCode JOB_APPLY_NOT_EXISTS = new ErrorCode(1_020_012_000, "职位申请不存在");
    ErrorCode JOB_ALREADY_APPLIED = new ErrorCode(1_020_012_001, "您已申请过该职位，请勿重复投递");
    ErrorCode JOB_APPLY_CANNOT_WITHDRAW = new ErrorCode(1_020_012_002, "当前申请状态不允许撤回");

    // ========== 名片交换 1-020-013-000 ============
    ErrorCode CARD_EXCHANGE_NOT_EXISTS = new ErrorCode(1_020_013_000, "名片交换记录不存在");
    ErrorCode CARD_EXCHANGE_SELF_NOT_ALLOWED = new ErrorCode(1_020_013_001, "不能与自己交换名片");

}
