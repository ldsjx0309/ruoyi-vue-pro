# 汉中模块前端集成指南 — 优先级整改清单

> **更新时间**: 2026-04-07（最新补强版）  
> **后端仓库**: `ldsjx0309/ruoyi-vue-pro`（`yudao-module-hanzhong`，**✅ 后端主链路已全面闭环**）  
> **管理后台**: `ldsjx0309/yudao-ui-admin-vue3`（**需实现全部汉中模块页面**）  
> **小程序**: `ldsjx0309/yudao-mall-uniapp`（**需实现全部汉中模块页面**）

---

## 一、现状总结

| 仓库 | 状态 | 说明 |
|------|------|------|
| `ruoyi-vue-pro` | ✅ **已完成（主链路闭环）** | 19 张业务表、75+ 个管理端接口、50+ 个 App 端接口、完整示例数据、菜单权限 SQL（IDs 5100-5181）|
| `yudao-ui-admin-vue3` | ❌ **完全缺失** | `src/views/hanzhong/` 和 `src/api/hanzhong/` 目录均不存在 |
| `yudao-mall-uniapp` | ❌ **完全缺失** | 无任何汉中相关页面或接口调用文件 |

**结论：后端已完全就绪，可以立即切换到两个前端仓库进行端到端闭环推进。**

### ✅ 后端最新补强（2026-04-07 最终轮）

| 新增/增强 | 说明 |
|-----------|------|
| `GET /hanzhong/overview/pending-tasks` | 管理员待处理任务数（角标接口）：待审职位申请数 + 退款待审数 |
| `GET /hanzhong/overview/stats` 新增 `pendingJobApplies` | 概览统计新增"待审核申请数"字段 |
| `GET /hanzhong/job-apply/stats-by-status` | 职位申请按状态分布（看板视图/漏斗图数据源）|
| `GET /hanzhong/app/mine/notification-summary` | 小程序通知角标：未读消息数 + 有进展申请数 |
| SQL: `system_dict_data` ID 3123 | 修复 banner_link_type 外链条目缺少 ID 的问题 |

---

## 一点五、三端切换推进建议（现在可以切换了）

> **后端已完全就绪，可以立即切换到两个前端仓库批量推进。**

### 推进节奏建议

| 阶段 | 目标 | 预计轮次 |
|------|------|---------|
| **第 1 轮**（当前）| 管理后台 API 模块（`src/api/hanzhong/`）+ P0 页面（Overview、Course、Job、JobApply、CourseOrder） | 1 轮 |
| **第 2 轮** | 管理后台 P1 页面（Banner、CourseCategory、Message、CommunityPost、UserProfile） + 小程序 API 模块 + 小程序首页 | 1-2 轮 |
| **第 3 轮** | 小程序 P0 主链路（课程详情 + 下单、职位详情 + 投递、我的页） | 1 轮 |
| **第 4 轮** | 小程序 P1（档案/简历/名片/社区/消息） + 管理后台 P2 辅助页面 | 1-2 轮 |
| **第 5 轮** | P3 精细化（图表、骨架屏、字典渲染、角标联动） | 1 轮 |

### 关键接口速查（前端 API 对接起点）

#### 管理后台必对接接口（P0）
```
GET  /hanzhong/overview/stats          — 概览数据（仪表盘）
GET  /hanzhong/overview/pending-tasks  — 待处理任务数（角标）★新增
GET  /hanzhong/course/page             — 课程列表
POST /hanzhong/course/create           — 创建课程
PUT  /hanzhong/course/update           — 更新课程
GET  /hanzhong/job/page                — 职位列表
POST /hanzhong/job/create              — 创建职位
GET  /hanzhong/job-apply/page          — 职位申请列表
PUT  /hanzhong/job-apply/update-status — 更新申请状态（审核主链路）
GET  /hanzhong/job-apply/stats-by-status — 申请状态分布（看板）★新增
GET  /hanzhong/course-order/page       — 课程订单列表
PUT  /hanzhong/course-order/approve-refund — 审批通过退款
PUT  /hanzhong/course-order/reject-refund  — 拒绝退款
```

#### 小程序必对接接口（P0）
```
GET  /hanzhong/app/home                         — 首页聚合数据
GET  /hanzhong/app/course/page                  — 课程列表
GET  /hanzhong/app/course/get-detail?id=        — 课程详情（含章节）
POST /hanzhong/app/course-order/create          — 下单
PUT  /hanzhong/app/course-order/pay?id=         — 确认支付
GET  /hanzhong/app/job/page                     — 职位列表
GET  /hanzhong/app/job/get?id=                  — 职位详情
POST /hanzhong/app/job-apply/create             — 投递简历
GET  /hanzhong/app/mine/stats                   — 我的统计
GET  /hanzhong/app/mine/notification-summary    — 角标数据 ★新增
GET  /hanzhong/app/message/page                 — 消息列表
PUT  /hanzhong/app/message/read-all             — 一键已读
```

---

## 二、优先整改顺序（P0 → P3）

### P0 — 必须先完成（主链路阻塞）

这是让整条业务"能跑起来"的最小必要集合。

| 优先级 | 仓库 | 需新建 | 说明 |
|--------|------|--------|------|
| P0-1 | 管理后台 | `src/api/hanzhong/` API 模块 | 所有管理端接口文件，后续所有页面都依赖此 |
| P0-2 | 管理后台 | `src/views/hanzhong/overview/index.vue` | 概览统计仪表盘 |
| P0-3 | 管理后台 | `src/views/hanzhong/course/index.vue` | 课程管理列表/新建/编辑 |
| P0-4 | 管理后台 | `src/views/hanzhong/job/index.vue` | 职位管理列表/新建/编辑 |
| P0-5 | 管理后台 | `src/views/hanzhong/jobApply/index.vue` | 职位申请管理（审核主链路） |
| P0-6 | 管理后台 | `src/views/hanzhong/courseOrder/index.vue` | 课程订单管理 |
| P0-7 | 小程序 | `sheep/api/hanzhong/` API 模块 | 所有 App 端接口调用，所有小程序页面都依赖此 |
| P0-8 | 小程序 | `pages/hanzhong/home/index.vue` | 首页（Banner、分类、课程、职位、社区入口） |
| P0-9 | 小程序 | `pages/hanzhong/course/list.vue` | 课程列表 |
| P0-10 | 小程序 | `pages/hanzhong/course/detail.vue` | 课程详情 + 下单按钮 |
| P0-11 | 小程序 | `pages/hanzhong/job/list.vue` | 职位列表 |
| P0-12 | 小程序 | `pages/hanzhong/job/detail.vue` | 职位详情 + 投递按钮 |
| P0-13 | 小程序 | `pages/hanzhong/mine/index.vue` | 我的页（用户信息入口） |

