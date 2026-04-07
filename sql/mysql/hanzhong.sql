-- ----------------------------
-- 汉中项目 (hanzhong) 初始化 SQL
-- ----------------------------

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hanzhong_banner
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_banner`;
CREATE TABLE `hanzhong_banner`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `pic_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片地址',
  `link_type` tinyint NULL DEFAULT 0 COMMENT '跳转类型（0-无跳转 1-内部页面 2-外部链接）',
  `link_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '跳转链接',
  `link_id` bigint NULL DEFAULT NULL COMMENT '跳转业务 id（如课程 id、职位 id 等）',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-开启 1-关闭）',
  `start_time` datetime NULL DEFAULT NULL COMMENT '生效开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '生效结束时间',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中首页 Banner' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_course_category
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_course_category`;
CREATE TABLE `hanzhong_course_category`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父分类编号',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-开启 1-关闭）',
  `icon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中课程分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_course
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_course`;
CREATE TABLE `hanzhong_course`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `category_id` bigint NOT NULL COMMENT '分类编号',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程标题',
  `cover_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面图片地址',
  `summary` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '简介',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '课程内容',
  `teacher_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '讲师名称',
  `price` int NOT NULL DEFAULT 0 COMMENT '价格（分）',
  `original_price` int NULL DEFAULT NULL COMMENT '原价（分）',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-开启 1-关闭）',
  `view_count` int NOT NULL DEFAULT 0 COMMENT '浏览量',
  `enroll_count` int NOT NULL DEFAULT 0 COMMENT '报名人数',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中课程' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_job
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_job`;
CREATE TABLE `hanzhong_job`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '职位名称',
  `company` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公司名称',
  `salary` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '薪资待遇',
  `location` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作地点',
  `category` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职位类别',
  `education` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学历要求',
  `experience` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经验要求',
  `headcount` int NULL DEFAULT NULL COMMENT '招聘人数',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '职位描述',
  `contact_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-开启 1-关闭）',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中职位' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_message
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_message`;
CREATE TABLE `hanzhong_message`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `type` tinyint NOT NULL DEFAULT 0 COMMENT '消息类型（0-系统消息 1-通知 2-活动）',
  `is_read` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已读',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中消息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_user_profile
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_user_profile`;
CREATE TABLE `hanzhong_user_profile`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像地址',
  `gender` tinyint NULL DEFAULT 0 COMMENT '性别（0-未知 1-男 2-女）',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `bio` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '个人简介',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-开启 1-关闭）',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中用户档案' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_card
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_card`;
CREATE TABLE `hanzhong_card`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '展示名称',
  `company` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公司',
  `position` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职位',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像地址',
  `qr_code_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名片二维码地址',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-开启 1-关闭）',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中名片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_resume
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_resume`;
CREATE TABLE `hanzhong_resume`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '求职者姓名',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `gender` tinyint NULL DEFAULT 0 COMMENT '性别（0-未知 1-男 2-女）',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `education` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学历',
  `school` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学校',
  `major` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专业',
  `work_experience` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作经验',
  `current_position` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '当前职位',
  `current_company` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '当前公司',
  `skills` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '技能',
  `self_intro` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '自我介绍',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-开启 1-关闭）',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中简历' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_community_post
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_community_post`;
CREATE TABLE `hanzhong_community_post`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '发布者用户编号',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `cover_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面图片地址',
  `category` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类',
  `view_count` int NOT NULL DEFAULT 0 COMMENT '浏览量',
  `like_count` int NOT NULL DEFAULT 0 COMMENT '点赞数',
  `comment_count` int NOT NULL DEFAULT 0 COMMENT '评论数',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-正常 1-已下线）',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中社区帖子' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_course_order
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_course_order`;
CREATE TABLE `hanzhong_course_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `order_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `course_id` bigint NOT NULL COMMENT '课程编号',
  `course_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名称（快照）',
  `cover_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程封面（快照）',
  `price` int NOT NULL DEFAULT 0 COMMENT '实付价格（分）',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-待支付 1-已支付 2-已取消 3-已退款）',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '取消时间',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_order_no` (`order_no`) COMMENT '订单号唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中课程订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_study_record
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_study_record`;
CREATE TABLE `hanzhong_study_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `course_id` bigint NOT NULL COMMENT '课程编号',
  `course_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名称（快照）',
  `cover_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程封面（快照）',
  `last_section_id` bigint NULL DEFAULT NULL COMMENT '最后学习的章节编号（断点续播）',
  `progress` int NOT NULL DEFAULT 0 COMMENT '学习进度（0-100）',
  `last_study_time` datetime NULL DEFAULT NULL COMMENT '最后学习时间',
  `finish_time` datetime NULL DEFAULT NULL COMMENT '完成时间',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-学习中 1-已完成）',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中学习记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_job_apply
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_job_apply`;
CREATE TABLE `hanzhong_job_apply`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `job_id` bigint NOT NULL COMMENT '职位编号',
  `job_title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职位名称（快照）',
  `company` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公司（快照）',
  `resume_id` bigint NULL DEFAULT NULL COMMENT '使用的简历编号',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-已投递 1-查看简历 2-邀请面试 3-不合适 4-已录用）',
  `apply_time` datetime NULL DEFAULT NULL COMMENT '申请时间',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中职位申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_card_exchange
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_card_exchange`;
CREATE TABLE `hanzhong_card_exchange`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '发起人用户编号',
  `target_user_id` bigint NOT NULL COMMENT '被查看名片的用户编号',
  `target_card_id` bigint NOT NULL COMMENT '名片记录编号',
  `target_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名片姓名（快照）',
  `target_company` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名片公司（快照）',
  `exchange_time` datetime NULL DEFAULT NULL COMMENT '交换时间',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中名片交换记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_course_section
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_course_section`;
CREATE TABLE `hanzhong_course_section`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `course_id` bigint NOT NULL COMMENT '所属课程编号',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '章节标题',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '章节描述',
  `video_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频地址',
  `duration` int NULL DEFAULT NULL COMMENT '视频时长（秒）',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `free_preview` tinyint NOT NULL DEFAULT 0 COMMENT '是否免费试看（0-否 1-是）',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-开启 1-关闭）',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id_sort` (`course_id`, `sort`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中课程章节' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_community_post_comment
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_community_post_comment`;
CREATE TABLE `hanzhong_community_post_comment`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `post_id` bigint NOT NULL COMMENT '帖子编号',
  `user_id` bigint NOT NULL COMMENT '评论用户编号',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父评论编号（0-顶层评论）',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-正常 1-已屏蔽）',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_post_id_status` (`post_id`, `status`) USING BTREE,
  INDEX `idx_user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中社区帖子评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hanzhong_course_favorite
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_course_favorite`;
CREATE TABLE `hanzhong_course_favorite`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `course_id` bigint NOT NULL COMMENT '课程编号',
  `course_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名称（冗余）',
  `cover_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程封面（冗余）',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_course` (`user_id`, `course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中课程收藏' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Indexes for hanzhong tables (performance optimization)
