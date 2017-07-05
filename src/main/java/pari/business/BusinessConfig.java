package pari.business;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class BusinessConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setJpaVendorAdapter(jpaVendorAdapter());
		emf.setPersistenceUnitName("pari");
		return emf;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}
}