---

### P1 — 完成核心业务闭环

| 优先级 | 仓库 | 需新建 | 说明 |
|--------|------|--------|------|
| P1-1 | 管理后台 | `src/views/hanzhong/banner/index.vue` | Banner 管理 |
| P1-2 | 管理后台 | `src/views/hanzhong/courseCategory/index.vue` | 课程分类管理 |
| P1-3 | 管理后台 | `src/views/hanzhong/message/index.vue` | 消息管理 + 广播发送 |
| P1-4 | 管理后台 | `src/views/hanzhong/communityPost/index.vue` | 社区帖子审核管理 |
| P1-5 | 管理后台 | `src/views/hanzhong/userProfile/index.vue` | 用户档案管理 |
| P1-6 | 小程序 | `pages/hanzhong/mine/profile.vue` | 我的档案（编辑用户资料） |
| P1-7 | 小程序 | `pages/hanzhong/mine/resume.vue` | 我的简历（创建/编辑） |
| P1-8 | 小程序 | `pages/hanzhong/mine/card.vue` | 我的名片（创建/编辑） |
| P1-9 | 小程序 | `pages/hanzhong/mine/job-apply.vue` | 我的求职申请列表 |
| P1-10 | 小程序 | `pages/hanzhong/mine/course-order.vue` | 我的课程订单列表 |
| P1-11 | 小程序 | `pages/hanzhong/mine/study-record.vue` | 我的学习记录列表 |
| P1-12 | 小程序 | `pages/hanzhong/message/list.vue` | 消息列表（已读/未读） |
| P1-13 | 小程序 | `pages/hanzhong/community/list.vue` | 社区帖子列表 |
| P1-14 | 小程序 | `pages/hanzhong/community/detail.vue` | 帖子详情 + 评论 + 点赞 |
| P1-15 | 小程序 | `pages/hanzhong/community/create.vue` | 发帖 |

---

### P2 — 补全辅助功能

| 优先级 | 仓库 | 需新建 | 说明 |
|--------|------|--------|------|
| P2-1 | 管理后台 | `src/views/hanzhong/studyRecord/index.vue` | 学习记录管理 |
| P2-2 | 管理后台 | `src/views/hanzhong/courseSection/index.vue` | 课程章节管理 |
| P2-3 | 管理后台 | `src/views/hanzhong/card/index.vue` | 名片管理 |
| P2-4 | 管理后台 | `src/views/hanzhong/resume/index.vue` | 简历管理 |
| P2-5 | 管理后台 | `src/views/hanzhong/cardExchange/index.vue` | 名片交换记录 |
| P2-6 | 管理后台 | `src/views/hanzhong/communityPostComment/index.vue` | 评论管理 |
| P2-7 | 管理后台 | `src/views/hanzhong/courseFavorite/index.vue` | 课程收藏管理 |
| P2-8 | 小程序 | `pages/hanzhong/course/study.vue` | 课程学习页（视频 + 进度更新） |
| P2-9 | 小程序 | `pages/hanzhong/mine/favorites.vue` | 我的课程收藏 |
| P2-10 | 小程序 | `pages/hanzhong/card/exchange.vue` | 名片交换（发起/记录列表） |
| P2-11 | 小程序 | `pages/hanzhong/search/index.vue` | 综合搜索（课程/职位/帖子） |

---

### P3 — 精细化收口

| 优先级 | 仓库 | 说明 |
|--------|------|------|
| P3-1 | 管理后台 | 概览仪表盘趋势图（ECharts，调用 `/hanzhong/overview/trend`） |
| P3-2 | 管理后台 | 用户活跃度详情（`/hanzhong/overview/user-activity?userId=`） |
| P3-3 | 小程序 | 课程详情中的章节列表折叠展示 |
| P3-4 | 小程序 | 消息未读角标（首页 + 我的页 badge） |
| P3-5 | 小程序 | 空状态、加载骨架屏、网络异常兜底 |
| P3-6 | 两端 | 字典枚举渲染（`hanzhong_course_order_status` 等 6 个字典类型） |

---

## 三、管理后台（yudao-ui-admin-vue3）实现规范

### 3.1 目录结构

```
src/
├── api/
│   └── hanzhong/
│       ├── banner.ts
│       ├── courseCategory.ts
│       ├── course.ts
│       ├── courseSection.ts
│       ├── job.ts
│       ├── jobApply.ts
│       ├── courseOrder.ts
│       ├── studyRecord.ts
│       ├── message.ts
│       ├── communityPost.ts
│       ├── communityPostComment.ts
│       ├── card.ts
│       ├── resume.ts
│       ├── cardExchange.ts
│       ├── courseFavorite.ts
│       ├── userProfile.ts
│       └── overview.ts
└── views/
    └── hanzhong/
        ├── overview/index.vue
        ├── banner/index.vue
        ├── courseCategory/index.vue
        ├── course/index.vue
        ├── courseSection/index.vue
        ├── job/index.vue
        ├── jobApply/index.vue
        ├── courseOrder/index.vue
        ├── studyRecord/index.vue
        ├── message/index.vue
        ├── communityPost/index.vue
        ├── communityPostComment/index.vue
        ├── card/index.vue
        ├── resume/index.vue
        ├── cardExchange/index.vue
        ├── courseFavorite/index.vue
        └── userProfile/index.vue
```

