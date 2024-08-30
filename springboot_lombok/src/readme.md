# Lombok����

* Lombok ����
> 1. ��JavaBean����������ʹ��Lombok��ע���ô�����Ӽ��
> 2. Java��Ŀ�У��ܶ�û�м����������ֱ�����ڵĴ��룺���磺Pojo �� getter/setter/toString ���쳣����I/O���Ĺرղ����ȵ�
> ��Щ�����û�м�����������Ӱ���Ŵ�������ۣ�LombokӦ�˶���
>
>



* Spring Boot �� IDEA �ٷ�֧��
> 1. IDEA 2020�Ѿ������� Lombok���
> 2. Spring Boot 2.1.x֮��İ汾Ҳ�� Stater�������� Lombok����
>
>

```
java

@Data��ע�������ϣ��ṩ���������Ե�getting��setting���������⻹�ṩ��equals,canEqual,hashCode,toString����
@Setter: ע���������ϣ�Ϊ�����ṩ setting ����
@Getter: ע���������ϣ�Ϊ�����ṩ getting ����
@Log4j��ע�������ϣ�Ϊ���ṩ��һ��������Ϊ: log �� log4j����־����
@NoArgsConstructor: ע�������ϣ�Ϊ���ṩһ���޲εĹ��췽��,(һ�����ṩ)
@AllArgsConstructor: ע�������ϣ�Ϊ���ṩһ��ȫ�εĹ��췽��
@Cleanup:���Թر���
@Builder: ��ע�����Ӹ�������ģʽ
@Synchronized: ��ͬ����
@SneakyThrows: ��ͬ��try/catcher�����쳣
@NonNull:����������Ӹ����ע�⣬����Ϊnull���׳���ָ���쳣
@Value: ע���@Value���ƣ�����������������г�Ա����Ĭ�϶���Ϊ private final ���Σ����Ҳ�������set()����

```


ʹ��lombokע��򻯴��룬����ͨ��idea�Դ��ķ����빦�� target����Furn.class��Դ�룬��
���Կ������ɵ��������롣


�ر�˵��: @Data �е� @RequiredArgsConstructor
������дcontroller ���� Service���ʱ����Ҫע��ܶ�� mapper�ӿڻ��������service�ӿڣ���ʱ��ͻ�д�ܶ�
��@Autowiredע�⣬���뿴�������ҡ�Lombok �ṩ��һ��ע��:
@RequiredArgsConstructor(onConstructor=@_(@AutoWired))
д�����Ͽ��Դ���@Autowiredע�⣬��Ҫע���ʱ��ע��ʱ����Ҫ��final���壬����ʹ�� @notnullע�� 


��idea ��װ lombok���

��װ���Ҳ�����û�����ע����磺@Data,@Getter...
���ǲ���ʹ������չ���ܣ�������־���...���������ǻ��ǰ�װһ�£�Ҳ�Ƚϼ򵥡�
ֱ��ȥ����̳ǣ�����: Lombok ����