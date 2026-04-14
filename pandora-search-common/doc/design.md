# 模块结构
## common模块
- 不能独立运行和打包
- 定制搜索框架
- 记录搜索话单 

## 业务模块
业务模块依赖于common模块

- 启动类
- 数据库的模型
- 配置搜索话单路径



# 常见命令
## 自动生成数据库语句


#部署
## 方式一 以Jar包的方式运行webapp工程
search
   search-1.0.0.jar
   conf
        application.properties
        application-prod.properties
        logback.xml

java -jar search-1.0.0.jar  --logging.config=/Volumes/extend/angel_workspace/composite_hot_server/target/conf/logback.xml

spring boot的Jar包默认在jar的当前目录下面的config子目录查找properties文件
 
# 方式二      


# 业务模式开发方式
## 增加接口请求类 controller
@see com.migu.pandora.search.www.controller.SearchController

## 增加请求对象 SearchReq的实现类
  1. 采用注解对字段进行校验
  2. 实现getSearchWords方法,获取用户输入的搜索词
  
@see com.migu.uss.search.bean.WWWSearchReq
   
## 增加搜索响应对象 SearchDataResp 的实现类
  1. 实现getSearchRecordsForLog方法,获取搜索响应的日志记录内容
  
@see com.migu.uss.search.bean.ResponseData  

## 增加搜索业务处理对象 ISearchService/DefaultSearchService的实现类
@see com.migu.pandora.search.www.service.WWWSearchService

主要实现逻辑:
1 封装solr查询语句对象
2 定制索引对应的结果对象 采用json注解


# 高亮
## 方案一 采用 FieldAnalysisRequest 方式获取分词,客户拿到给分词后,可以遍历展示
缺点: 结果中含有输入的某一个词,但是不属于分词,则无法高亮

服务端处理基类 FieldAnalysisRequestHandler

## 方案二 服务端高亮

## 方案三 返回输入的每一个词


# 单元测试
## 基类
FrameworkApplicationTest.java

SpringBoot入口类 TestMain.java

## 问题
TestMain.java应该放在单元测试相应的目录,但是不能运行


## 使用注意事项
spring Boot项目Bean的装配默认规则是根据Application类所在的包位置从上往下扫描。
在子业务模块的Application类中增加对父类包路径的自动扫描

## 子业务记录请求结果话单(为elk统计使用)

# 调用spellcheck支持服务注册方式
##  直接调用solr方式
   spellcheck.service.enable=true
## 通过服务id的方式调用
   spellcheck.service.enable=false
   
   
   
## Feign 自动化注册
FeignAutoConfiguration   