> **菜单 SQL 已就绪**。以上 `component` 路径已在 `sql/mysql/hanzhong.sql`（菜单 ID 5100~5163）中配置，
> 执行 SQL 后页面路由即生效，对应 `component_name` 映射如下：

| 菜单 ID | 路由 path | component | component_name |
|---------|-----------|-----------|----------------|
| 5101 | `hanzhong/overview` | `hanzhong/overview/index` | `HanzhongOverview` |
| 5103 | `hanzhong/banner` | `hanzhong/banner/index` | `HanzhongBanner` |
| 5108 | `hanzhong/course-category` | `hanzhong/courseCategory/index` | `HanzhongCourseCategory` |
| 5113 | `hanzhong/course` | `hanzhong/course/index` | `HanzhongCourse` |
| 5118 | `hanzhong/job` | `hanzhong/job/index` | `HanzhongJob` |
| 5123 | `hanzhong/user-profile` | `hanzhong/userProfile/index` | `HanzhongUserProfile` |
| 5126 | `hanzhong/course-order` | `hanzhong/courseOrder/index` | `HanzhongCourseOrder` |
| 5129 | `hanzhong/job-apply` | `hanzhong/jobApply/index` | `HanzhongJobApply` |
| 5132 | `hanzhong/message` | `hanzhong/message/index` | `HanzhongMessage` |
| 5137 | `hanzhong/community-post` | `hanzhong/communityPost/index` | `HanzhongCommunityPost` |
| 5140 | `hanzhong/card` | `hanzhong/card/index` | `HanzhongCard` |
| 5143 | `hanzhong/resume` | `hanzhong/resume/index` | `HanzhongResume` |
| 5145 | `hanzhong/card-exchange` | `hanzhong/cardExchange/index` | `HanzhongCardExchange` |
| 5147 | `hanzhong/study-record` | `hanzhong/studyRecord/index` | `HanzhongStudyRecord` |
| 5150 | `hanzhong/course-section` | `hanzhong/courseSection/index` | `HanzhongCourseSection` |
| 5155 | `hanzhong/community-post-comment` | `hanzhong/communityPostComment/index` | `HanzhongCommunityPostComment` |
| 5159 | `hanzhong/course-favorite` | `hanzhong/courseFavorite/index` | `HanzhongCourseFavorite` |

### 3.2 API 接口清单（管理端）

所有接口的 base URL：`/hanzhong`

#### Banner 管理（`/hanzhong/banner`）
```typescript
// src/api/hanzhong/banner.ts
export const getBannerPage = (params: PageParam & { title?: string; status?: number }) =>
  request.get({ url: '/hanzhong/banner/page', params })

export const getBanner = (id: number) =>
  request.get({ url: '/hanzhong/banner/get', params: { id } })

export const createBanner = (data: BannerCreateReqVO) =>
  request.post({ url: '/hanzhong/banner/create', data })

export const updateBanner = (data: BannerUpdateReqVO) =>
  request.put({ url: '/hanzhong/banner/update', data })

export const updateBannerStatus = (id: number, status: number) =>
  request.put({ url: '/hanzhong/banner/update-status', data: { id, status } })

export const deleteBanner = (id: number) =>
  request.delete({ url: '/hanzhong/banner/delete', params: { id } })
```

#### 课程分类（`/hanzhong/course-category`）
```typescript
export const getCourseCategoryPage = (params) =>
  request.get({ url: '/hanzhong/course-category/page', params })
export const createCourseCategory = (data) =>
  request.post({ url: '/hanzhong/course-category/create', data })
export const updateCourseCategory = (data) =>
  request.put({ url: '/hanzhong/course-category/update', data })
export const deleteCourseCategory = (id: number) =>
  request.delete({ url: '/hanzhong/course-category/delete', params: { id } })
```

#### 课程管理（`/hanzhong/course`）
```typescript
export const getCoursePage = (params) =>
  request.get({ url: '/hanzhong/course/page', params })
export const getCourse = (id: number) =>
  request.get({ url: '/hanzhong/course/get', params: { id } })
export const createCourse = (data) =>
  request.post({ url: '/hanzhong/course/create', data })
export const updateCourse = (data) =>
  request.put({ url: '/hanzhong/course/update', data })
export const updateCourseStatus = (id: number, status: number) =>
  request.put({ url: '/hanzhong/course/update-status', data: { id, status } })
export const deleteCourse = (id: number) =>
  request.delete({ url: '/hanzhong/course/delete', params: { id } })
```

#### 课程章节（`/hanzhong/course-section`）
```typescript
export const getCourseSectionPage = (params) =>
  request.get({ url: '/hanzhong/course-section/page', params })
export const createCourseSection = (data) =>
  request.post({ url: '/hanzhong/course-section/create', data })
export const updateCourseSection = (data) =>
  request.put({ url: '/hanzhong/course-section/update', data })
export const deleteCourseSection = (id: number) =>
  request.delete({ url: '/hanzhong/course-section/delete', params: { id } })
```

#### 职位管理（`/hanzhong/job`）
```typescript
export const getJobPage = (params) =>
  request.get({ url: '/hanzhong/job/page', params })
export const getJob = (id: number) =>
  request.get({ url: '/hanzhong/job/get', params: { id } })
export const createJob = (data) =>
  request.post({ url: '/hanzhong/job/create', data })
export const updateJob = (data) =>
  request.put({ url: '/hanzhong/job/update', data })
export const updateJobStatus = (id: number, status: number) =>
  request.put({ url: '/hanzhong/job/update-status', data: { id, status } })
export const deleteJob = (id: number) =>
  request.delete({ url: '/hanzhong/job/delete', params: { id } })
```

