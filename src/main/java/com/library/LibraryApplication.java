package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.library"})
@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}


/*
CREATE USER 'testuser'@'email.com' IDENTIFIED BY 'AyRGzYuaJFCV8N';
GRANT ALL PRIVILEGES ON *.* TO 'testuser'@'email.com' WITH GRANT OPTION;
FLUSH PRIVILEGES;
EXIT;
*/
