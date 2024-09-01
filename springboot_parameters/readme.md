# 接收参数相关注解


# 基本介绍

1. SpringBoot 接收客户都提交数据/参数会使用到相关注解
> @PathVariable,@RequestHeader,@ModelAttribute,@RequestParam,@CookieValue,@RequestBody


需求：演示各种方式提交数据/参数给服务器


接收参数相关注解应用实例


# 复杂参数
1.在开发中，SpringBoot在响应客户端请求时，也支持复杂参数
2.Map，Model，Errors/BindingResult,RedirectAttributes,ServletResponse,
SessionStatus,UriComponentsBuilder,ServletUriComponentsBuilder,HttpSession
3. Map,Model 数据会被放在 request 域，到时 Debug 一下
4. RedirectAttributes 重定向携带数据。





# 自定义对象参数:自动封装

1.在开发中，SpringBoot 在响应客户端请求时,也支持自定义对象参数
2.完成自动类型转换与格式化
3.支持级联封装


SpringBoot在响应客户端请求时，将提交的数据封装成对象时，使用了内置的转换器
SpringBoot也支持自定义转换器，这个内置的转换器在debug的时候，可以看到，后面给大家演示，提供了
124个内置转换器，看下源码GenericConverter-ConvertiblePair

