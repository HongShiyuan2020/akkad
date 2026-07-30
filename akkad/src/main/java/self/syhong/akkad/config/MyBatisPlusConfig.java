package self.syhong.akkad.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

@Configuration
public class MyBatisPlusConfig {
    /**
     * 分页插件配置
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.POSTGRE_SQL);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        
        return interceptor;
    }

    // @Bean
    // public ConfigurationCustomizer configurationCustomizer() {
    //     return configuration -> 
    //         configuration.getTypeHandlerRegistry()
    //             .register(
    //                 List.class,
    //                 JacksonTypeHandler.class
    //             );
    // }
}
