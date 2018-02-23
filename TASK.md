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
* 失败重试还是太low
* httpclient 这里需要重构

---

新年新气象，所有的工作重新计算吧

* httpclient 感觉还是需要从新搞一下 重新搞了一下，先这样吧 √
* 页面渲染这里迫切需要
* 正则匹配这里，要不要到底
* watcher监控需要从长计议
* 代理IP这里需要抽出来做成服务吧

