# cockroach 爬虫 更新日志

## 2018-01-19

* 修改了 response 的 close 方法到 finally 方法块中
* 增加了队列过滤器

## 2017-12-24
* 增加了 TaskResponse 中指定编码格式的接口
* 去掉了 taskqueue 中的单例
* 删除了一些无用类
* 增加了 cookie 生成器与 header 生成器

## 2017-09-13

* 增加了 log4j 为默认日志组件
* 修改了一些架构上的问题