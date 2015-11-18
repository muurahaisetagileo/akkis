package fi.agileo.akkis.jpaconfigurations;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.cache.HashtableCacheProvider;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/* Javalla tehty Spring-konfiguraatiotiedosto. 
 * Kaikkien Metodien palautusarvona on @Bean, joita voi käyttää Springissä.
 */

@Configuration
public class JpaConfiguration {
	
	/*
	 Example 5-23 Using composite-unit.properties in a Property Map

	Map props1 = new HashMap();
 
   props1.put("javax.persistence.jdbc.user", "user1");
   props1.put("javax.persistence.jdbc.password", "password1");
   props1.put("javax.persistence.jdbc.driver", "oracle.jdbc.OracleDriver");
   props1.put("javax.persistence.jdbc.url", "jdbc:oracle:thin:@oracle_db_url:1521:db");
 
	Map props2 = new HashMap();
 
   props2.put("javax.persistence.jdbc.user", "user2");
   props2.put("javax.persistence.jdbc.password", "password2");
   props2.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
   props2.put("javax.persistence.jdbc.url", " jdbc:mysql://my_sql_db_url:3306/user2");
 
	Map memberProps = new HashMap();
   memberProps.put("memberPu1", props1);
   memberProps.put("memberPu2", props2);
 
	Map props = new HashMap();
   props.put("eclipselink.logging.level", "FINEST");
   props.put("eclipselink.composite-unit.properties", memberProps);
 
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("compositePu", props);

	 */

	@Value("#{dataSource}")
	private javax.sql.DataSource dataSource;

	@Bean
	public Map<String, Object> jpaProperties() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("hibernate.dialect", MySQL5Dialect.class.getName());
		props.put("hibernate.cache.provider_class", HashtableCacheProvider.class.getName());
		return props;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager( localContainerEntityManagerFactoryBean().getObject() );
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(this.dataSource);
		//lef.setJpaPropertyMap(this.jpaProperties());
		lef.setJpaVendorAdapter(this.jpaVendorAdapter());
		return lef;
	}

}