#### 职位申请（`/hanzhong/job-apply`）
```typescript
export const getJobApplyPage = (params) =>
  request.get({ url: '/hanzhong/job-apply/page', params })
export const getJobApply = (id: number) =>
  request.get({ url: '/hanzhong/job-apply/get', params: { id } })
// 审核操作（status: 0=投递 1=查看简历 2=邀请面试 3=不合适 4=录用）
export const updateJobApplyStatus = (id: number, status: number, remark?: string) =>
  request.put({ url: '/hanzhong/job-apply/update-status', data: { id, status, remark } })
export const batchUpdateJobApplyStatus = (ids: number[], status: number) =>
  request.put({ url: '/hanzhong/job-apply/batch-update-status', data: { ids, status } })
```

#### 课程订单（`/hanzhong/course-order`）
```typescript
export const getCourseOrderPage = (params) =>
  request.get({ url: '/hanzhong/course-order/page', params })
export const getCourseOrder = (id: number) =>
  request.get({ url: '/hanzhong/course-order/get', params: { id } })
// status: 0=待支付 1=已支付 2=已取消 3=已退款
export const updateCourseOrderStatus = (id: number, status: number) =>
  request.put({ url: '/hanzhong/course-order/update-status', data: { id, status } })
```

#### 学习记录（`/hanzhong/study-record`）
```typescript
export const getStudyRecordPage = (params) =>
  request.get({ url: '/hanzhong/study-record/page', params })
export const getStudyRecord = (id: number) =>
  request.get({ url: '/hanzhong/study-record/get', params: { id } })
```

#### 消息管理（`/hanzhong/message`）
```typescript
export const getMessagePage = (params) =>
  request.get({ url: '/hanzhong/message/page', params })
export const getMessage = (id: number) =>
  request.get({ url: '/hanzhong/message/get', params: { id } })
export const createMessage = (data) =>
  request.post({ url: '/hanzhong/message/create', data })
export const updateMessage = (data) =>
  request.put({ url: '/hanzhong/message/update', data })
export const deleteMessage = (id: number) =>
  request.delete({ url: '/hanzhong/message/delete', params: { id } })
// 广播消息给所有用户
export const broadcastMessage = (title: string, content: string, type: number) =>
  request.post({ url: '/hanzhong/message/broadcast', data: { title, content, type } })
```

#### 社区帖子（`/hanzhong/community-post`）
```typescript
export const getCommunityPostPage = (params) =>
  request.get({ url: '/hanzhong/community-post/page', params })
export const getCommunityPost = (id: number) =>
  request.get({ url: '/hanzhong/community-post/get', params: { id } })
// status: 0=正常 1=已屏蔽
export const updateCommunityPostStatus = (id: number, status: number) =>
  request.put({ url: '/hanzhong/community-post/update-status', data: { id, status } })
export const deleteCommunityPost = (id: number) =>
  request.delete({ url: '/hanzhong/community-post/delete', params: { id } })
```

#### 帖子评论（`/hanzhong/community-post-comment`）
```typescript
export const getCommunityPostCommentPage = (params) =>
  request.get({ url: '/hanzhong/community-post-comment/page', params })
export const updateCommunityPostCommentStatus = (id: number, status: number) =>
  request.put({ url: '/hanzhong/community-post-comment/update-status', params: { id, status } })
export const deleteCommunityPostComment = (id: number) =>
  request.delete({ url: '/hanzhong/community-post-comment/delete', params: { id } })
```

#### 名片（`/hanzhong/card`）
```typescript
export const getCardPage = (params) =>
  request.get({ url: '/hanzhong/card/page', params })
export const getCard = (id: number) =>
  request.get({ url: '/hanzhong/card/get', params: { id } })
export const updateCardStatus = (id: number, status: number) =>
  request.put({ url: '/hanzhong/card/update-status', data: { id, status } })
export const deleteCard = (id: number) =>
  request.delete({ url: '/hanzhong/card/delete', params: { id } })
```

#### 简历（`/hanzhong/resume`）
```typescript
export const getResumePage = (params) =>
  request.get({ url: '/hanzhong/resume/page', params })
export const getResume = (id: number) =>
  request.get({ url: '/hanzhong/resume/get', params: { id } })
export const deleteResume = (id: number) =>
  request.delete({ url: '/hanzhong/resume/delete', params: { id } })
```

#### 名片交换（`/hanzhong/card-exchange`）
```typescript
export const getCardExchangePage = (params) =>
  request.get({ url: '/hanzhong/card-exchange/page', params })
export const getCardExchange = (id: number) =>
  request.get({ url: '/hanzhong/card-exchange/get', params: { id } })
```

#### 课程收藏（`/hanzhong/course-favorite`）
```typescript
export const getCourseFavoritePage = (params) =>
  request.get({ url: '/hanzhong/course-favorite/page', params })
export const deleteCourseFavorite = (id: number) =>
  request.delete({ url: '/hanzhong/course-favorite/delete', params: { id } })
```

#### 用户档案（`/hanzhong/user-profile`）
```typescript
export const getUserProfilePage = (params) =>
  request.get({ url: '/hanzhong/user-profile/page', params })
export const getUserProfile = (id: number) =>
  request.get({ url: '/hanzhong/user-profile/get', params: { id } })
export const updateUserProfileStatus = (id: number, status: number) =>
  request.put({ url: '/hanzhong/user-profile/update-status', data: { id, status } })
```

#### 概览统计（`/hanzhong/overview`）
```typescript
export const getOverviewStats = () =>
  request.get({ url: '/hanzhong/overview/stats' })
// days: 7 | 14 | 30 | 90
export const getOverviewTrend = (days: number = 7) =>
  request.get({ url: '/hanzhong/overview/trend', params: { days } })
export const getUserActivity = (userId: number) =>
  request.get({ url: '/hanzhong/overview/user-activity', params: { userId } })
```

### 3.3 关键字典类型（下拉选项）

管理后台所有状态下拉均从后端字典获取，字典类型名称如下：

