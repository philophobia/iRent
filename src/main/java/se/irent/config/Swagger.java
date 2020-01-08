package se.irent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swagger implements WebMvcConfigurer {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()  //RequestHandlerSelectors.any()，所有的路径都去扫描
                //这个扫描包的意思一样，固定路径就去相应的路径扫描
                .apis(RequestHandlerSelectors.basePackage("se.irent.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //对生成的api文档的一个描述
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("后台系统API文档")
                .description("为iRent后台管理系统提供的API进行说明")
                .version("版本：1.0")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/static/");
    }
}
