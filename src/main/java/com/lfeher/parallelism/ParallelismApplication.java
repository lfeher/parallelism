package com.lfeher.parallelism;

import com.lfeher.parallelism.jpa.model.Data;
import com.lfeher.parallelism.jpa.repository.DataRepository;
import com.lfeher.parallelism.service.DataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication
public class ParallelismApplication {

    @Bean
    CommandLineRunner dummyCLR(DataRepository dataRepository) {
        return args -> {
            Stream.of("Anyuci", "Apuci", "Aniko", "Tomi", "Lacika")
                    .forEach(name -> dataRepository.save(new Data(name)));

            //dataRepository.findAll().forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ParallelismApplication.class, args);
    }

}

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
@Slf4j
class DataController {

    private final DataRepository dataRepository;
    private final DataService dataService;

    @GetMapping
    public Collection<Data> findAll() {
        log.debug("> findAll");
        return dataRepository.findAll();
    }

    @GetMapping(path = "/find-by-name")
    public Collection<Data> findByName(String name) {
        log.debug("> findByName > name: {}", name);
        return dataRepository.findByName(name);
    }

    @GetMapping(path = "/getSzulok")
    public String getSzulok() {
        log.debug("> getSzulok");
        return dataService.getSzulok_async();
    }
}

@Configuration
@EnableAsync
class AsyncConfiguration {}
