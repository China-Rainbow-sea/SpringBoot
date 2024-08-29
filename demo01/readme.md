Spring Boot 注入打 ioc 容器：底层机制：仍然是：我们实现Spring 容器那一套机制 IO/文件扫描+注解+反射+集合+映射


SpringApplication.run()
DeBug  SpringApplication.run(MainApp.class, args); 看看 Spring Boot 是如何启动 Tomcat的
> 我们的Debug 目标：紧抓一条线，就是看到 tomcat 被启动的代码: 比如tomcat.start() 

1. SpringApplication.java
```
java
// 这里我们开始 Debug SpringApplication。run()
   public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
        return run(new Class[]{primarySource}, args);
    }

```

2. SpringApplication.java
```
java

    public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
        return (new SpringApplication(primarySources)).run(args);
    }

```


3.SpringApplication.java
```
java
 public ConfigurableApplicationContext run(String... args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        DefaultBootstrapContext bootstrapContext = this.createBootstrapContext();
        ConfigurableApplicationContext context = null;
        this.configureHeadlessProperty();
        SpringApplicationRunListeners listeners = this.getRunListeners(args);
        listeners.starting(bootstrapContext, this.mainApplicationClass);

        try {
            ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
            ConfigurableEnvironment environment = this.prepareEnvironment(listeners, bootstrapContext, applicationArguments);
            this.configureIgnoreBeanInfo(environment);
            Banner printedBanner = this.printBanner(environment);
            context = this.createApplicationContext();  // 特别分析： 创建容器
            context.setApplicationStartup(this.applicationStartup);
            this.prepareContext(bootstrapContext, context, environment, listeners, applicationArguments, printedBanner);
            this.refreshContext(context);  // 特别分析：刷新应用程序上下文，比如：初始化默认设置/注入相关Bean/启动 tomcat
            this.afterRefresh(context, applicationArguments);
            stopWatch.stop();
            if (this.logStartupInfo) {
                (new StartupInfoLogger(this.mainApplicationClass)).logStarted(this.getApplicationLog(), stopWatch);
            }

            listeners.started(context);
            this.callRunners(context, applicationArguments);
        } catch (Throwable var10) {
            this.handleRunFailure(context, var10, listeners);
            throw new IllegalStateException(var10);
        }

        try {
            listeners.running(context);
            return context;
        } catch (Throwable var9) {
            this.handleRunFailure(context, var9, (SpringApplicationRunListeners)null);
            throw new IllegalStateException(var9);
        }
    }
```


4. SpringApplication.java : 容器类型很多，会根据你的 this.webApplicationType 创建对应的容器
默认 this.webApplicationType 是 servlet 也就是 web 容器/处理的 servlet
```
java

    protected ConfigurableApplicationContext createApplicationContext() {
        return this.applicationContextFactory.create(this.webApplicationType);
    }

```

5. ApplicationContextFactory.java
默认是进入这个分支 case SERVLET:  返回 new AnnotationConfigServletWebServerApplicationContext();
```
java
public interface ApplicationContextFactory {
    ApplicationContextFactory DEFAULT = (webApplicationType) -> {
        try {
            switch(webApplicationType) {
            case SERVLET:  // 默认是进入这个分支
                return new AnnotationConfigServletWebServerApplicationContext();
            case REACTIVE:
                return new AnnotationConfigReactiveWebServerApplicationContext();
            default:
                return new AnnotationConfigApplicationContext();
            }
        } catch (Exception var2) {
            throw new IllegalStateException("Unable create a default ApplicationContext instance, you may need a custom ApplicationContextFactory", var2);
        }
    };

    ConfigurableApplicationContext create(WebApplicationType webApplicationType);

    static ApplicationContextFactory ofContextClass(Class<? extends ConfigurableApplicationContext> contextClass) {
        return of(() -> {
            return (ConfigurableApplicationContext)BeanUtils.instantiateClass(contextClass);
        });
    }

    static ApplicationContextFactory of(Supplier<ConfigurableApplicationContext> supplier) {
        return (webApplicationType) -> {
            return (ConfigurableApplicationContext)supplier.get();
        };
    }
}
```

6. SpringApplication.java
```
java
    private void refreshContext(ConfigurableApplicationContext context) {
        if (this.registerShutdownHook) {
            shutdownHook.registerApplicationContext(context);
        }
        this.refresh(context);  // 特别分析，真正执行相关任务
    }

```

7. SpringApplication.java
```
java

    protected void refresh(ConfigurableApplicationContext applicationContext) {
        applicationContext.refresh();
    }
```

8.Servlet 中的  ServletWebServerApplicationContext.java

```
java
    public final void refresh() throws BeansException, IllegalStateException {
        try {
            super.refresh();  // 特别分析这个方法
        } catch (RuntimeException var3) {
            WebServer webServer = this.webServer;
            if (webServer != null) {
                webServer.stop();
            }

            throw var3;
        }
    }
```

9.ApplicationContextFactory .java