-- ----------------------------

-- hanzhong_banner: 按排序查询
CREATE INDEX idx_banner_sort_status ON `hanzhong_banner` (`sort`, `status`);

-- hanzhong_course_category: 按父分类查询
CREATE INDEX idx_course_category_parent ON `hanzhong_course_category` (`parent_id`, `status`);

-- hanzhong_course: 按分类+状态+排序查询
CREATE INDEX idx_course_category_status ON `hanzhong_course` (`category_id`, `status`, `sort`);

-- hanzhong_message: 按用户+已读状态查询（消息中心高频）
CREATE INDEX idx_message_user_read ON `hanzhong_message` (`user_id`, `is_read`);

-- hanzhong_user_profile: 按用户查询（唯一）
CREATE UNIQUE INDEX uk_user_profile_user_id ON `hanzhong_user_profile` (`user_id`);

-- hanzhong_card: 按用户查询（唯一）
CREATE UNIQUE INDEX uk_card_user_id ON `hanzhong_card` (`user_id`);

-- hanzhong_resume: 按用户查询（唯一）
CREATE UNIQUE INDEX uk_resume_user_id ON `hanzhong_resume` (`user_id`);

-- hanzhong_course_order: 按用户+课程+状态查询（去重检查高频）
CREATE INDEX idx_course_order_user_course ON `hanzhong_course_order` (`user_id`, `course_id`, `status`);

-- hanzhong_study_record: 按用户+课程查询（唯一，进度更新高频）
CREATE UNIQUE INDEX uk_study_record_user_course ON `hanzhong_study_record` (`user_id`, `course_id`);

-- hanzhong_job_apply: 按用户+职位+状态查询（去重检查高频）
CREATE INDEX idx_job_apply_user_job ON `hanzhong_job_apply` (`user_id`, `job_id`, `status`);

-- hanzhong_card_exchange: 按发起人+时间查询
CREATE INDEX idx_card_exchange_user ON `hanzhong_card_exchange` (`user_id`);


-- hanzhong_job: 按状态+排序查询（首页推荐职位）
CREATE INDEX idx_job_status_sort ON `hanzhong_job` (`status`, `sort`);

-- hanzhong_community_post: 按用户查询（我的帖子）
CREATE INDEX idx_community_post_user ON `hanzhong_community_post` (`user_id`);

-- hanzhong_community_post: 按状态+时间查询（公开帖子列表）
CREATE INDEX idx_community_post_status_time ON `hanzhong_community_post` (`status`, `create_time`);


-- ----------------------------
-- Admin Menu (system_menu) entries for the hanzhong module
-- IDs: 5100 ~ 5148 (reserved range, no conflict with existing 5046 max)
-- ----------------------------

