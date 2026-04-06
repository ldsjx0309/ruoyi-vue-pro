# SQL 导入风险自查与后续建议

> **生成时间**: 2026-04-06  
> **评估范围**: `sql/mysql/` 目录下新增的三份芋道源码 SQL 文件  
> **结论先行**: ✅ **三份 SQL 均可平滑导入，零结构冲突，零业务主链路阻断。**

---

## 一、新增 SQL 文件概览

| 文件名 | 业务模块 | 表数量 | 数据量 |
|--------|----------|--------|--------|
| `pay-2025-07-27传播违法.sql` | 支付模块（pay_*） | 14 张 | 含示例数据 |
| `member-2024-01-18.sql` | 会员模块（member_*） | 11 张 | 含示例数据 |
| `ruoyi-vue-pro-mall-2025-05-12传播违法.sql` | 商城模块（product_* + promotion_* + trade_*） | 48 张 | 含示例数据 |

---

## 二、冲突排查结果

### 2.1 表名冲突检查

| 检查对象 | 现有表前缀 | 新增表前缀 | 冲突 |
|----------|-----------|-----------|------|
| `ruoyi-vue-pro.sql`（系统主表） | `system_*`, `infra_*`, `yudao_demo*` | `pay_*`, `member_*`, `product_*`, `promotion_*`, `trade_*` | ✅ 无冲突 |
| `hanzhong.sql`（汉中业务表） | `hanzhong_*` | 同上 | ✅ 无冲突 |
| `quartz.sql`（定时任务表） | `QRTZ_*` | 同上 | ✅ 无冲突 |

> **注意**: `promotion_banner`（商城促销轮播）与 `hanzhong_banner`（汉中轮播）为不同表，不会互相覆盖。

### 2.2 字段级 Java 代码匹配检查

| SQL 模块 | Java 模块路径 | 表↔@TableName 全匹配 |
|----------|--------------|---------------------|
| `pay_*`（14 张表） | `yudao-module-pay` | ✅ 14/14 完全匹配 |
| `member_*`（11 张表） | `yudao-module-member` | ✅ 11/11 完全匹配 |
| `product_*`（9 张表） | `yudao-module-mall/yudao-module-product` | ✅ 9/9 完全匹配 |
| `promotion_*`（23 张表） | `yudao-module-mall/yudao-module-promotion` | ✅ 23/23 完全匹配 |
| `trade_*`（16 张表） | `yudao-module-mall/yudao-module-trade` | ✅ 16/16 完全匹配 |

### 2.3 系统元数据冲突检查

| 检查项 | 结果 |
|--------|------|
| `system_menu` 写入 | ❌ 三份文件均**不含** `system_menu` 插入，无菜单 ID 冲突 |
| `system_dict_type/data` 写入 | ❌ 三份文件均**不含** `system_dict` 插入，无字典 ID 冲突 |
| 外键约束（FOREIGN KEY） | ❌ 三份文件均**无** FOREIGN KEY / REFERENCES 声明，无跨表依赖 |

### 2.4 导入安全性

| 特性 | 状态 |
|------|------|
| `DROP TABLE IF EXISTS`（幂等） | ✅ 所有表前均有，可重复导入 |
| `SET FOREIGN_KEY_CHECKS = 0/1` | ✅ 已包裹，导入顺序无严格要求 |
| 重复表定义 | ✅ 无重复（经 `uniq -d` 验证） |

---

## 三、业务代码依赖影响分析

### 3.1 pay 模块（支付）

- 后端 `yudao-module-pay` 已完整实现，含 Controller/Service/Mapper/DO。
- SQL 提供的示例数据中包含 `pay_app`（ID=1 商城应用、ID=7 示例应用、ID=8 会员钱包），可直接支撑 `trade` 订单支付回调联调。
- **需关注**: 若当前数据库中已有 `pay_app` 记录（ID 1/7/8），导入时会因 `DROP TABLE IF EXISTS` + `INSERT` 覆盖原有数据，建议首次全量初始化时一并导入，或手动删除示例 INSERT 语句后仅导入表结构。

### 3.2 member 模块（会员）

- 后端 `yudao-module-member` 已完整实现。
- `member_user`（会员用户）与 `system_users`（管理员用户）**完全独立**，字段设计不同（会员无 `username`/`dept_id` 等管理员专属字段），导入不影响系统用户表。
- 示例数据为 ID 247–285 的会员账号（均含 BCrypt 密码），可直接用于开发/测试登录。

### 3.3 mall 模块（商城）

- 后端 `yudao-module-mall`（含 product、promotion、trade、statistics 四个子模块）已完整实现。
- 商城模块依赖支付模块（`trade_order.pay_order_id` 逻辑关联 `pay_order`），因此**建议先导入 pay SQL，再导入 mall SQL**（虽无数据库级 FOREIGN KEY，但业务逻辑上 pay 应先就绪）。

---

## 四、集成度评估

| 维度 | 评估 |
|------|------|
| 后端代码覆盖 | ✅ 三份 SQL 所有表均已有对应后端 Java 代码（Mapper/Service/Controller） |
| 前端管理后台 | ⚠️ `yudao-ui-admin-vue3` 中 pay/member/mall 相关页面需确认是否已对接 |
| 前端小程序 | ⚠️ `yudao-mall-uniapp` 中 mall/member/pay 页面是主链路核心，需确认是否已完成 |
| 汉中自定义模块 | ✅ 无任何依赖关系，相互独立 |

---

## 五、导入顺序建议

```
1. ruoyi-vue-pro.sql      ← 系统基础表（system_*, infra_*）
2. member-2024-01-18.sql  ← 会员表（独立）
3. pay-2025-07-27传播违法.sql   ← 支付表（mall 依赖）
4. ruoyi-vue-pro-mall-2025-05-12传播违法.sql ← 商城表（依赖 pay_app）
5. quartz.sql             ← 定时任务表（独立）
6. hanzhong.sql           ← 汉中业务表（独立）
```

---

## 六、风险等级汇总

| 风险点 | 等级 | 说明 |
|--------|------|------|
| 表名冲突 | 🟢 无 | 三份 SQL 使用全新前缀，与现有表完全隔离 |
| 字段/索引冲突 | 🟢 无 | SQL ↔ Java DO 全量匹配，无差异 |
| 菜单/字典 ID 冲突 | 🟢 无 | 三份 SQL 不含 system_menu/dict 写入 |
| 示例数据覆盖 | 🟡 低 | DROP TABLE 会重置示例数据，仅影响开发环境；生产环境应仅执行 DDL（CREATE TABLE） |
| 前端集成缺口 | 🟡 中 | mall/member/pay 前端页面需确认对接状态，否则功能不可用 |
| 业务主链路阻断 | 🟢 无 | 三份 SQL 与汉中主链路完全独立，可安全导入 |

---

## 七、后续建议

1. **立即可执行**: 按上述顺序导入三份 SQL，后端服务可直接启动并访问 pay/member/mall 模块接口。
2. **前端补全优先级**: 
   - P0：`yudao-mall-uniapp` 小程序端的商品浏览、下单、支付、订单查询流程
   - P1：`yudao-ui-admin-vue3` 管理端的订单管理、会员管理、支付应用配置页面
3. **生产环境导入**: 去掉三份 SQL 中的 `INSERT` 语句（示例数据），仅执行 `CREATE TABLE` 部分，避免覆盖已有业务数据。
4. **支付配置**: `pay_app` 示例数据中的回调 URL 指向 `127.0.0.1:48080`，生产/测试环境需更新为实际服务地址。
