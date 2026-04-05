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

}
