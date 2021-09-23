package com.lfeher.parallelism.service;

import com.lfeher.parallelism.jpa.model.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static com.lfeher.parallelism.utils.CommonUtil.startTimer;
import static com.lfeher.parallelism.utils.CommonUtil.timeTaken;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataService {

    private final SzuloService szuloService;

    /*public String getSzulok() {
        log.debug("> getSzulok");
        startTimer();
        Data dataOne = szuloService.getData(1L);
        Data dataTwo = szuloService.getData(2L);

        timeTaken();
        return dataOne.getName() + " " + dataTwo.getName();
    }*/

    public String getSzulok_async() {
        log.debug("> getSzulok_async");
        startTimer();

        List<CompletableFuture<Data>> collect = LongStream.of(1, 2).boxed().map(szuloService::getData).collect(Collectors.toList());

        List<Data> collect1 = collect.stream().map(CompletableFuture::join).collect(Collectors.toList());

        String join = collect1.stream().map(Data::getName).collect(Collectors.joining(" "));
        //CompletableFuture<Data> dataOne = szuloService.getData(1L);
        //CompletableFuture<Data> dataTwo = szuloService.getData(2L);
        //String join = dataOne.thenCombine(dataTwo, (one, two) -> one.getName() + " " + two.getName()).join();

        timeTaken();
        return join;
    }
}
