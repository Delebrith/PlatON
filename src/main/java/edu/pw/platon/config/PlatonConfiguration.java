package edu.pw.platon.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.DefaultHttpLogFormatter;
import org.zalando.logbook.DefaultHttpLogWriter;
import org.zalando.logbook.Logbook;

@Configuration
@Slf4j
public class PlatonConfiguration {

    @Bean
    public Logbook logbook() {
        return Logbook.builder()
                .writer(new DefaultHttpLogWriter(log, DefaultHttpLogWriter.Level.INFO))
                .formatter(new DefaultHttpLogFormatter())
                .build();
    }

}
