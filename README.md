## Concept

### 二方包

模块|说明|功能
---|---|---
blog-common|公共模块|定义项目所需的所有实体及一些工具类
blog-logging|日志模块|提供统一默认的日志配置
blog-swagger|swagger在线api模块|提供在线的api文档
blog-cloud-openfeign|rpc客户端|用于服务之间调用

### 服务端

模块|说明|功能
---|---|---
blog-server|基础管理服务端|提供基本的业务功能，如文章编辑等
blog-server-consumer|基础客户服务端|提供用户的阅读评论等
blog-server-file|文件管理服务端|提供文件的上传下载
blog-server-sso|sso登录验证及授权|做系统各个服务访问权限的统一认证