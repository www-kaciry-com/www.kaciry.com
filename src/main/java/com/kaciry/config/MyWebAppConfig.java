package com.kaciry.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>文件名称: MyWebAppConfig</li>
 * <li>文件描述:</li>
 * <li>版权所有: 版权所有(C) 2017</li>
 * <li>公 司: 厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要:</li>
 * <li>其他说明:</li>
 * <li>创建日期:2019/5/11</li>
 * </ul>
 * <ul>
 * <li>修改记录:</li>
 * <li>版 本 号:</li>
 * <li>修改日期:</li>
 * <li>修 改 人:</li>
 * <li>修改内容:</li>
 * </ul>
 *
 * @author majx
 * @version 1.0.0
 */
//@Configuration
public class MyWebAppConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                // 防止循环引用
                SerializerFeature.DisableCircularReferenceDetect,
                // 空集合返回[],不返回null
                SerializerFeature.WriteNullListAsEmpty,
                // 空字符串返回"",不返回null
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteMapNullValue
        );
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        //设定json格式且编码为UTF-8
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        fastMediaTypes.add(MediaType.TEXT_HTML);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        converters.add(fastJsonHttpMessageConverter);


//             MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//             List<MediaType> mappingJackson2MediaTypes = new ArrayList<>();
//             mappingJackson2MediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//             mappingJackson2MediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
//             mappingJackson2MediaTypes.add(MediaType.TEXT_HTML);
//             mappingJackson2HttpMessageConverter.setSupportedMediaTypes(mappingJackson2MediaTypes);


        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
        List<MediaType> stringMediaTypes = new ArrayList<>();
        stringMediaTypes.add(MediaType.TEXT_PLAIN);
        stringMediaTypes.add(MediaType.TEXT_HTML);
        stringMediaTypes.add(MediaType.APPLICATION_JSON);
        stringHttpMessageConverter.setSupportedMediaTypes(stringMediaTypes);
        converters.add(stringHttpMessageConverter);

    }

    @Override
    // 用于在HandlerMappings中设置路径的匹配样式
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 配置是否使用通用后缀匹配符(".*")
        // 如果设为true则当设置匹配"/users"时也会对"/users.*"进行匹配
        // 默认值为true
        configurer.setUseSuffixPatternMatch(true);
        // 配置是否匹配url而不考虑是否存在拖尾斜杠
        // 如果设为true则当匹配"/users"时也会匹配"/users/"
        // 默认值为true
        configurer.setUseTrailingSlashMatch(true);
        // 配置是否后缀匹配模式只适用于显示注册的路径拓展
        // 如果设置为true，当传递的路径参数中有特殊含义和作用的"."符号时框架不会对其进行处理
        // 默认值为false
        configurer.setUseRegisteredSuffixPatternMatch(true);
        // 设置一个UrlPathHelper来帮助Spring解析路径
        // 使用这个自定义的UrlPathHelper来覆盖默认的UrlPathHelper
        // 或者是在多个HandlerMappings和MethodNameResolvers中共享UrlPathHelper
        configurer.setUrlPathHelper(new UrlPathHelper());
        // 设置一个PathMatcher用于匹配URL路径
        // 默认的是AntPathMatcher
        configurer.setPathMatcher(new AntPathMatcher());
        // 设置一个路径前缀来匹配controller中的方法,
        // 在Spring初始化阶段，如果第二个参数检测结果返回为true则
        // "/prefix"会作为一个前缀添加到requestMapping的前面,
        // 比如方法上的RequestMapping的注解为"/method"，则这个方法
        // 最终的匹配路径是"/prefix/method"
        // 初始化的地方为RequestMappingHandler.getPathPrefix()
//        configurer.addPathPrefix("/prefix", (aClass) -> true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射 /static 的请求到 classpath 下的 static 目录
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
        //Linux 下目录配置
        //registry.addResourceHandler("/files/**").addResourceLocations("file:/www/wwwroot/www.kaciry.com/upload/");
        //Windows下目录配置
        registry.addResourceHandler("/files/**").addResourceLocations("file:F://upload/");
    }

