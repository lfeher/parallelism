package com.lfeher.parallelism.service;

import com.lfeher.parallelism.jpa.model.Data;
import com.lfeher.parallelism.jpa.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static com.lfeher.parallelism.utils.CommonUtil.delay;

@Service
@Slf4j
@RequiredArgsConstructor
public class SzuloService {

    private final DataRepository dataRepository;

    @Async
    public CompletableFuture<Data> getData(Long id) {
        log.debug("> getData > id: {}", id);
        delay(1000);
        return CompletableFuture.completedFuture(dataRepository.findById(id).orElseThrow(() -> new RuntimeException("Data cannot be found!")));
    }
}
