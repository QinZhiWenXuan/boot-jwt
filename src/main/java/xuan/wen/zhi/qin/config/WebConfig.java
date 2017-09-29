package xuan.wen.zhi.qin.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        UrlBasedCorsConfigurationSource basedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        filter.setFilter(new CorsFilter(basedCorsConfigurationSource));
        /***
         * Set the order of the registration bean
         */
        filter.setOrder(0);
        /***
         * Add an origin to allow.
         */
        config.addAllowedOrigin("*");
        /***
         * Add an actual request header to allow.
         */
        config.addAllowedHeader("*");

        /***
         * Add an HTTP method to allow.
         */
        config.addAllowedMethod("*");
        basedCorsConfigurationSource.registerCorsConfiguration("/**", config);
        return filter;
    }
}
