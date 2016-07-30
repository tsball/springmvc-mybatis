# springmvc-mybatis

1. 技术架构：spring mvc(spring boot) + mybatis + maven
2. 环境： eclipse + jdk7 + maven
3. Future Features: restful, authority, mybatis one-to-many/one-to-one sample, user crud, custom transaction level(control/service), findbug, checkstyle

功能：
1. 用户及角色管理
2. spring + security登录
3. 模板技术
4. 后台登录
5. 表单前后端验证
6. 国际化
7. pagination
   https://github.com/abel533/Mapper

1. MyBatis
   Use for the database operation. More refer: http://www.mybatis.org/mybatis-3/
   
   About common mapper:
   The project imports a third-party mapper3.jar for common curd.
   a) Link: 
   	  https://github.com/abel533/Mapper
   	  http://www.mybatis.tk/
   b) Please add annotation @Transient over model attribute while it is not a table column of db.
   
   
   