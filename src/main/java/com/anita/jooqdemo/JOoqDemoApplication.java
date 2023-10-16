package com.anita.jooqdemo;

import jakarta.annotation.PostConstruct;
import model.Tables;
import model.tables.records.CountriesRecord;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JOoqDemoApplication {

	private final DefaultDSLContext dsl;

	public JOoqDemoApplication(DefaultDSLContext dsl) {
		this.dsl = dsl;
	}

	public static void main(String[] args) {
		SpringApplication.run(JOoqDemoApplication.class, args);
	}

	@PostConstruct
	public void post() {
		CountriesRecord countriesRecord = dsl
				.select()
				.from(Tables.COUNTRIES)
				.where(Tables.COUNTRIES.ID.eq(1))
				.fetchOneInto(Tables.COUNTRIES);
        assert countriesRecord != null;
        System.out.printf("FIRST COUNTRY: %s%n", countriesRecord.getName());

		CountriesRecord countriesRecord1 = dsl
				.select()
				.from(Tables.COUNTRIES)
				.where(Tables.COUNTRIES.NAME.like("%Jap%"))
				.fetchOneInto(Tables.COUNTRIES);
		assert countriesRecord1 != null;
		System.out.printf("%s is also in the list%n;", countriesRecord1.getName());
	}

}