-- 汉中管理 – root directory
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5100, '汉中管理', '', 1, 50, 0, '/hanzhong', 'ep:location', NULL, NULL, 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 概览统计
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5101, '概览统计', '', 2, 1, 5100, 'overview', 'ep:data-analysis', 'hanzhong/overview/index', 'HanzhongOverview', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5102, '概览统计查询', 'hanzhong:overview:query', 3, 1, 5101, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- Banner 管理
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5103, 'Banner 管理', '', 2, 2, 5100, 'banner', 'ep:picture', 'hanzhong/banner/index', 'HanzhongBanner', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5104, 'Banner 查询', 'hanzhong:banner:query', 3, 1, 5103, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5105, 'Banner 创建', 'hanzhong:banner:create', 3, 2, 5103, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5106, 'Banner 更新', 'hanzhong:banner:update', 3, 3, 5103, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5107, 'Banner 删除', 'hanzhong:banner:delete', 3, 4, 5103, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 课程分类管理
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5108, '课程分类管理', '', 2, 3, 5100, 'course-category', 'ep:collection-tag', 'hanzhong/courseCategory/index', 'HanzhongCourseCategory', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5109, '课程分类查询', 'hanzhong:course-category:query', 3, 1, 5108, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5110, '课程分类创建', 'hanzhong:course-category:create', 3, 2, 5108, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5111, '课程分类更新', 'hanzhong:course-category:update', 3, 3, 5108, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5112, '课程分类删除', 'hanzhong:course-category:delete', 3, 4, 5108, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 课程管理
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5113, '课程管理', '', 2, 4, 5100, 'course', 'ep:reading', 'hanzhong/course/index', 'HanzhongCourse', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5114, '课程查询', 'hanzhong:course:query', 3, 1, 5113, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5115, '课程创建', 'hanzhong:course:create', 3, 2, 5113, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5116, '课程更新', 'hanzhong:course:update', 3, 3, 5113, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5117, '课程删除', 'hanzhong:course:delete', 3, 4, 5113, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 职位管理
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5118, '职位管理', '', 2, 5, 5100, 'job', 'ep:briefcase', 'hanzhong/job/index', 'HanzhongJob', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5119, '职位查询', 'hanzhong:job:query', 3, 1, 5118, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5120, '职位创建', 'hanzhong:job:create', 3, 2, 5118, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5121, '职位更新', 'hanzhong:job:update', 3, 3, 5118, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5122, '职位删除', 'hanzhong:job:delete', 3, 4, 5118, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 用户档案管理
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5123, '用户档案管理', '', 2, 6, 5100, 'user-profile', 'ep:user', 'hanzhong/userProfile/index', 'HanzhongUserProfile', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5124, '用户档案查询', 'hanzhong:user-profile:query', 3, 1, 5123, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5125, '用户档案状态更新', 'hanzhong:user-profile:update', 3, 2, 5123, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 课程订单管理
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5126, '课程订单管理', '', 2, 7, 5100, 'course-order', 'ep:shopping-cart', 'hanzhong/courseOrder/index', 'HanzhongCourseOrder', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5127, '课程订单查询', 'hanzhong:course-order:query', 3, 1, 5126, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5128, '课程订单状态更新', 'hanzhong:course-order:update', 3, 2, 5126, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 职位申请管理
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5129, '职位申请管理', '', 2, 8, 5100, 'job-apply', 'ep:document-checked', 'hanzhong/jobApply/index', 'HanzhongJobApply', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5130, '职位申请查询', 'hanzhong:job-apply:query', 3, 1, 5129, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5131, '职位申请状态更新', 'hanzhong:job-apply:update', 3, 2, 5129, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 消息管理
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5132, '消息管理', '', 2, 9, 5100, 'message', 'ep:bell', 'hanzhong/message/index', 'HanzhongMessage', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5133, '消息查询', 'hanzhong:message:query', 3, 1, 5132, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5134, '消息创建', 'hanzhong:message:create', 3, 2, 5132, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5135, '消息更新', 'hanzhong:message:update', 3, 3, 5132, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5136, '消息删除', 'hanzhong:message:delete', 3, 4, 5132, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 社区帖子管理
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5137, '社区帖子管理', '', 2, 10, 5100, 'community-post', 'ep:chat-dot-round', 'hanzhong/communityPost/index', 'HanzhongCommunityPost', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5138, '帖子查询', 'hanzhong:community-post:query', 3, 1, 5137, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5139, '帖子状态更新', 'hanzhong:community-post:update', 3, 2, 5137, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5149, '帖子删除', 'hanzhong:community-post:delete', 3, 3, 5137, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 名片管理
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5140, '名片管理', '', 2, 11, 5100, 'card', 'ep:postcard', 'hanzhong/card/index', 'HanzhongCard', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5141, '名片查询', 'hanzhong:card:query', 3, 1, 5140, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5142, '名片状态更新', 'hanzhong:card:update', 3, 2, 5140, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 简历管理
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5143, '简历管理', '', 2, 12, 5100, 'resume', 'ep:document', 'hanzhong/resume/index', 'HanzhongResume', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5144, '简历查询', 'hanzhong:resume:query', 3, 1, 5143, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 名片交换管理
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5145, '名片交换管理', '', 2, 13, 5100, 'card-exchange', 'ep:connection', 'hanzhong/cardExchange/index', 'HanzhongCardExchange', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5146, '名片交换查询', 'hanzhong:card-exchange:query', 3, 1, 5145, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 学习记录管理
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5147, '学习记录管理', '', 2, 14, 5100, 'study-record', 'ep:trend-charts', 'hanzhong/studyRecord/index', 'HanzhongStudyRecord', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5148, '学习记录查询', 'hanzhong:study-record:query', 3, 1, 5147, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 课程章节管理（IDs: 5150 ~ 5154）
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5150, '课程章节管理', '', 2, 15, 5100, 'course-section', 'ep:video-play', 'hanzhong/courseSection/index', 'HanzhongCourseSection', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5151, '章节查询', 'hanzhong:course-section:query', 3, 1, 5150, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5152, '章节创建', 'hanzhong:course-section:create', 3, 2, 5150, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5153, '章节更新', 'hanzhong:course-section:update', 3, 3, 5150, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5154, '章节删除', 'hanzhong:course-section:delete', 3, 4, 5150, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 帖子评论管理（IDs: 5155 ~ 5157）
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5155, '帖子评论管理', '', 2, 16, 5100, 'community-post-comment', 'ep:comment', 'hanzhong/communityPostComment/index', 'HanzhongCommunityPostComment', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5156, '评论查询', 'hanzhong:community-post-comment:query', 3, 1, 5155, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5157, '评论状态更新', 'hanzhong:community-post-comment:update', 3, 2, 5155, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5158, '评论删除', 'hanzhong:community-post-comment:delete', 3, 3, 5155, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 课程收藏管理菜单
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5159, '课程收藏管理', '', 2, 17, 5100, 'course-favorite', 'ep:star', 'hanzhong/courseFavorite/index', 'HanzhongCourseFavorite', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5160, '收藏查询', 'hanzhong:course-favorite:query', 3, 1, 5159, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5161, '收藏删除', 'hanzhong:course-favorite:delete', 3, 2, 5159, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Sample data for hanzhong tables (development / demo)
-- ----------------------------

-- Banner 示例数据
INSERT INTO `hanzhong_banner` (`id`, `title`, `pic_url`, `link_type`, `link_url`, `link_id`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '汉中职业技能培训', 'https://images.unsplash.com/photo-1524178232363-1fb2b075b655?w=750', 1, '/pages/course/list', NULL, 1, 0, '课程推荐 Banner', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '汉中人才招聘季', 'https://images.unsplash.com/photo-1521737711867-e3b97375f902?w=750', 1, '/pages/job/list', NULL, 2, 0, '职位招聘 Banner', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '职场名片交换', 'https://images.unsplash.com/photo-1586953208448-b95a79798f07?w=750', 1, '/pages/card/exchange', NULL, 3, 0, '名片交换 Banner', 'admin', NOW(), 'admin', NOW(), b'0');

-- 课程分类示例数据
INSERT INTO `hanzhong_course_category` (`id`, `name`, `parent_id`, `sort`, `status`, `icon`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '职业技能', 0, 1, 0, 'icon-skill', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '互联网技术', 0, 2, 0, 'icon-tech', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '电商运营', 0, 3, 0, 'icon-shop', 'admin', NOW(), 'admin', NOW(), b'0'),
(4, '财务会计', 0, 4, 0, 'icon-finance', 'admin', NOW(), 'admin', NOW(), b'0'),
(5, '语言能力', 0, 5, 0, 'icon-language', 'admin', NOW(), 'admin', NOW(), b'0'),
(6, 'Java 开发', 2, 1, 0, 'icon-java', 'admin', NOW(), 'admin', NOW(), b'0'),
(7, '前端开发', 2, 2, 0, 'icon-frontend', 'admin', NOW(), 'admin', NOW(), b'0');

