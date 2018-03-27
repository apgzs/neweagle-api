<p>

  <a href="https://gitee.com/xinyingkeji/neweagle-api/wikis">
    <img alt="maven" src="https://img.shields.io/badge/%E5%BC%80%E5%8F%91%E6%96%87%E6%A1%A3-10%25-orange.svg">
  </a>
  <a href="#">
    <img alt="maven" src="https://img.shields.io/badge/version-v1.0.0-green.svg">
  </a>

  <a href="https://www.apache.org/licenses/LICENSE-2.0">
    <img alt="code style" src="https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square">
  </a>
</p> 

**适用对象** 
- 应小伙伴反应现在好多框架搞得好复杂看不懂、很多中小项目不需要用到这种框架，公司以前的项目也是很杂乱、有时候只是想舒舒服服地写个接口都无从下手。SO你如果有遇到这种问题这个项目也许能帮得到你！

 **Quick Start** 
- 建议使用IntelliJ IDEA导入此项目
- 为IntelliJ IDEA安装lombok插件并重启
- 用git/svn clone项目
- 启动redis
- 修改配置文件中的redis、mysql配置  注意：配置文件中的密码使用jasypt进行加密，可在test包中JasypTest类中获得加密后的密码，不加密去掉ENC() 即可  直接写上明文密码
- 不同的配置环境打包, 如本地环境: mvn install package -Plocal -DskipTests

 **技术栈** 
- springboot2.0
- docker
- spring security
- jwt
- auth
- mybatis-plus【多租户、公共字段动态插入等，具体详见官网[链接](http://mp.baomidou.com)】
- redis
- druid
- jasypt
- rabbitmq
- quartz【分布式形式，持久化】
- 公共接口签名sign验证
- .....

持续更新中。。。
- 多数据源、读写分离
- elasticsearch
- 基于vue构建的前后端分离的管理权限管理平台

- 分模块化

- 微服务化
