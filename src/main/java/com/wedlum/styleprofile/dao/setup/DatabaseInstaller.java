package com.wedlum.styleprofile.dao.setup;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInstaller implements InitializingBean {

	@Autowired
	DataSource dataSource;

	@Override
	public void afterPropertiesSet() throws Exception {
		InputStream inputStream = this.getClass().getResourceAsStream("/META-INF/schema.sql");
		String installationScript = IOUtils.toString(inputStream);
		String[] scripts = installationScript.split(";");

		for (String script : scripts) {
			System.out.println(script);

			if (script.trim().isEmpty()) continue;

			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.execute(script);
			statement.close();
		}
	}

}