| 字段 | 字典类型 | 值范围 |
|------|---------|--------|
| 课程订单状态 | `hanzhong_course_order_status` | 0=待支付 1=已支付 2=已取消 3=已退款 |
| 职位申请状态 | `hanzhong_job_apply_status` | 0=已投递 1=查看简历 2=邀请面试 3=不合适 4=已录用 |
| 学习记录状态 | `hanzhong_study_record_status` | 0=学习中 1=已完成 |
| 消息类型 | `hanzhong_message_type` | 0=系统通知 1=申请状态更新 2=课程通知 3=名片通知 |
| 帖子状态 | `hanzhong_community_post_status` | 0=正常 1=已屏蔽 |
| Banner 跳转类型 | `hanzhong_banner_link_type` | 0=不跳转 1=跳转课程 2=跳转职位 3=自定义链接 |

---

## 四、小程序（yudao-mall-uniapp）实现规范

### 4.1 目录结构

```
sheep/
└── api/
    └── hanzhong/
        ├── home.js          # 首页聚合
        ├── course.js        # 课程
        ├── courseOrder.js   # 课程订单
        ├── studyRecord.js   # 学习记录
        ├── courseFavorite.js
        ├── job.js           # 职位
        ├── jobApply.js      # 职位申请
        ├── message.js       # 消息
        ├── communityPost.js # 社区帖子
        ├── communityPostComment.js
        ├── card.js          # 名片
        ├── cardExchange.js  # 名片交换
        ├── resume.js        # 简历
        ├── userProfile.js   # 用户档案
        ├── mine.js          # 我的聚合
        └── search.js        # 搜索

pages/
└── hanzhong/
    ├── home/
    │   └── index.vue
    ├── course/
    │   ├── list.vue
    │   ├── detail.vue
    │   └── study.vue
    ├── job/
    │   ├── list.vue
    │   └── detail.vue
    ├── community/
    │   ├── list.vue
    │   ├── detail.vue
    │   └── create.vue
    ├── mine/
    │   ├── index.vue
    │   ├── profile.vue
    │   ├── resume.vue
    │   ├── card.vue
    │   ├── job-apply.vue
    │   ├── course-order.vue
    │   ├── study-record.vue
    │   └── favorites.vue
    ├── message/
    │   └── list.vue
    ├── card/
    │   └── exchange.vue
    └── search/
        └── index.vue
```

### 4.2 App 端 API 接口清单

所有接口基础路径：`/hanzhong/app`

#### 首页（`/hanzhong/app/home`）
```javascript
// sheep/api/hanzhong/home.js
export const getHomeData = () => sheep.$api('get', '/hanzhong/app/home')
// 返回: { banners, categories, featuredCourses, hotCourses, featuredJobs, hotPosts }
```

#### 课程（`/hanzhong/app/course`）
```javascript
// sheep/api/hanzhong/course.js
export const getCoursePage = (params) => sheep.$api('get', '/hanzhong/app/course/page', params)
// params: { pageNo, pageSize, keyword?, categoryId?, sortBy? }

export const getCourseDetail = (id) => sheep.$api('get', '/hanzhong/app/course/get-detail', { id })
// 返回含 sections 章节列表和 totalDuration

export const hasPurchasedCourse = (courseId) =>
  sheep.$api('get', '/hanzhong/app/course/has-purchased', { courseId })
```

#### 课程订单（`/hanzhong/app/course-order`）
```javascript
// sheep/api/hanzhong/courseOrder.js
export const createCourseOrder = (courseId) =>
  sheep.$api('post', '/hanzhong/app/course-order/create', { courseId })

export const payCourseOrder = (id) =>
  sheep.$api('put', '/hanzhong/app/course-order/pay', { id })

export const cancelCourseOrder = (id) =>
  sheep.$api('put', '/hanzhong/app/course-order/cancel', { id })

export const getCourseOrderPage = (params) =>
  sheep.$api('get', '/hanzhong/app/course-order/page', params)

export const getCourseOrderByCourse = (courseId) =>
  sheep.$api('get', '/hanzhong/app/course-order/get-by-course', { courseId })
```

#### 学习记录（`/hanzhong/app/study-record`）
```javascript
// sheep/api/hanzhong/studyRecord.js
export const getStudyRecordPage = (params) =>
  sheep.$api('get', '/hanzhong/app/study-record/page', params)

export const updateStudyProgress = (data) =>
  sheep.$api('post', '/hanzhong/app/study-record/update-progress', data)
// data: { courseId, sectionId, progress }
// sectionId（可选）：当前正在学习的章节编号，用于视频断点续播

export const getStudyRecordByCourse = (courseId) =>
  sheep.$api('get', '/hanzhong/app/study-record/get-by-course', { courseId })
// 返回中包含 lastSectionId 字段，前端视频页可据此跳转到上次停留的章节

export const deleteStudyRecord = (id) =>
  sheep.$api('delete', '/hanzhong/app/study-record/delete', { id })
```

#### 课程收藏（`/hanzhong/app/course-favorite`）
```javascript
// sheep/api/hanzhong/courseFavorite.js
export const toggleCourseFavorite = (courseId) =>
  sheep.$api('post', '/hanzhong/app/course-favorite/toggle', { courseId })

export const getCourseFavoritePage = (params) =>
  sheep.$api('get', '/hanzhong/app/course-favorite/page', params)
```

#### 职位（`/hanzhong/app/job`）
```javascript
// sheep/api/hanzhong/job.js
export const getJobPage = (params) => sheep.$api('get', '/hanzhong/app/job/page', params)
// params: { pageNo, pageSize, keyword?, category?, location?, education?, experience? }

export const getJobDetail = (id) => sheep.$api('get', '/hanzhong/app/job/get', { id })

export const hasAppliedJob = (jobId) =>
  sheep.$api('get', '/hanzhong/app/job/has-applied', { jobId })
```

#### 职位申请（`/hanzhong/app/job-apply`）
```javascript
// sheep/api/hanzhong/jobApply.js
export const createJobApply = (jobId) =>
  sheep.$api('post', '/hanzhong/app/job-apply/create', { jobId })

export const withdrawJobApply = (id) =>
  sheep.$api('delete', '/hanzhong/app/job-apply/withdraw', { id })

export const getJobApplyPage = (params) =>
  sheep.$api('get', '/hanzhong/app/job-apply/page', params)

export const getJobApplyByJob = (jobId) =>
  sheep.$api('get', '/hanzhong/app/job-apply/get-by-job', { jobId })
```

