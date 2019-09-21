package com.seeyu.normal.configuration;

import com.seeyu.normal.initialization.DatabaseUpgradeInitialization;
import com.seeyu.normal.service.DataUpgradeService;
import com.seeyu.normal.service.MysqlExecuteService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @author seeyu
 * @date 2019/6/12
 */

@ImportAutoConfiguration({
        DatabaseUpgradeInitialization.class,
        DataUpgradeService.class, MysqlExecuteService.class
})
@ConditionalOnClass({SqlSessionFactory.class})
@ConditionalOnBean({SqlSessionFactory.class})
@AutoConfigureAfter({MybatisAutoConfiguration.class})
@Configuration
public class DatabaseUpgradeAutoConfigure {
}