-- 课程示例数据
INSERT INTO `hanzhong_course` (`id`, `category_id`, `title`, `cover_url`, `summary`, `content`, `teacher_name`, `price`, `original_price`, `view_count`, `enroll_count`, `sort`, `status`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 6, 'Java 零基础入门到精通', 'https://images.unsplash.com/photo-1517694712202-14dd9538aa97?w=400', 'Java 编程从零入门，适合完全无编程基础的学员', '课程内容涵盖 Java 基础语法、面向对象编程、集合框架、IO 流、多线程等核心知识点，并通过大量实战项目帮助学员快速成长。', '张伟', 0, 9900, 256, 128, 1, 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 6, 'Spring Boot 实战开发', 'https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=400', '快速掌握 Spring Boot 核心特性与项目实战', '本课程系统讲解 Spring Boot 自动配置、Web 开发、数据库操作、安全认证等核心功能，适合有 Java 基础的同学进阶学习。', '李明', 4900, 9900, 186, 72, 2, 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(3, 7, 'Vue3 + TypeScript 前端开发', 'https://images.unsplash.com/photo-1627398242454-45a1465c2479?w=400', 'Vue3 组合式 API 与 TypeScript 实战', '从 Vue3 基础到高级特性，结合 TypeScript 类型系统，学习现代前端开发最佳实践。', '王芳', 5900, 11900, 320, 95, 3, 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(4, 3, '电商运营实战技巧', 'https://images.unsplash.com/photo-1516321318423-f06f85e504b3?w=400', '从零到一打造爆款店铺', '系统讲解电商选品、店铺装修、活动运营、数据分析等核心运营技能，助力学员快速上手。', '赵静', 0, 6900, 198, 156, 4, 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(5, 4, '财务会计基础课程', 'https://images.unsplash.com/photo-1554224155-8d04cb21cd6c?w=400', '会计基础知识零基础入门', '全面讲解借贷记账法、财务报表编制、税务申报等实用财务知识，适合会计初学者。', '陈敏', 2900, 5800, 142, 88, 5, 0, 'admin', NOW(), 'admin', NOW(), b'0');

-- 职位示例数据
INSERT INTO `hanzhong_job` (`id`, `title`, `company`, `salary`, `location`, `category`, `education`, `experience`, `headcount`, `description`, `contact_name`, `contact_phone`, `sort`, `status`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 'Java 后端开发工程师', '汉中科技有限公司', '10k-20k', '汉中市汉台区', '技术研发', '本科', '1-3年', 3, '负责公司核心业务系统的设计与开发，要求熟悉 Java、Spring Boot、MySQL 等技术栈。', '张人事', '13800138001', 1, 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '前端开发工程师', '汉中云联网络科技', '8k-15k', '汉中市南郑区', '技术研发', '大专', '1-3年', 2, '负责公司 H5/小程序/Web 前端开发，熟悉 Vue3、微信小程序开发优先。', '李人事', '13800138002', 2, 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '运营专员', '汉中电商发展中心', '5k-8k', '汉中市城固县', '运营推广', '大专', '不限', 5, '负责公司电商平台的日常运营工作，包括内容编辑、活动策划、数据分析等。', '王人事', '13800138003', 3, 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(4, '会计出纳', '汉中盛达商贸有限公司', '4k-6k', '汉中市勉县', '财务', '大专', '1年以上', 1, '负责公司日常账务处理、银行对账、税务申报等工作，有相关工作经验优先。', '赵人事', '13800138004', 4, 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(5, '客服专员', '汉中优品科技', '4k-6k', '汉中市汉台区', '客服', '高中/中专', '不限', 10, '负责线上客户咨询接待，处理订单售后问题，要求普通话标准，有良好的沟通能力。', '陈人事', '13800138005', 5, 0, 'admin', NOW(), 'admin', NOW(), b'0');

-- 社区帖子示例数据
INSERT INTO `hanzhong_community_post` (`id`, `user_id`, `title`, `content`, `cover_url`, `category`, `view_count`, `like_count`, `comment_count`, `status`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 1, '分享我的 Java 学习路线', '经过半年的努力，我终于拿到了心仪公司的 offer！在这里分享一下我的 Java 学习路线，希望对大家有帮助。\n\n1. 基础阶段：Java 核心语法、面向对象、集合框架\n2. 进阶阶段：多线程、JVM、设计模式\n3. 框架阶段：Spring、SpringBoot、MyBatis\n4. 项目实战：参与公司实习项目\n\n坚持就是胜利！', NULL, '求职经验', 86, 12, 5, 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 2, '求职路上的一些心得体会', '最近成功拿到了三家公司的 offer，想和大家分享一下求职经验。\n\n简历方面：突出项目经验，量化成果；\n面试方面：多刷 LeetCode，准备好场景题；\n最重要的是保持好心态，每一次面试都是成长。\n\n加油，大家都能找到好工作！', NULL, '求职经验', 64, 8, 3, 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(3, 3, '汉中人才招聘大会参会总结', '上周参加了汉中市举办的人才招聘大会，现场来了很多企业，就业机会很多！\n\n现场看到的岗位以互联网、电商、财务为主，薪资范围 4000-20000 不等。\n建议大家多参加这类活动，现场交流效率比网上投简历高很多。', NULL, '行业动态', 42, 6, 2, 0, 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Table structure for hanzhong_community_post_like
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_community_post_like`;
CREATE TABLE `hanzhong_community_post_like`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '点赞用户编号',
  `post_id` bigint NOT NULL COMMENT '帖子编号',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_post` (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '汉中社区帖子点赞' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Sample data: hanzhong_community_post_like
-- ----------------------------
INSERT INTO `hanzhong_community_post_like` (`id`, `user_id`, `post_id`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 2, 1, 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 3, 1, 'admin', NOW(), 'admin', NOW(), b'0'),
(3, 1, 2, 'admin', NOW(), 'admin', NOW(), b'0'),
(4, 3, 2, 'admin', NOW(), 'admin', NOW(), b'0'),
(5, 1, 3, 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Sample data: hanzhong_course_section (5 courses × multiple sections)
-- ----------------------------
INSERT INTO `hanzhong_course_section` (`id`, `course_id`, `title`, `description`, `video_url`, `duration`, `sort`, `free_preview`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
-- 课程 1: Java 零基础入门
(1,  1, '第一章 Java 简介与环境搭建', 'JDK 安装、IDEA 开发环境配置，编写第一个 Java 程序', '', 1800, 1, 1, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(2,  1, '第二章 基础语法与数据类型', '变量、数据类型、运算符、流程控制语句', '', 2400, 2, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(3,  1, '第三章 面向对象编程', '类、对象、继承、封装、多态', '', 3600, 3, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(4,  1, '第四章 集合框架', 'List、Map、Set 核心集合类使用详解', '', 2700, 4, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(5,  1, '第五章 IO 流与文件操作', '字节流、字符流、缓冲流、NIO 基础', '', 2100, 5, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(6,  1, '第六章 多线程基础', '线程创建、同步机制、线程池入门', '', 2400, 6, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
-- 课程 2: Spring Boot 实战开发
(7,  2, '第一章 Spring Boot 入门', 'Spring Boot 核心特性与自动配置原理', '', 1800, 1, 1, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(8,  2, '第二章 Web 开发实战', 'RESTful API 设计、Controller 开发、参数校验', '', 2400, 2, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(9,  2, '第三章 数据库集成', 'MyBatis-Plus、JPA 配置与使用', '', 2700, 3, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(10, 2, '第四章 安全认证', 'Spring Security + JWT 认证授权', '', 2400, 4, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(11, 2, '第五章 项目实战', '完整 Spring Boot 项目从零搭建到部署', '', 3600, 5, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
-- 课程 3: Vue3 + TypeScript 前端开发
(12, 3, '第一章 Vue3 基础与组合式 API', 'setup()、ref、reactive、computed、watch', '', 2400, 1, 1, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(13, 3, '第二章 TypeScript 类型系统', '基础类型、接口、泛型、类型推断', '', 2100, 2, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(14, 3, '第三章 Vue Router 路由', '动态路由、嵌套路由、路由守卫', '', 1800, 3, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(15, 3, '第四章 Pinia 状态管理', 'Store 定义、state/getters/actions 最佳实践', '', 1800, 4, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(16, 3, '第五章 项目实战：后台管理系统', '综合运用 Vue3 + TypeScript + Pinia 构建完整项目', '', 4200, 5, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
-- 课程 4: 电商运营实战技巧
(17, 4, '第一章 电商行业概览', '主流平台对比、选品思路、蓝海市场发现', '', 1500, 1, 1, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(18, 4, '第二章 店铺装修与视觉设计', '首页设计、详情页优化、主图拍摄技巧', '', 1800, 2, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(19, 4, '第三章 活动运营与推广', '直通车、超级推荐、店铺活动策划', '', 2100, 3, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(20, 4, '第四章 数据分析与优化', '生意参谋使用、转化率分析、流量诊断', '', 1800, 4, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
-- 课程 5: 财务会计基础
(21, 5, '第一章 借贷记账法原理', '账户体系、借贷方向、记账规则', '', 1800, 1, 1, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(22, 5, '第二章 凭证填制与账簿登记', '原始凭证、记账凭证、账簿登记规范', '', 1800, 2, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(23, 5, '第三章 财务报表编制', '资产负债表、利润表、现金流量表编制', '', 2400, 3, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0'),
(24, 5, '第四章 税务申报实务', '增值税、企业所得税申报流程', '', 2100, 4, 0, 0, '', 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Sample data: hanzhong_community_post_comment
-- ----------------------------
INSERT INTO `hanzhong_community_post_comment` (`id`, `post_id`, `user_id`, `parent_id`, `content`, `status`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 1, 2, 0, '感谢分享！学习路线写得很清晰，跟着这个思路来学一定没问题！', 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 1, 3, 0, '请问从零基础到能应聘大概需要多久呢？', 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(3, 1, 1, 2, '一般认真学 6-8 个月可以达到初级水平，关键要多练项目。', 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(4, 2, 1, 0, '太实用了！面试准备这块确实很重要，量化成果这个思路很好。', 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(5, 2, 3, 0, '请问你刷的 LeetCode 主要刷哪些类型的题目？', 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(6, 3, 1, 0, '汉中真是越来越多机会了，之前还担心本地就业，现在看来不错！', 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(7, 3, 2, 0, '下次再有招聘大会的时候提前通知一下，上次错过了可惜。', 0, 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Sample data: hanzhong_user_profile
-- ----------------------------
INSERT INTO `hanzhong_user_profile` (`id`, `user_id`, `nickname`, `avatar_url`, `gender`, `phone`, `email`, `address`, `bio`, `status`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 1, '张磊', 'https://api.dicebear.com/7.x/avataaars/svg?seed=1', 1, '13800138001', 'zhanglei@example.com', '汉中市汉台区', '热爱技术，全栈开发爱好者，目前就职于汉中某互联网公司', 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 2, '李娜', 'https://api.dicebear.com/7.x/avataaars/svg?seed=2', 2, '13900139002', 'lina@example.com', '汉中市南郑区', '人力资源专家，擅长招聘与团队管理，10年HR经验', 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(3, 3, '王浩', 'https://api.dicebear.com/7.x/avataaars/svg?seed=3', 1, '13700137003', 'wanghao@example.com', '汉中市城固县', '应届毕业生，计算机科学专业，求职Java后端开发方向', 0, 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Sample data: hanzhong_card
-- ----------------------------
INSERT INTO `hanzhong_card` (`id`, `user_id`, `name`, `company`, `position`, `phone`, `email`, `avatar_url`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 1, '张磊', '汉中云创科技有限公司', '高级Java工程师', '13800138001', 'zhanglei@example.com', 'https://api.dicebear.com/7.x/avataaars/svg?seed=1', 0, NULL, 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 2, '李娜', '汉中人力资源服务有限公司', '人力资源总监', '13900139002', 'lina@example.com', 'https://api.dicebear.com/7.x/avataaars/svg?seed=2', 0, NULL, 'admin', NOW(), 'admin', NOW(), b'0'),
(3, 3, '王浩', NULL, '求职中-Java后端工程师', '13700137003', 'wanghao@example.com', 'https://api.dicebear.com/7.x/avataaars/svg?seed=3', 0, NULL, 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Sample data: hanzhong_resume
-- ----------------------------
INSERT INTO `hanzhong_resume` (`id`, `user_id`, `name`, `phone`, `email`, `gender`, `education`, `school`, `major`, `work_experience`, `current_position`, `current_company`, `skills`, `self_intro`, `status`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 1, '张磊', '13800138001', 'zhanglei@example.com', 1, '本科', '陕西理工大学', '计算机科学与技术', '5年以上', '高级Java工程师', '汉中云创科技有限公司', 'Java, Spring Boot, MySQL, Redis, Docker, Vue3', '5年Java开发经验，熟悉微服务架构，主导过多个企业级项目开发', 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 2, '李娜', '13900139002', 'lina@example.com', 2, '本科', '汉中职业技术学院', '人力资源管理', '10年以上', '人力资源总监', '汉中人力资源服务有限公司', '人才招募, 绩效管理, 劳动法规, 薪酬体系设计', '10年以上HR管理经验，主导建立多家企业人才体系，擅长招聘规划与团队建设', 0, 'admin', NOW(), 'admin', NOW(), b'0'),
(3, 3, '王浩', '13700137003', 'wanghao@example.com', 1, '本科', '陕西理工大学', '软件工程', '应届', NULL, NULL, 'Java, Spring Boot, MySQL, Git, Vue基础', '应届毕业生，熟悉Java后端开发，在校期间完成多个项目实践，积极主动，学习能力强', 0, 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Sample data: hanzhong_course_order (user 3 bought course 1, user 1 bought course 2)
-- ----------------------------
INSERT INTO `hanzhong_course_order` (`id`, `user_id`, `order_no`, `course_id`, `course_name`, `cover_url`, `price`, `status`, `pay_time`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 3, 'HZ20240101001', 1, 'Java 零基础入门', 'https://images.unsplash.com/photo-1516116216624-53e697fedbea?w=400', 0, 1, NOW(), 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 1, 'HZ20240101002', 2, 'Spring Boot 实战开发', 'https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=400', 9900, 1, NOW(), 'admin', NOW(), 'admin', NOW(), b'0'),
(3, 2, 'HZ20240101003', 4, '电商运营实战技巧', 'https://images.unsplash.com/photo-1556742049-0cfed4f6a45d?w=400', 14900, 1, NOW(), 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Sample data: hanzhong_study_record
-- ----------------------------
INSERT INTO `hanzhong_study_record` (`id`, `user_id`, `course_id`, `course_name`, `cover_url`, `last_section_id`, `progress`, `status`, `last_study_time`, `finish_time`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 3, 1, 'Java 零基础入门', 'https://images.unsplash.com/photo-1516116216624-53e697fedbea?w=400', 2, 45, 0, NOW(), NULL, 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 1, 2, 'Spring Boot 实战开发', 'https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=400', NULL, 100, 1, NOW(), NOW(), 'admin', NOW(), 'admin', NOW(), b'0'),
(3, 2, 4, '电商运营实战技巧', 'https://images.unsplash.com/photo-1556742049-0cfed4f6a45d?w=400', NULL, 30, 0, NOW(), NULL, 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Sample data: hanzhong_job_apply
-- ----------------------------
INSERT INTO `hanzhong_job_apply` (`id`, `user_id`, `job_id`, `job_title`, `company`, `resume_id`, `status`, `remark`, `apply_time`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 3, 1, 'Java 后端开发工程师', '汉中云创科技有限公司', 3, 0, NULL, NOW(), 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 3, 3, '产品经理', '汉中数字科技有限公司', 3, 2, '经验背景不错，邀请明天下午参加面试', NOW(), 'admin', NOW(), 'admin', NOW(), b'0'),
(3, 1, 2, '前端开发工程师（Vue3）', '汉中互联网科技有限公司', 1, 1, NULL, NOW(), 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Sample data: hanzhong_card_exchange
-- ----------------------------
INSERT INTO `hanzhong_card_exchange` (`id`, `user_id`, `target_user_id`, `target_card_id`, `target_name`, `target_company`, `exchange_time`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 3, 1, 1, '张磊', '汉中云创科技有限公司', NOW(), NULL, 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 3, 2, 2, '李娜', '汉中人力资源服务有限公司', NOW(), NULL, 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Sample data: hanzhong_message (系统通知)
-- ----------------------------
INSERT INTO `hanzhong_message` (`id`, `user_id`, `title`, `content`, `type`, `is_read`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 3, '欢迎加入汉中职业发展平台', '您好，欢迎注册汉中职业发展平台！平台提供职业技能培训、职位招聘、职场社区等服务，祝您求职顺利！', 0, b'0', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 3, '职位投递成功通知', '您已成功投递「Java 后端开发工程师」职位（汉中云创科技有限公司），请耐心等待 HR 查看。', 1, b'0', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, 3, '收到面试邀请', '恭喜！您投递的职位《产品经理》（汉中数字科技有限公司）邀请您参加面试，请及时确认。', 1, b'1', 'admin', NOW(), 'admin', NOW(), b'0'),
(4, 1, '欢迎加入汉中职业发展平台', '您好，欢迎注册汉中职业发展平台！平台提供职业技能培训、职位招聘、职场社区等服务，祝您使用愉快！', 0, b'0', 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Sample data: hanzhong_course_favorite
-- ----------------------------
INSERT INTO `hanzhong_course_favorite` (`id`, `user_id`, `course_id`, `course_name`, `cover_url`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 3, 2, 'Spring Boot 实战开发', 'https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=400', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 3, 3, 'Vue3 + TypeScript 前端开发', 'https://images.unsplash.com/photo-1593720213428-28a5b9e94613?w=400', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, 1, 3, 'Vue3 + TypeScript 前端开发', 'https://images.unsplash.com/photo-1593720213428-28a5b9e94613?w=400', 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Menu permissions: resume delete, card delete (added in completeness pass)
-- ----------------------------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5162, '简历删除', 'hanzhong:resume:delete', 3, 2, 5143, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5163, '名片删除', 'hanzhong:card:delete', 3, 3, 5140, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
-- 学习记录管理新增：重置和删除权限（补全至 5165）
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5164, '学习记录重置', 'hanzhong:study-record:update', 3, 2, 5147, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5165, '学习记录删除', 'hanzhong:study-record:delete', 3, 3, 5147, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 名片交换删除权限（补全至 5166）
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5166, '名片交换删除', 'hanzhong:card-exchange:delete', 3, 2, 5145, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- 用户档案删除权限（补全至 5167）
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5167, '用户档案删除', 'hanzhong:user-profile:delete', 3, 3, 5123, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Data dictionaries for hanzhong module (IDs 2100+, data IDs 3100+)
-- These provide label/value mappings used by admin frontend dropdowns
-- ----------------------------
INSERT INTO `system_dict_type` (`id`, `name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`) VALUES (2100, '汉中课程订单状态', 'hanzhong_course_order_status', 0, '汉中课程订单状态', 'admin', NOW(), 'admin', NOW(), b'0', '1970-01-01 00:00:00');
INSERT INTO `system_dict_type` (`id`, `name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`) VALUES (2101, '汉中职位申请状态', 'hanzhong_job_apply_status', 0, '汉中职位申请状态', 'admin', NOW(), 'admin', NOW(), b'0', '1970-01-01 00:00:00');
INSERT INTO `system_dict_type` (`id`, `name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`) VALUES (2102, '汉中学习记录状态', 'hanzhong_study_record_status', 0, '汉中学习记录状态', 'admin', NOW(), 'admin', NOW(), b'0', '1970-01-01 00:00:00');
INSERT INTO `system_dict_type` (`id`, `name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`) VALUES (2103, '汉中消息类型', 'hanzhong_message_type', 0, '汉中系统消息类型', 'admin', NOW(), 'admin', NOW(), b'0', '1970-01-01 00:00:00');
INSERT INTO `system_dict_type` (`id`, `name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`) VALUES (2104, '汉中帖子状态', 'hanzhong_community_post_status', 0, '汉中社区帖子审核状态', 'admin', NOW(), 'admin', NOW(), b'0', '1970-01-01 00:00:00');
INSERT INTO `system_dict_type` (`id`, `name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`) VALUES (2105, '汉中Banner跳转类型', 'hanzhong_banner_link_type', 0, '汉中首页Banner跳转目标类型', 'admin', NOW(), 'admin', NOW(), b'0', '1970-01-01 00:00:00');

-- hanzhong_course_order_status
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3100, 1, '待支付', '0', 'hanzhong_course_order_status', 0, 'warning', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3101, 2, '已支付', '1', 'hanzhong_course_order_status', 0, 'success', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3102, 3, '已取消', '2', 'hanzhong_course_order_status', 0, 'info', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3103, 4, '已退款', '3', 'hanzhong_course_order_status', 0, 'danger', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
-- 退款申请中（status=4）和退款拒绝（status=5）
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3121, 5, '退款申请中', '4', 'hanzhong_course_order_status', 0, 'warning', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3122, 6, '退款拒绝', '5', 'hanzhong_course_order_status', 0, 'danger', '', '', 'admin', NOW(), 'admin', NOW(), b'0');

-- hanzhong_job_apply_status
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3104, 1, '已投递', '0', 'hanzhong_job_apply_status', 0, 'primary', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3105, 2, '查看简历', '1', 'hanzhong_job_apply_status', 0, 'primary', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3106, 3, '邀请面试', '2', 'hanzhong_job_apply_status', 0, 'success', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3107, 4, '不合适', '3', 'hanzhong_job_apply_status', 0, 'danger', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3108, 5, '已录用', '4', 'hanzhong_job_apply_status', 0, 'success', '', '', 'admin', NOW(), 'admin', NOW(), b'0');

-- hanzhong_study_record_status
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3109, 1, '学习中', '0', 'hanzhong_study_record_status', 0, 'primary', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3110, 2, '已完成', '1', 'hanzhong_study_record_status', 0, 'success', '', '', 'admin', NOW(), 'admin', NOW(), b'0');

-- hanzhong_message_type
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3111, 1, '系统通知', '0', 'hanzhong_message_type', 0, 'primary', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3112, 2, '申请状态更新', '1', 'hanzhong_message_type', 0, 'warning', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3113, 3, '课程通知', '2', 'hanzhong_message_type', 0, 'success', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3114, 4, '名片通知', '3', 'hanzhong_message_type', 0, 'info', '', '', 'admin', NOW(), 'admin', NOW(), b'0');

-- hanzhong_community_post_status
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3115, 1, '正常', '0', 'hanzhong_community_post_status', 0, 'success', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3116, 2, '已屏蔽', '1', 'hanzhong_community_post_status', 0, 'danger', '', '', 'admin', NOW(), 'admin', NOW(), b'0');

-- hanzhong_banner_link_type
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3117, 1, '不跳转', '0', 'hanzhong_banner_link_type', 0, 'default', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3118, 2, '跳转课程', '1', 'hanzhong_banner_link_type', 0, 'primary', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3119, 3, '跳转职位', '2', 'hanzhong_banner_link_type', 0, 'primary', '', '', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (3120, 4, '自定义链接', '3', 'hanzhong_banner_link_type', 0, 'warning', '', '', 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Table structure for hanzhong_job_collect (职位收藏)
-- Added in this session to support job bookmarking feature
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_job_collect`;
CREATE TABLE `hanzhong_job_collect`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `job_id` bigint NOT NULL COMMENT '职位编号',
  `job_title` varchar(100) NULL DEFAULT NULL COMMENT '职位标题（冗余）',
  `company` varchar(100) NULL DEFAULT NULL COMMENT '公司名称（冗余）',
  `creator` varchar(64) NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_job`(`user_id` ASC, `job_id` ASC) COMMENT '每个用户对同一职位只能收藏一次'
) ENGINE = InnoDB AUTO_INCREMENT = 1 COMMENT = '汉中 职位收藏表';

-- ----------------------------
-- Menu permissions: 职位收藏管理 (IDs: 5168 ~ 5170)
-- ----------------------------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5168, '职位收藏管理', '', 2, 18, 5100, 'job-collect', 'ep:collection', 'hanzhong/jobCollect/index', 'HanzhongJobCollect', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5169, '职位收藏查询', 'hanzhong:job-collect:query', 3, 1, 5168, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5170, '职位收藏删除', 'hanzhong:job-collect:delete', 3, 2, 5168, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Sample data: hanzhong_job_collect
-- ----------------------------
INSERT INTO `hanzhong_job_collect` (`id`, `user_id`, `job_id`, `job_title`, `company`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 3, 2, '前端开发工程师（Vue3）', '汉中互联网科技有限公司', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 3, 4, '数据分析师', '汉中大数据产业园有限公司', 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Dict: hanzhong_job_collect_status (职位收藏状态不需要单独字典，直接布尔值)
-- Added new dict_type for job apply type if not exists
-- ----------------------------


-- ==============================================================
-- New tables and menus added in this session
-- ==============================================================

-- ----------------------------
-- Table structure for hanzhong_course_rating (课程评分)
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_course_rating`;
CREATE TABLE `hanzhong_course_rating`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `course_id` bigint NOT NULL COMMENT '课程编号',
  `course_name` varchar(200) NULL DEFAULT NULL COMMENT '课程名称（冗余）',
  `rating` tinyint NOT NULL COMMENT '评分（1-5 星）',
  `comment` varchar(500) NULL DEFAULT NULL COMMENT '评价内容',
  `creator` varchar(64) NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_course`(`user_id` ASC, `course_id` ASC) COMMENT '每个用户对同一课程只能有一条评分记录'
) ENGINE = InnoDB AUTO_INCREMENT = 1 COMMENT = '汉中 课程评分表';

-- ----------------------------
-- Table structure for hanzhong_hot_keyword (热门搜索关键词)
-- ----------------------------
DROP TABLE IF EXISTS `hanzhong_hot_keyword`;
CREATE TABLE `hanzhong_hot_keyword`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `keyword` varchar(100) NOT NULL COMMENT '关键词',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序（升序）',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态（0-禁用 1-启用）',
  `creator` varchar(64) NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_keyword`(`keyword` ASC) COMMENT '关键词唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 1 COMMENT = '汉中 热门搜索关键词表';

-- ----------------------------
-- Sample data: hanzhong_hot_keyword
-- ----------------------------
INSERT INTO `hanzhong_hot_keyword` (`id`, `keyword`, `sort`, `status`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 'Java开发', 1, 1, 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '前端工程师', 2, 1, 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '数据分析', 3, 1, 'admin', NOW(), 'admin', NOW(), b'0'),
(4, '产品经理', 4, 1, 'admin', NOW(), 'admin', NOW(), b'0'),
(5, 'Python', 5, 1, 'admin', NOW(), 'admin', NOW(), b'0'),
(6, 'Spring Boot', 6, 1, 'admin', NOW(), 'admin', NOW(), b'0'),
(7, 'Vue3', 7, 1, 'admin', NOW(), 'admin', NOW(), b'0'),
(8, '全栈开发', 8, 1, 'admin', NOW(), 'admin', NOW(), b'0'),
(9, '运营', 9, 1, 'admin', NOW(), 'admin', NOW(), b'0'),
(10, '实习生', 10, 1, 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Menu permissions: 课程评分管理 (IDs: 5171 ~ 5173)
-- ----------------------------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5171, '课程评分管理', '', 2, 19, 5100, 'course-rating', 'ep:star', 'hanzhong/courseRating/index', 'HanzhongCourseRating', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5172, '课程评分查询', 'hanzhong:course-rating:query', 3, 1, 5171, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5173, '课程评分删除', 'hanzhong:course-rating:delete', 3, 2, 5171, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Menu permissions: 热门关键词管理 (IDs: 5174 ~ 5177)
-- ----------------------------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5174, '热门关键词管理', '', 2, 20, 5100, 'hot-keyword', 'ep:search', 'hanzhong/hotKeyword/index', 'HanzhongHotKeyword', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5175, '热门关键词查询', 'hanzhong:hot-keyword:query', 3, 1, 5174, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5176, '热门关键词新增', 'hanzhong:hot-keyword:create', 3, 2, 5174, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5177, '热门关键词修改', 'hanzhong:hot-keyword:update', 3, 3, 5174, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5178, '热门关键词删除', 'hanzhong:hot-keyword:delete', 3, 4, 5174, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Menu permissions: 新增权限（5179 ~ 5180）
-- 课程订单删除、职位申请删除（补全管理端权限）
-- ----------------------------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5179, '课程订单删除', 'hanzhong:course-order:delete', 3, 3, 5126, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (5180, '职位申请删除', 'hanzhong:job-apply:delete', 3, 3, 5129, '', '', '', '', 0, b'1', b'1', b'1', 'admin', NOW(), 'admin', NOW(), b'0');

-- ----------------------------
-- Update course order status comment (add status 4: 退款申请中)
-- Note: ALTER COLUMN COMMENT only changes metadata; existing data is unaffected.
-- The application code already handles status 4 = REFUND_REQUESTED.
-- ----------------------------
ALTER TABLE `hanzhong_course_order` MODIFY COLUMN `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0-待支付 1-已支付 2-已取消 3-已退款 4-退款申请中 5-退款拒绝）';

-- ----------------------------
-- Sample data: hanzhong_course_rating
-- ----------------------------
INSERT INTO `hanzhong_course_rating` (`id`, `user_id`, `course_id`, `course_name`, `rating`, `comment`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 3, 1, 'Java 零基础入门课程', 5, '课程内容非常详尽，讲师讲解清晰，非常适合零基础学员！', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 3, 2, 'Vue3 前端开发实战', 4, '实战案例很有参考价值，章节内容丰富。', 'admin', NOW(), 'admin', NOW(), b'0');
