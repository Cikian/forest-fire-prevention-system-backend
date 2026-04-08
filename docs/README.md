# 森林防火系统后端 (Forest Fire Prevention System Backend)

> 基于 JeecgBoot 低代码平台开发的森林防火智能监控系统后端服务。

## 📖 项目概述

本项目是一套面向森林防火场景的智能监控系统后端，核心能力包括：**无人机设备管理**、**无人机机场/机库管理**、**飞行任务调度**、**告警监测**、**航线规划与管理**。系统通过集成大疆无人机云 SDK，实现了对无人机的远程控制与数据采集，支撑森林防火巡检的自动化与智能化。

## 🏗️ 技术架构

| 层级 | 技术选型 |
|------|---------|
| **基础框架** | JeecgBoot 3.4.3（基于 Spring Boot 2.6.6） |
| **Java 版本** | JDK 11 |
| **持久层** | MyBatis-Plus 3.5.1 + Dynamic DataSource 3.2.0 |
| **微服务** | Spring Cloud 2021.0.3 + Spring Cloud Alibaba 2021.0.1.0 |
| **服务注册/配置中心** | Alibaba Nacos 2.0.4 |
| **数据库** | MySQL 8.0 + Redis 5.0 |
| **定时任务** | XXL-Job 2.2.0 |
| **API 文档** | Knife4j（Swagger）3.0.3 |
| **低代码能力** | JeecgBoot 代码生成器 + Online 表单 |
| **第三方集成** | 大疆无人机云 SDK（DJI Cloud SDK） |
| **文件存储** | MinIO + 阿里云 OSS + 七牛云 |
| **消息推送** | RabbitMQ + MQTT |
| **安全框架** | Shiro 1.10.0 + JWT |
| **其他** | Lombok、FastJSON、Hutool、AutoPOI |

## 📁 项目结构

```
forest-fire-prevention-system-backend/
├── pom.xml                          # 父项目 POM（统一版本管理）
├── docker-compose.yml               # Docker 快速启动配置
│
├── jeecg-boot-base-core/            # JeecgBoot 核心模块（公共基类）
├── jeecg-module-demo/               # JeecgBoot 示例模块
├── jeecg-module-system/             # JeecgBoot 系统模块（用户/权限/字典等）
├── jeecg-server-cloud/              # JeecgBoot 微服务模块（Nacos/Sentinel 等）
│
├── stargis-module-business/         # 🔥 森林防火核心业务模块
│   └── src/main/java/org/jeecg/modules/slfh/
│       ├── equipment/               # 设备管理
│       │   ├── drone/              # 无人机设备
│       │   ├── airport/            # 无人机机场（机库）
│       │   └── alert/             # 告警设备
│       ├── fxgl/                  # 飞行管理
│       │   ├── fly/               # 飞行记录
│       │   └── flyairportrecord/  # 飞行机场记录
│       ├── route/                 # 航线管理
│       └── task/                  # 任务管理
│           ├── dronetask/          # 无人机任务
│           ├── dronetasklog/       # 任务执行日志
│           ├── donetaskstatus/      # 任务完成状态
│           ├── droneroute/         # 无人机航线
│           └── taskrouterel/       # 任务-航线关联
│
├── dji-cloud-sdk/                  # 大疆无人机云 SDK 封装模块
├── dji-sample/                     # 大疆 SDK 示例模块
└── db/                             # MySQL 数据库初始化脚本
```

> **命名说明：** `slfh` 为"森林防火"拼音首字母缩写，所有业务实体、表名均以此为前缀（如表 `slfh_equipment_drone`）。

## 🔑 核心业务模块

### 1. 设备管理（equipment）

| 子模块 | 说明 | 典型数据表 |
|--------|------|-----------|
| **无人机（drone）** | 管理巡检无人机的基本信息、状态、归属机场等 | `slfh_equipment_drone` |
| **机场（airport）** | 管理无人机机库/停机坪的位置、设备状态 | `slfh_equipment_airport` |
| **告警（alert）** | 管理烟感/热成像等告警设备及告警记录 | `slfh_equipment_alert` |

### 2. 飞行管理（fxgl）

| 子模块 | 说明 | 典型数据表 |
|--------|------|-----------|
| **飞行记录（fly）** | 记录每次无人机飞行的起止时间、航线、高度等信息 | `slfh_fly_list` |
| **飞行机场记录（flyairportrecord）** | 关联飞行与起降机场的记录 | `slfh_fly_airport_record` |

### 3. 航线管理（route）

管理预定义的巡检航线，包括航线坐标点、航点动作等，支持多条航线模板化管理。

### 4. 任务管理（task）

| 子模块 | 说明 |
|--------|------|
| **无人机任务（dronetask）** | 创建、调度无人机巡检任务，支持多种任务类型 |
| **任务日志（dronetasklog）** | 记录任务执行过程中的关键事件 |
| **任务状态（donetaskstatus）** | 跟踪任务完成状态 |
| **无人机航线（droneroute）** | 管理无人机与航线的绑定关系 |
| **任务-航线关联（taskrouterel）** | 关联任务与其执行的航线 |

## 🔌 第三方集成

### 大疆无人机云 SDK（DJI Cloud SDK）

- **模块路径：** `dji-cloud-sdk/`
- **用途：** 与大疆无人机地面站通信，实现航线订阅、直播推流、设备状态获取等能力
- **参考实现：** `dji-sample/`

典型集成场景：
- 订阅无人机实时位置
- 下发航线飞行指令
- 获取无人机图传/视频流
- 接收飞行任务执行结果回调

## 🚀 本地运行

### 环境依赖

- JDK 11
- Maven 3.6+
- MySQL 8.0
- Redis 5.0+
- Nacos 2.0.4+（微服务模式需要）

### 快速启动（Docker）

```bash
# 启动 MySQL + Redis + 应用
docker-compose up -d

# 访问应用
# http://localhost:8080
```

### IDE 启动（单体模式）

1. 导入 IDEA/Eclipse，确保 Maven 构建成功
2. 初始化数据库：执行 `db/` 下的 SQL 脚本
3. 修改 `application.yml` 中的数据库/Redis 连接配置
4. 启动 `stargis-module-business` 中的 Spring Boot 主类

### 微服务模式启动

```bash
# 激活 SpringCloud profile
mvn clean package -Pspring-cloud -Pdev

# 需要先启动 Nacos
docker run --name nacos -p 8848:8848 --env MODE=standalone -d nacos/nacos-server:v2.0.4
```

## 📮 API 文档

启动后在浏览器访问 Knife4j 文档（需启用 `knife4j` 依赖）：

```
http://localhost:8080/doc.html
```

或 Swagger 标准文档：

```
http://localhost:8080/swagger-ui/index.html
```

## 📌 主要配置项

> 配置文件路径（单体模式）：`stargis-module-business/src/main/resources/application.yml`

| 配置项 | 说明 |
|--------|------|
| `spring.datasource` | 数据库连接（MySQL） |
| `spring.redis` | Redis 连接 |
| `nacos.config.server-addr` | Nacos 地址（微服务模式） |
| `dji.cloud.sdk` | 大疆云 SDK 相关配置 |
| `file.upload.path` | 文件上传路径 |
| `xxl.job` | 定时任务 XXL-Job 配置 |

## 📄 许可证

本项目基于 [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) 开源，同时继承 JeecgBoot 许可证条款。

---

*项目作者：Cikian | 2026*