```
java

 public void refresh() throws BeansException, IllegalStateException {
        synchronized(this.startupShutdownMonitor) {
            StartupStep contextRefresh = this.applicationStartup.start("spring.context.refresh");
            this.prepareRefresh();
            ConfigurableListableBeanFactory beanFactory = this.obtainFreshBeanFactory();
            this.prepareBeanFactory(beanFactory);

            try {
                this.postProcessBeanFactory(beanFactory);
                StartupStep beanPostProcess = this.applicationStartup.start("spring.context.beans.post-process");
                this.invokeBeanFactoryPostProcessors(beanFactory);
                this.registerBeanPostProcessors(beanFactory);
                beanPostProcess.end();
                this.initMessageSource();
                this.initApplicationEventMulticaster();
                this.onRefresh();  // 特别分析，当父类完成通用的工作后，再重新动态绑定机制回到
                this.registerListeners();
                this.finishBeanFactoryInitialization(beanFactory);
                this.finishRefresh();
            } catch (BeansException var10) {
                if (this.logger.isWarnEnabled()) {
                    this.logger.warn("Exception encountered during context initialization - cancelling refresh attempt: " + var10);
                }

                this.destroyBeans();
                this.cancelRefresh(var10);
                throw var10;
            } finally {
                this.resetCommonCaches();
                contextRefresh.end();
            }

        }
    }
```

10. ServletWebServerApplicationContext.java
```
java

 protected void onRefresh() {
        super.onRefresh();

        try {
            this.createWebServer();  创建 webServer 可以理解成会创建指定 web服务器-tomcat
        } catch (Throwable var2) {
            throw new ApplicationContextException("Unable to start web server", var2);
        }
    }
```

11.ServletWebServerApplicationContext.java

```
java
 private void createWebServer() {
        WebServer webServer = this.webServer;
        ServletContext servletContext = this.getServletContext();
        if (webServer == null && servletContext == null) {
            StartupStep createWebServer = this.getApplicationStartup().start("spring.boot.webserver.create");
            ServletWebServerFactory factory = this.getWebServerFactory();
            createWebServer.tag("factory", factory.getClass().toString());
            this.webServer = factory.getWebServer(new ServletContextInitializer[]{this.getSelfInitializer
()}); // 特别分析，使用 TomcatServletWebServerFactory 创建一个TomcatWEbServer
            createWebServer.end();
            this.getBeanFactory().registerSingleton("webServerGracefulShutdown", new WebServerGracefulShutdownLifecycle(this.webServer));
            this.getBeanFactory().registerSingleton("webServerStartStop", new WebServerStartStopLifecycle(this, this.webServer));
        } else if (servletContext != null) {
            try {
                this.getSelfInitializer().onStartup(servletContext);
            } catch (ServletException var5) {
                throw new ApplicationContextException("Cannot initialize servlet context", var5);
            }
        }

        this.initPropertySources();
    }

```

12.TomcatServletWebServerFactory.java 会创建Tomcat 并启动 Tomcat
```
java
 public WebServer getWebServer(ServletContextInitializer... initializers) {
        if (this.disableMBeanRegistry) {
            Registry.disableRegistry();
        }

        Tomcat tomcat = new Tomcat();
        File baseDir = this.baseDirectory != null ? this.baseDirectory : this.createTempDir("tomcat");
        tomcat.setBaseDir(baseDir.getAbsolutePath());
        Connector connector = new Connector(this.protocol);
        connector.setThrowOnFailure(true);
        tomcat.getService().addConnector(connector);
        this.customizeConnector(connector);
        tomcat.setConnector(connector);
        tomcat.getHost().setAutoDeploy(false);
        this.configureEngine(tomcat.getEngine());
        Iterator var5 = this.additionalTomcatConnectors.iterator();

        while(var5.hasNext()) {
            Connector additionalConnector = (Connector)var5.next();
            tomcat.getService().addConnector(additionalConnector);
        }

        this.prepareContext(tomcat.getHost(), initializers);
        return this.getTomcatWebServer(tomcat);
    }
```

13.TomcatServletWebServerFactory.java
```
java

    protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
        return new TomcatWebServer(tomcat, this.getPort() >= 0, this.getShutdown());
    }

```


14.TomcatWebServer.java
```
java
    public TomcatWebServer(Tomcat tomcat, boolean autoStart, Shutdown shutdown) {
        this.monitor = new Object();
        this.serviceConnectors = new HashMap();
        Assert.notNull(tomcat, "Tomcat Server must not be null");
        this.tomcat = tomcat;
        this.autoStart = autoStart;
        this.gracefulShutdown = shutdown == Shutdown.GRACEFUL ? new GracefulShutdown(tomcat) : null;
        this.initialize(); // 分析这个方法
    }
```

15.TomcatWebServer.java

``` 
java
 private void initialize() throws WebServerException {
        logger.info("Tomcat initialized with port(s): " + this.getPortsDescription(false));
        synchronized(this.monitor) {
            try {
                this.addInstanceIdToEngineName();
                Context context = this.findContext();
                context.addLifecycleListener((event) -> {
                    if (context.equals(event.getSource()) && "start".equals(event.getType())) {
                        this.removeServiceConnectors();
                    }

                });
                this.tomcat.start();   // *********     启动 Tomcat 
                this.rethrowDeferredStartupExceptions();

                try {
                    ContextBindings.bindClassLoader(context, context.getNamingToken(), this.getClass().getClassLoader());
                } catch (NamingException var5) {
                }

                this.startDaemonAwaitThread();
            } catch (Exception var6) {
                this.stopSilently();
                this.destroySilently();
                throw new WebServerException("Unable to start embedded Tomcat", var6);
            }

        }
    }
```