#### 消息（`/hanzhong/app/message`）
```javascript
// sheep/api/hanzhong/message.js
export const getMessagePage = (params) =>
  sheep.$api('get', '/hanzhong/app/message/page', params)

export const getUnreadCount = () =>
  sheep.$api('get', '/hanzhong/app/message/unread-count')

export const readMessage = (id) =>
  sheep.$api('put', '/hanzhong/app/message/read', { id })

export const readAllMessages = () =>
  sheep.$api('put', '/hanzhong/app/message/read-all')

export const deleteMessage = (id) =>
  sheep.$api('delete', '/hanzhong/app/message/delete', { id })
```

#### 社区帖子（`/hanzhong/app/community-post`）
```javascript
// sheep/api/hanzhong/communityPost.js
export const getCommunityPostPage = (params) =>
  sheep.$api('get', '/hanzhong/app/community-post/page', params)

export const getCommunityPost = (id) =>
  sheep.$api('get', '/hanzhong/app/community-post/get', { id })

export const createCommunityPost = (data) =>
  sheep.$api('post', '/hanzhong/app/community-post/create', data)
// data: { title, content, coverUrl?, category? }

export const updateCommunityPost = (data) =>
  sheep.$api('put', '/hanzhong/app/community-post/update', data)

export const togglePostLike = (id) =>
  sheep.$api('post', '/hanzhong/app/community-post/like', { id })

export const getMyPostPage = (params) =>
  sheep.$api('get', '/hanzhong/app/community-post/my-page', params)

export const getPostPageByUser = (userId, params) =>
  sheep.$api('get', '/hanzhong/app/community-post/page-by-user', { userId, ...params })
// 公开接口，用于他人主页展示其发布的帖子

export const deleteCommunityPost = (id) =>
  sheep.$api('delete', '/hanzhong/app/community-post/delete', { id })
```

#### 帖子评论（`/hanzhong/app/community-post-comment`）
```javascript
export const getCommentPage = (params) =>
  sheep.$api('get', '/hanzhong/app/community-post-comment/page', params)
export const createComment = (data) =>
  sheep.$api('post', '/hanzhong/app/community-post-comment/create', data)
// data: { postId, parentId(0=顶级), content }
export const deleteComment = (id) =>
  sheep.$api('delete', '/hanzhong/app/community-post-comment/delete', { id })
export const getMyCommentPage = (params) =>
  sheep.$api('get', '/hanzhong/app/community-post-comment/my-page', params)
// 获取我发布的所有评论，用于"我的评论历史"页
```

#### 名片（`/hanzhong/app/card`）
```javascript
// sheep/api/hanzhong/card.js
export const getMyCard = () => sheep.$api('get', '/hanzhong/app/card/get')
export const saveMyCard = (data) =>
  sheep.$api('post', '/hanzhong/app/card/create-or-update', data)
export const getCardByUserId = (userId) =>
  sheep.$api('get', '/hanzhong/app/card/get-by-user-id', { userId })
```

#### 名片交换（`/hanzhong/app/card-exchange`）
```javascript
// sheep/api/hanzhong/cardExchange.js
export const createCardExchange = (targetUserId) =>
  sheep.$api('post', '/hanzhong/app/card-exchange/create', { targetUserId })

export const getCardExchangePage = (params) =>
  sheep.$api('get', '/hanzhong/app/card-exchange/page', params)
```

#### 简历（`/hanzhong/app/resume`）
```javascript
// sheep/api/hanzhong/resume.js
export const getMyResume = () => sheep.$api('get', '/hanzhong/app/resume/get')
export const saveMyResume = (data) =>
  sheep.$api('post', '/hanzhong/app/resume/create-or-update', data)
```

#### 用户档案（`/hanzhong/app/user-profile`）
```javascript
// sheep/api/hanzhong/userProfile.js
export const getMyProfile = () =>
  sheep.$api('get', '/hanzhong/app/user-profile/get')
export const saveMyProfile = (data) =>
  sheep.$api('put', '/hanzhong/app/user-profile/create-or-update', data)
```

#### 我的聚合（`/hanzhong/app/mine`）
```javascript
// sheep/api/hanzhong/mine.js
export const getMineStats = () => sheep.$api('get', '/hanzhong/app/mine/stats')
// 返回: { courseOrderCount, studyRecordCount, jobApplyCount, cardExchangeCount, courseFavoriteCount, unreadMessageCount }

export const getMineProfile = () => sheep.$api('get', '/hanzhong/app/mine/profile')
// 返回: { profile, card, resume, stats }

export const getMineRecentActivity = () =>
  sheep.$api('get', '/hanzhong/app/mine/recent-activity')

export const getMyPostPage = (params) =>
  sheep.$api('get', '/hanzhong/app/mine/my-posts', params)
```

#### 搜索（`/hanzhong/app/search`）
```javascript
// sheep/api/hanzhong/search.js
export const search = (keyword, type = 'all', limit = 5) =>
  sheep.$api('get', '/hanzhong/app/search', { keyword, type, limit })
// type: 'all' | 'course' | 'job' | 'post'
// 返回: { courses, jobs, posts, totalCourses, totalJobs, totalPosts }
```

### 4.3 pages.json 需新增的路由

