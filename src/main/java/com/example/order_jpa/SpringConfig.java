package com.example.order_jpa;

import com.example.order_jpa.filter.LogFilter;
import com.example.order_jpa.filter.LoginFilter;
import com.example.order_jpa.interceptor.LogInterceptor;
import com.example.order_jpa.interceptor.LoginInterceptor;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)  // 순서가 1번
                .addPathPatterns("/**")  // 하위 패스에 전부 적용
                .excludePathPatterns("/css/**", "/*.ico", "/error"); // 적용 제외

        registry.addInterceptor(new LoginInterceptor())
                .order(2)// 순서가 2번
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error",
                        "/user/add", "/login", "/logout", "/product/list") ;//
    }


    //@Bean
    public FilterRegistrationBean<LogFilter>   logFilter() {
        FilterRegistrationBean<LogFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1); // 1번
        filterRegistrationBean.addUrlPatterns("/*");// 전부다 적용해
        return filterRegistrationBean;
    }

    //@Bean
    public FilterRegistrationBean<LoginFilter> loginFilter() {
        FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.setOrder(2); // 2번
        filterRegistrationBean.addUrlPatterns("/order/*", "/product/add/*");// order 요청에 대해서만 적용해
        return filterRegistrationBean;
    }

}
