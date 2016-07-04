package com.springmvc.controller.admin;


/**
 * 需要用户登陆验证的Controller，都必须继承该类
 * UserAwareInterceptor负责对实现该抽象类的所有controller进行拦截，并验证
 */
public abstract class UserAwareController {
	
}
