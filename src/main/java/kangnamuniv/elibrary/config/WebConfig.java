package kangnamuniv.elibrary.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AuthInterceptor authInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("addInterceptors");
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")    // 모든 요청에 대해 인터셉터 적용
                .excludePathPatterns("/user/**"); // 로그인 및 회원가입 페이지는 제외
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/pdf/**")
                .addResourceLocations("classpath:/uploads/pdf/");
    }
}
