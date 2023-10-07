package ca.zakismail.crawlerbackend;

import ca.zakismail.crawlerbackend.crawler.Crawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@SpringBootApplication
public class CrawlerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrawlerBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner crawlerRunner(TaskExecutor executor, Crawler crawler) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				executor.execute(crawler);
			}
		};
	}

}