```json
{
  "pages": [
    { "path": "pages/hanzhong/home/index", "style": { "navigationBarTitleText": "汉中职业平台" } },
    { "path": "pages/hanzhong/course/list", "style": { "navigationBarTitleText": "课程列表" } },
    { "path": "pages/hanzhong/course/detail", "style": { "navigationBarTitleText": "课程详情" } },
    { "path": "pages/hanzhong/course/study", "style": { "navigationBarTitleText": "课程学习" } },
    { "path": "pages/hanzhong/job/list", "style": { "navigationBarTitleText": "职位列表" } },
    { "path": "pages/hanzhong/job/detail", "style": { "navigationBarTitleText": "职位详情" } },
    { "path": "pages/hanzhong/community/list", "style": { "navigationBarTitleText": "职场社区" } },
    { "path": "pages/hanzhong/community/detail", "style": { "navigationBarTitleText": "帖子详情" } },
    { "path": "pages/hanzhong/community/create", "style": { "navigationBarTitleText": "发帖" } },
    { "path": "pages/hanzhong/mine/index", "style": { "navigationBarTitleText": "我的" } },
    { "path": "pages/hanzhong/mine/profile", "style": { "navigationBarTitleText": "我的档案" } },
    { "path": "pages/hanzhong/mine/resume", "style": { "navigationBarTitleText": "我的简历" } },
    { "path": "pages/hanzhong/mine/card", "style": { "navigationBarTitleText": "我的名片" } },
    { "path": "pages/hanzhong/mine/job-apply", "style": { "navigationBarTitleText": "求职记录" } },
    { "path": "pages/hanzhong/mine/course-order", "style": { "navigationBarTitleText": "课程订单" } },
    { "path": "pages/hanzhong/mine/study-record", "style": { "navigationBarTitleText": "学习记录" } },
    { "path": "pages/hanzhong/mine/favorites", "style": { "navigationBarTitleText": "课程收藏" } },
    { "path": "pages/hanzhong/message/list", "style": { "navigationBarTitleText": "消息通知" } },
    { "path": "pages/hanzhong/card/exchange", "style": { "navigationBarTitleText": "名片交换" } },
    { "path": "pages/hanzhong/search/index", "style": { "navigationBarTitleText": "搜索" } }
  ]
}
```

---

## 五、核心业务链路闭环验证清单

实现完成后，按以下流程验证整条链路：

### 链路 1：课程报名 → 学习 → 完课
1. 小程序首页 → 课程列表 → 课程详情
2. 点击"立即报名" → 创建订单（`POST /course-order/create`）
3. 订单详情页 → "去支付"（`PUT /course-order/pay`）
4. 支付后学习记录自动创建，进入课程学习页
5. 更新学习进度（`POST /study-record/update-progress`）
6. 管理后台 → 课程订单管理 → 可见新订单

### 链路 2：职位投递 → HR 审核 → 面试通知
1. 小程序职位列表 → 职位详情
2. 点击"立即投递" → 创建申请（`POST /job-apply/create`），自动携带简历
3. 申请成功 → 收到系统消息（申请已成功）
4. 管理后台 → 职位申请管理 → 审核：邀请面试
5. 小程序消息列表 → 收到"收到面试邀请"消息
6. 我的求职记录 → 状态变为"邀请面试"

### 链路 3：用户名片交换
1. 小程序浏览他人名片（通过社区/职位详情）
2. 点击"交换名片" → `POST /card-exchange/create`
3. 对方收到系统消息通知
4. 管理后台 → 名片交换管理 → 可见记录

### 链路 4：管理后台概览统计
1. 登录管理后台 → 汉中管理 → 概览统计
2. 显示总用户数、课程数、订单数、申请数等统计数据
3. 趋势图（近 7/14/30 天趋势）

---

## 六、快速启动检查清单

在正式开发前确认以下环境准备：

```bash
# 1. 执行数据库初始化（包含示例数据）
mysql -u root -p your_database < sql/mysql/hanzhong.sql

# 2. 验证后端正常启动
mvn spring-boot:run -pl yudao-server

# 3. 验证关键接口（无需认证）
curl http://localhost:48080/hanzhong/app/home
curl http://localhost:48080/hanzhong/app/course/page

# 4. 验证菜单已创建（需登录管理后台）
# 汉中管理 → 应出现完整菜单树（17 个子菜单）
```

---

## 七、已知可能的坑 & 注意事项

| 编号 | 问题 | 说明 |
|------|------|------|
| 1 | 管理后台路由懒加载 | Vue3 路由配置要确保 `component` 字段路径与 SQL 菜单中的 `component` 一致 |
| 2 | App 接口鉴权 | App 端接口除首页/课程列表/职位列表/搜索外，其余需登录（`Authorization: Bearer <token>`） |
| 3 | 课程订单自动支付 | 免费课程（`price == 0`）创建订单后自动标记为已支付并初始化学习记录，无需再调 `pay` 接口 |
| 4 | 职位申请自动携带简历 | 投递时如用户已有简历，`resume_id` 自动从数据库填入，无需前端传递 |
| 5 | 名片交换防自交换 | 向自己发起名片交换会返回 `1_020_013_001` 错误 |
| 6 | 帖子点赞幂等 | 重复点赞为取消点赞（toggle），返回当前最新点赞状态 |
| 7 | 批量消息标为已读 | `PUT /message/read-all` 无参数，标记当前用户所有未读消息 |
| 8 | 重复下单防护 | 同课程已有"待支付"或"已支付"订单时，创建订单返回 `1_020_010_001` 错误 |
| 9 | 重复投递防护 | 已有未撤销申请时再投递同职位，返回 `1_020_012_001` 错误 |
| 10 | 章节断点续播 | 调用 `POST /study-record/update-progress` 时传入 `sectionId`，下次进入学习页从 `lastSectionId` 跳转章节 |
| 11 | 学习记录 lastSectionId | `GET /study-record/get-by-course` 返回 `lastSectionId`，视频播放器据此定位到上次停留章节（可为 null） |
| 12 | 他人帖子查看 | `GET /community-post/page-by-user?userId=X` 公开接口，用于他人主页展示其帖子列表 |

---

*文档由 Copilot Agent 自动生成，基于 `ruoyi-vue-pro` 仓库 `yudao-module-hanzhong` 实际代码状态。*

---

## 八、本轮新增功能说明（2026-04-06 更新）

本轮在现有基础上增加了以下功能，前端集成时需同步对接：

### 8.1 职位收藏（Job Collect）— 新模块

新增 `hanzhong_job_collect` 表和完整的收藏功能，支持用户收藏/取消收藏职位：