//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//
//        // 设置解析用户请求的内容格式的策略,SpringMVC 默认加载两个该接口的实现类：
//        // ServletPathExtensionContentNegotiationStrategy–根据文件扩展名。
//        // HeaderContentNegotiationStrategy–根据HTTP Header里的Accept字段。
//        configurer.strategies(new ArrayList<>());
//
//        // 设置是否在URL中的path的拓展是否应该被用于作为被要求的内容格式
//        // 默认设置为true
//        // 如果一个请求的url为"/content.pdf"则会被认为是请求"application/pdf"
//        // 同时直接忽略"Accept"头中的格式
//        configurer.favorPathExtension(true);
//
//        // 向mediaTypes集合中添加，这里添加的顺序必须要是有序的以方便参数策略工作
//        // 在这里注册的所有的拓展名都是反射型文件下载攻击的白名单
//        configurer.mediaType("pdf", MediaType.APPLICATION_PDF)
//                .mediaType("json", MediaType.APPLICATION_JSON);
//
//        // 将一个hashMap中所有的键值对都作为内容格式加入configurer中
//        configurer.mediaTypes(new HashMap<>());
//
//        // 将原来记录的MediaTypes清除并更换为新的HashMap中的内容
//        configurer.replaceMediaTypes(new HashMap<>());
//
//        // 设置当无法找到相匹配的类型时是否忽略URL路径中的类型
//        // 设置为false则当找不到时会抛出HttpMediaTypeNotAcceptableException
//        // 默认设置为true
//        configurer.ignoreUnknownPathExtensions(true);
//
//        // 当favorPathExtension被设置的时候，这个方法被用于确认是否只使用被注册
//        // 的匹配类型来进行路径解析
//        // 默认未设置值
//        configurer.useRegisteredExtensionsOnly(true);
//
//        // 设置一个request的parameter是否会被解析为media type
//        // 如果要使用的话需要增加一个mediaType(String, MediaType)
//        // 默认设置为false
//        configurer.favorParameter(true);
//
//        // 设置要用于确定请求的媒体类型的参数的名称
//        // 默认值为"format"
//        configurer.parameterName("format");
//
//        // 设置时候禁止进行对request头的"Accept"的检查
//        // 默认未false
//        configurer.ignoreAcceptHeader(false);
//
//        // 设置当请求的类型没有匹配的时候的默认类型，按优先级顺序排序
//        // 如果想要支持所有的类型可以在最后使用MediaType.ALL
//        // 默认下没有任何设置
//        configurer.defaultContentType(MediaType.APPLICATION_JSON, MediaType.APPLICATION_PDF);
//
//        // 设置当没有任何类型被请求的时候的自定义类型处理策略
//        // 默认没有进行设置
//        configurer.defaultContentTypeStrategy(new PathExtensionContentNegotiationStrategy());
//    }

//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        // 设置一个异步线程池
//        // 默认使用SimpleAsyncTaskExecutor
//        configurer.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        // 设置异步request等待被处理的超时时间
//        // 默认的大小为10秒
//        configurer.setDefaultTimeout(10);
//        // 设置Callable任务的拦截器
//        configurer.registerCallableInterceptors(new CallableProcessingInterceptor() {
//        });
//        // 设置Callable任务的带有延迟的拦截器
//        configurer.registerDeferredResultInterceptors(new DeferredResultProcessingInterceptor() {
//        });
//    }

//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        // 激活默认的default servlet
//        // 默认的servlet由tomcat提供，一般的名字叫做"default"，匹配路径为"/"
//        configurer.enable();
//        // 设置default servlet的名称
//        configurer.enable("default");
//    }
//    @Override
    //传入的FormatterRegister默认为WebConversionService
