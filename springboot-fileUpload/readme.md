# Spring Boot 的文件上传
 注意如果配置了拦截器，资源的上传 upload.html页面也是要放行的
 
 
 注意：默认单个文件最大1MB,一次上传多个文件最大10MB，我们可以修改
 MultipartProperties类当中的属性，我们可以通过在 resources类路径下，application.yaml 进行修改
```
yaml
spring:
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 50MB

```



# 文件上传注意事项：
1.解决文件覆盖问题，如果文件名相同，会出现覆盖问题，如何解决
2.解决文件分目录存放问题，如果将文件都上传到一个目录下，当上传文件很多时，会造成文件速度变慢，
因此，可以将文件上传到不同目录，比如一天上传的文件，统一放到一个文件夹 年/月/日，比如 2022/11/11目录