| 端点 | 说明 |
|------|------|
| `POST /hanzhong/app/job-collect/toggle?jobId=X` | 切换收藏状态（返回 true=已收藏，false=已取消） |
| `GET /hanzhong/app/job-collect/is-collected?jobId=X` | 判断是否已收藏 |
| `GET /hanzhong/app/job-collect/my-page?pageNo=1&pageSize=10` | 我的收藏列表（分页） |
| `GET /hanzhong/app/job-collect/collected-job-ids` | 获取我收藏的所有职位ID（用于列表页批量标记收藏状态） |
| `DELETE /hanzhong/app/job-collect/delete?id=X` | 取消收藏 |
| `GET /hanzhong/job-collect/page` (Admin) | 管理员查看收藏记录 |

> 职位详情接口 `GET /hanzhong/app/job/get?id=X` 新增 `isCollected` 字段，登录用户自动返回收藏状态。

### 8.2 首页新增「最新帖子」字段

`GET /hanzhong/app/home` 返回的 `AppHomeRespVO` 新增：

- `latestPosts`: 最新 6 条社区帖子（按发布时间降序，与 `hotPosts` 热门帖子分离）

### 8.3 搜索页热门关键词接口

| 端点 | 说明 |
|------|------|
| `GET /hanzhong/app/search/hot-keywords` | 返回预置热门搜索词列表，用于搜索页推荐词展示 |

### 8.4 课程订单退款接口（管理员）

| 端点 | 说明 |
|------|------|
| `PUT /hanzhong/course-order/refund?id=X` | 将课程订单状态更新为「已退款」（需 `hanzhong:course-order:update` 权限） |

### 8.5 课程章节排序接口

| 端点 | 说明 |
|------|------|
| `PUT /hanzhong/course-section/update-sort` | 批量更新章节排序，Body: `{ "chapterId1": sort1, "chapterId2": sort2, ... }` |

### 8.6 统计数据更新

- 「我的统计」(`GET /hanzhong/app/mine/stats`) 新增 `totalJobCollects` 字段
- 管理后台概览统计 (`GET /hanzhong/overview/stats`) 新增 `totalJobCollects` 字段
- 用户活动统计 (`GET /hanzhong/overview/user-activity`) 新增 `totalJobCollects` 字段

### 8.7 菜单更新

新增菜单权限（ID 5168-5170），数据库执行 `sql/mysql/hanzhong.sql` 可自动插入：

| ID | 名称 | 权限标识 |
|----|------|---------|
| 5168 | 职位收藏管理 | 路由页面 |
| 5169 | 职位收藏查询 | `hanzhong:job-collect:query` |
| 5170 | 职位收藏删除 | `hanzhong:job-collect:delete` |


---

## 五、本轮新增功能（controller-service-vo-dto 分支）

本轮补充了以下 3 个重要模块：

### 1. 课程评分系统（courserating）

| 端 | 接口 | 说明 |
|----|------|------|
| App | `POST /hanzhong/app/course-rating/create-or-update` | 创建或更新课程评分（1-5 星 + 评价内容；付费课程需先购买）|
| App | `GET /hanzhong/app/course-rating/my-rating?courseId=X` | 获取当前用户对指定课程的评分 |
| App | `GET /hanzhong/app/course-rating/page?courseId=X` | 获取课程评分分页列表（公开可见）|
| 管理 | `GET /hanzhong/course-rating/page` | 管理后台课程评分列表 |
| 管理 | `DELETE /hanzhong/course-rating/delete?id=X` | 删除课程评分 |

**影响的现有接口：**
- `GET /hanzhong/app/course/get-detail` 响应增加 `averageRating`（平均评分）、`ratingCount`（评分人数）、`myRating`（当前用户评分）
- `AppCourseRespVO` 增加 `averageRating`、`ratingCount` 字段（课程列表页可展示星级）

**新增 SQL：** `hanzhong_course_rating` 表，菜单权限 ID 5171-5173

---

### 2. 热门搜索关键词管理（hotkeyword）

| 端 | 接口 | 说明 |
|----|------|------|
| App | `GET /hanzhong/app/search/hot-keywords` | 获取热门搜索词（**已更改为从数据库加载**，无数据时回退为默认预置词）|
| 管理 | `POST /hanzhong/hot-keyword/create` | 新增热门关键词 |
| 管理 | `PUT /hanzhong/hot-keyword/update` | 编辑热门关键词 |
| 管理 | `DELETE /hanzhong/hot-keyword/delete?id=X` | 删除热门关键词 |
| 管理 | `GET /hanzhong/hot-keyword/page` | 热门关键词分页列表 |

**新增 SQL：** `hanzhong_hot_keyword` 表（含 10 条预置数据），菜单权限 ID 5174-5178

---

### 3. 用户退款申请流程

**订单状态新增：** `4 = 退款申请中`（用户申请，等待管理员处理）

| 端 | 接口 | 说明 |
|----|------|------|
| App | `PUT /hanzhong/app/course-order/request-refund?id=X` | **新增**：用户对已支付订单申请退款（状态 1→4）|
| 管理 | `PUT /hanzhong/course-order/refund?id=X` | 管理员处理退款（状态 4→3）|

**完整订单状态流转：**
```
0-待支付 → 1-已支付 → 4-退款申请中 → 3-已退款（管理员处理）
0-待支付 → 2-已取消（用户取消）
```

---

### 4. 其他增强

- `AppHomeRespVO` 增加 `unreadMessageCount`（登录用户的未读消息数，用于首页角标）
- `OverviewStatsRespVO` 增加 `totalCourseRatings`、`avgCourseRating`、`refundRequestedOrders` 字段
- 错误码新增：`COURSE_RATING_NOT_EXISTS`、`COURSE_NOT_PURCHASED_FOR_RATING`、`HOT_KEYWORD_NOT_EXISTS`、`HOT_KEYWORD_DUPLICATE`、`COURSE_ORDER_CANNOT_REFUND`、`COURSE_ORDER_ALREADY_REFUND_REQUESTED`