//    public void addFormatters(FormatterRegistry registry) {
//        // 增加一个formatter
//        registry.addFormatter(new CurrencyStyleFormatter());
//
//        // 增加一个为指定类型进行类型转换的formatter
//        registry.addFormatterForFieldType(String.class, new CurrencyStyleFormatter());
//
//        // 增加一个对注解进行formatter的FormatterFactory
//        registry.addFormatterForFieldAnnotation(new AnnotationFormatterFactory<Annotation>() {
//            @Override
//            public Set<Class<?>> getFieldTypes() {
//                return null;
//            }
//            @Override
//            public Printer<?> getPrinter(Annotation annotation, Class<?> fieldType) {
//                return null;
//            }
//            @Override
//            public Parser<?> getParser(Annotation annotation, Class<?> fieldType) {
//                return null;
//            }
//        });
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration registration = registry.addInterceptor(new HandlerInterceptor() {
//            @Override
//            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//                System.out.println("自定义拦截器");
//                return true;
//            }
//
//            @Override
//            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//            }
//
//            @Override
//            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//            }
//        });
        //排除的路径
//      registration.excludePathPatterns("/login");
//      registration.excludePathPatterns("/logout");

        //拦截全部
//        registration.addPathPatterns("/**");

        //将这个controller放行
//        registration.excludePathPatterns("/pages/errorpage/*");
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 增加一个资源处理器用于针对URL中对静态资源的调用
//        // 处理器将会对每一个匹配上的URL进行调用
//        // 允许使用诸如"/static/**"或者/css/{filename:\\w+\\.css}
//        ResourceHandlerRegistration registration = registry.addResourceHandler("/static/**","/template/**");
//        // 添加一个或多个资源地址用于处理静态资源的请求，多个静态资源库如果
//        // 有同样的文件会选择优先级高的
////        registration.addResourceLocations("/resourceLocation1", "/resourceLocation2");
//        // 设置静态资源处理器的缓存时间
//        // 0表示没有缓存，大于0的数表示缓存过期的秒数 默认0
//        registration.setCachePeriod(500);
//        // 设置一个缓存管理器，管理器中的缓存时间设置会覆盖上一步中设置的缓存过期时间
//        registration.setCacheControl(CacheControl.empty());
//        // 返回一个资源处理器链，设置为true表示使用缓存，推荐为true
//        ResourceChainRegistration resourceChainRegistration = registration.resourceChain(true);
//        // 给ResourceChain添加一个resolver
//        // ResourceResolver用于根据url解析获取静态文件
//        resourceChainRegistration.addResolver(new ResourceResolver() {
//            @Override
//            public Resource resolveResource(HttpServletRequest request, String requestPath, List<? extends Resource> locations, ResourceResolverChain chain) {
//                return null;
//            }
//
//            @Override
//            public String resolveUrlPath(String resourcePath, List<? extends Resource> locations, ResourceResolverChain chain) {
//                return null;
//            }
//        });
//        // 给resourceChain添加一个resourceTransformer
//        // resourceTransformer用于给返回的静态文件进行一些自定义修改
//        resourceChainRegistration.addTransformer(new ResourceTransformer() {
//            @Override
//            public Resource transform(HttpServletRequest request, Resource resource, ResourceTransformerChain transformerChain) throws IOException {
//                return null;
//            }
//        });
//
//        // 返回是否当前的pathPattern已经被注册
//        Boolean has = registry.hasMappingForPattern("/pathPattern");
//
//        // 为当前的资源处理器设置一个优先级
//        registry.setOrder(1);
//    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        // 允许指定的pathPattern可以进行跨域请求
//        CorsRegistration corsRegistration = registry.addMapping("/pathPattern");
//        // 设置允许哪些可以进行跨域访问，设置为"*"表示允许所有
//        // 默认设置为允许所有
//        corsRegistration.allowedOrigins("http://domain1.com", "http://domain2.com");
//        // 设置允许的跨域请求动作，设置为"*"表示允许所有
//        // 默认设置为允许简单动作，包括GET POST HEAD
//        corsRegistration.allowedMethods("GET", "POST");
//        // 设置允许的请求头，默认设置为允许所有，即"*"
//        corsRegistration.allowedHeaders("Cache-Control", "Content-Language");
//        // 设置response的头结构，不支持"*"
//        corsRegistration.exposedHeaders("Cache-Control", "Content-Language");
//        // 设置浏览器是否需要发送认证信息
//        corsRegistration.allowCredentials(true);
//        // 设置客户端保存pre-flight request缓存的时间
//        // pre-flight request 预检请求
//        corsRegistration.maxAge(1);
//    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        // 为指定的url设置一个viewController
//        ViewControllerRegistration viewControllerRegistration
//                = registry.addViewController("/admin/**");
//        // 为url设置返回值，默认值为200
//        viewControllerRegistration.setStatusCode(HttpStatus.valueOf(404));
//        // 设置真实有效的view名，如果不设置默认为null
//        viewControllerRegistration.setViewName("/foo/bar");
//
//        // 设置原url和跳转到的url
//        RedirectViewControllerRegistration redirectViewControllerRegistration
//                = registry.addRedirectViewController("/urlPath", "/redirectUrl");
//        // 设置跳转url的状态码，默认为302
//        redirectViewControllerRegistration.setStatusCode(HttpStatus.valueOf(302));
//        // 是否将以斜杠("/")开头的定重定向URL解释为相对于当前ServletContext
//        // 默认值为true
//        redirectViewControllerRegistration.setContextRelative(true);
//        //是否传播当前请求的查询参数，默认值为false
//        redirectViewControllerRegistration.setKeepQueryParams(true);
//
//        // 对于设置的url将直接返回状态码而不进行任何body的渲染
//        registry.addStatusController("/urlPath", HttpStatus.valueOf(404));
//
//        // 设置当前viewController的优先值，默认为1
//        // 注解的Controller的优先值为0
//        registry.setOrder(1);
//    }

//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        // 返回是否当前有resolver被注册
//        boolean has = registry.hasRegistrations();
//        // 启用contentNegotiatingViewResolver来前置所有其他配置的视图
//        // 解析器，并根据客户端请求的媒体类型在所有选择的视图中进行选择
//        registry.enableContentNegotiation();
//
//        // 启用contentNegotiatingViewResolver来前置所有其他配置的视图
//        // 解析器，并根据客户端请求的媒体类型在所有选择的视图中进行选择
//        registry.enableContentNegotiation(true);
//    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        // 添加一个方法参数处理器
//        resolvers.add(new HandlerMethodArgumentResolver() {
//            @Override
//            public boolean supportsParameter(MethodParameter parameter) {
//                return false;
//            }
//
//            @Override
//            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//                return null;
//            }
//        });
//    }

//    @Override
//    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
//        // 添加一个方法返回值处理器
//        handlers.add(new HandlerMethodReturnValueHandler() {
//            @Override
//            public boolean supportsReturnType(MethodParameter returnType) {
//                return false;
//            }
//
//            @Override
//            public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
//
//            }
//        });
//    }

//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//        // 用于扩展或修改已配置的转换器列表的钩子
//        converters.add(1, new ByteArrayHttpMessageConverter());
//    }

//    @Override
//    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
//        // 添加一个处理异常的解析器
//        resolvers.add(new DefaultHandlerExceptionResolver());
//    }

//    @Override
//    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
//
//        // 用于拓展活修改已经加入配置中的解析器
//        resolvers.add(1, new DefaultHandlerExceptionResolver());
//    }


}
