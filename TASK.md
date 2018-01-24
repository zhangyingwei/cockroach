# cockroach 爬虫

* httpclient 设计的还是不够优雅  √
* httperrorhandler 可以考虑增加代理模式使用 exception 的方式，统一管理起来 √

接下来

* 替换打印日志的方式 改用 log4j 或者 logback √
* 增加login方法，获取登录 cookie (考虑要不要做)
* task 优先级 √
* 代理IP这里需要抽象一个接口出来，可以实现实时获取
* queue 持久化
* 支持 POST 请求
* 添加 watcher 支持监控