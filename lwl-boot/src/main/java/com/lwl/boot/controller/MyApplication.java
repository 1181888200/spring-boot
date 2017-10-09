package com.lwl.boot.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
/**
 * @EnableAutoConfiguration 用于自动配置。简单的说，它会根据你的pom配置（实际上应该是根据具体的依赖）来判断这是一个什么应用，并创建相应的环境。
 */
public class MyApplication {


	@RequestMapping("/")
    @ResponseBody
    public String index() {
        return "你现在看到的是帅帅的我";
    }
	
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello 帅帅的我出现啦";
	}

	
	
    public static void main(String[] args) throws Exception {
    	/**
    	 * SpringApplication 则是用于从main方法启动Spring应用的类。默认，它会执行以下步骤：
    		1.创建一个合适的ApplicationContext实例 （取决于classpath）。
    		2.注册一个CommandLinePropertySource，以便将命令行参数作为Spring properties。
    		3.刷新application context，加载所有单例beans。
    		4.激活所有CommandLineRunner beans。
    		默认，直接使用SpringApplication 的静态方法run()即可。但也可以创建实例，并自行配置需要的设置。
    	 */
        SpringApplication.run(MyApplication.class, args);
        
//        SpringApplication app = new SpringApplication(MyApplication.class);
//        app.run(args);
        
        
        /**
         * 如果设置 properties 则配置参数会读取properties设置的值
         */
      
        
    }
    
    /**
     * 设置基本异常处理抛出页面
     * @return
     * @create 2017-10-9 上午11:44:13
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
                container.addErrorPages(error401Page, error404Page, error500Page);				
			}
        };
    }
	
}
