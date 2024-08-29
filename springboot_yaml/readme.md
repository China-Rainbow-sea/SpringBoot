# yaml ����

## yaml ����˵��

1. YAML �ǡ�YAML Ain't a Markup Language�� (YAML����һ�ֱ��)�ĵݹ���д���ڿ�������������ʱ��
YAML����˼��ʵ�ǣ���Yet Another Markup Language�� ������һ�ֱ�����ԣ�����Ϊ��ǿ����������������
��Ϊ���ģ��������Ա������Ϊ�ص㣬���÷���������������{�ٶȰٿ�}
2. YAML ������Ϊ���ģ��������Ա������Ϊ�ص㡣
3. YAML ��Ȼ��һ�ֱ�����ԣ����Ǻʹ�ͳ�ı�����Բ�һ������������Ϊ���ĵı������
4. YAML �ǳ��ʺ�������������Ϊ���ĵ������ļ�[springboot:application.yml]

## yaml�����﷨
1. ��ʽΪ: key: value  ע�⣺�����пո�
2. ���ִ�Сд
3. ʹ��������ʾ�㼶��ϵ
4. ����������ʹ�� tab��ֻ����ո���Щ�ط�Ҳʶ��tab,�Ƽ�ʹ�ÿո�
5. �����Ŀո�������Ҫ��ֻҪ��ͬ�㼶��Ԫ������뼴��
6. �ַ�����������ţ���Ȼ����������Ҳ����ν
7. yml�У�ע��ʹ�� #
8. yaml Ҳ����д�� yml �ĺ�׺

# yaml����������

## ������
1. �������������ģ������ٷֵ�ֻ��date,boolean,string,nmber,null
2. ������ʽ: key: value
```
yaml

monster:
    id: 100
    name: ţħ��
    isMarried: true
    birth: 200/10/10
    car: 
        name: ����
        price: 999
# skill: {�Ž���,ţħ��}
```


## ����
1. ����: ��ֵ�Եļ��ϣ�����: map,hash,set,object
����д���� k:{k1:v1,k2:v2,k3:v3}
# ��
k:
    k1:v1
    k2:v2
    k3:v3

```
yaml
monster:
    id: 100
    name: ţħ��
    isMarried: true
    birth: 200/10/10
    car: 
        name: ����
        price: 999
    wife:{key01: �������,key02: ���ȹ���}

# ����ķ�ʽ
    skill:
    - �Ž���
    - ţħȭ
hobby: {�Ⱦ�,����}

``` 


## ����
1. ���飺һ�鰴���������ֵ������: array, list,queue
����д��: K: [v1,v2,v3] ע���пո�
# ����
K:
- v1
- v2
- v3
```
yaml
hobby:[������,��ƹ����,������]

hobby:
- ������
- ��ƹ����
- ������
```

1. 如果application.properties 和 application.yml 有相同的前缀绑定，
则 application.properties优先级高，开发时，应当避免
2. 字符串无需加引号，这个在前面已经演示了，如果你用"" 或者'' 包起来，也可以，简单举例。

# 解决yaml配置文件，不提示字段信息问题
1.如图，大家可以知道，在编写application.yml文件时，没有提示Monster的字段信息，非常不方便
方案一：加上如下依赖
```
xml

        <!--        解决  @ConfigurationProperties(prefix = "furn01") 提示信息,  在 pom.xml  增加依赖,  即可
Spring Boot Configuration Annotation Processor not configured 提示信息
-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
<!--
    这里配置 optional 为 ture
    说明: 表示防止将此依赖传递到其它模块上，
-->
            <optional>true</optional>
        </dependency>
```
同时，你已经配置好了的 属性，它是不会提示的，非常的方便

如果上述方式没有提示的话，可以安装一个名为 yaml的插件来搞定
