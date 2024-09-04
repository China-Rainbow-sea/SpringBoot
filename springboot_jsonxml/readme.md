# 内容协商


# AbstractJackson2HttpMessageConverter 
```
java
 protected void writeInternal(Object object, @Nullable Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        MediaType contentType = outputMessage.getHeaders().getContentType();  // 重点
        JsonEncoding encoding = this.getJsonEncoding(contentType);  // 重点
        Class<?> clazz = object instanceof MappingJacksonValue ? ((MappingJacksonValue)object).getValue().getClass() : object.getClass();
        ObjectMapper objectMapper = this.selectObjectMapper(clazz, contentType);
        Assert.state(objectMapper != null, "No ObjectMapper for " + clazz.getName());
        OutputStream outputStream = StreamUtils.nonClosing(outputMessage.getBody());


 try {
            JsonGenerator generator = objectMapper.getFactory().createGenerator(outputStream, encoding);
            Throwable var10 = null;

// generator 这个值非常重要。

            try {
                this.writePrefix(generator, object);
                Object value = object;
                Class<?> serializationView = null;
                FilterProvider filters = null;
                JavaType javaType = null;
                if (object instanceof MappingJacksonValue) {
                    MappingJacksonValue container = (MappingJacksonValue)object;
                    value = container.getValue();
                    serializationView = container.getSerializationView();
                    filters = container.getFilters();
                }
```



1. 根据客户端接收能力不同，SpringBoot返回不同媒体类型的数据
2. 比如：客户端Http请求Accept:application/xml 则返回xml数据，客户端Http请求 Accept:application/json
则返回json数据。


Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
xml 为 0.9 优先级更高
*/* 为 0.8 稍微低一点。

# 内容协商:注意事项和使用细节
1. Postman 可以通过修改 Accept 的值，来返回不同的数据格式
2. 对于浏览器，我们无法修改其 Accept的值，怎么办？解决方案：开启支持基于请求参数的内容协商功能
3. 修改 application.yaml（必须在类路径下才行）,开启基于请求参数的内容协商功能。

```
yaml
spring:
  mvc:
    contentnegotiation:
      favor-parameter: true # 开启基于请求参数的内容协商功能
      #？format=json 
      
```
注意：参数format是规定好的，在开启请求参数的内容协商功能后，SpringBoot底层 ParameterContentNegotiationStrategy
会通过 format 来接收参数，然后返回对应的媒体类型/数据格式，当然format=xxx,这个xxx媒体类型/数据格式是SpringBoot可以
处理的才行，不能乱写。
```
java
public class ParameterContentNegotiationStrategy extends AbstractMappingContentNegotiationStrategy {
    private String parameterName = "format";
```
http://localhost:8080/get/monster?format=json 
注意：有个问号中间
http://localhost:8080/get/monster?format=xml


```
yaml
spring:
  mvc:
    contentnegotiation:
      favor-parameter: true # 开启基于请求参数的内容协商功能
      #？format=json
      parameter-name: rainbowsea # 指定一个内容协商的参数名，就不再是默认的 format而是
      # ？rainbowsea=json ，默认的失效了,
失效了就是 默认的 xml 优先级比较高了。
```
