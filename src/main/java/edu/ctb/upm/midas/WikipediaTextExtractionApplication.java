package edu.ctb.upm.midas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class WikipediaTextExtractionApplication {

	public static void main(String[] args) {
		SpringApplication.run(WikipediaTextExtractionApplication.class, args);
		setup();
	}


	public static void setup(){
		System.out.println(
				"    ____  _________ _   ______________   _______  _____________________  ___\n" +
				"   / __ \\/  _/ ___// | / / ____/_  __/  / ___/\\ \\/ / ___/_  __/ ____/  |/  /\n" +
				"  / / / // / \\__ \\/  |/ / __/   / /     \\__ \\  \\  /\\__ \\ / / / __/ / /|_/ / \n" +
				" / /_/ // / ___/ / /|  / /___  / /     ___/ /  / /___/ // / / /___/ /  / /  \n" +
				"/_____/___//____/_/ |_/_____/ /_/     /____/  /_//____//_/ /_____/_/  /_/   ");
	}
}
