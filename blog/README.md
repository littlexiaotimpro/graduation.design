### Concept

前后端分离式搭建博客。

后端：
- 管理系统  
页面结构、用户、及博客内容统一管理平台
- 客户端访问  
提供客户端访问API
- ELK日志监控平台  
收集系统运行时的日志数据

前端：
- React + antd

### 重构（项目结构 & 实现逻辑）

- 项目多模块化，简化冗杂的功能点
- 去除OSS存储功能，文件上传存储地址为本地服务器
- 去除原项目中的日志持久化相关数据：日志表、entity、dto等
- 采用ELK的方式收集系统日志，并统一进行监控和管理
- ...