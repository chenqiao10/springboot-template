package com.example.demo.config;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * 配置2个事务管理器：jpa和jdbc。默认是jpa事务
 * 使用方法@Transactional(transactionManager="jdbcTxManager",readOnly=false)
 * @author lifw
 *
 */
@Configuration
@EnableTransactionManagement
public class ProfileTransaction implements TransactionManagementConfigurer  {

	@Resource(name="jpaTxManager")
    private PlatformTransactionManager jpaTxManager;
	
	// 创建事务管理器:jpa
    @Bean(name = "jpaTxManager")
    public PlatformTransactionManager jpaTxManager(EntityManagerFactory factory) {
    	return new JpaTransactionManager(factory);
        
    }

    // 创建事务管理器:jdbc
    @Bean(name = "jdbcTxManager")
    public PlatformTransactionManager jdbcTxManager(DataSource dataSource) {
    	return new DataSourceTransactionManager(dataSource);
    }
    
    //默认是采用jpa的事务
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return jpaTxManager;
	}
}
