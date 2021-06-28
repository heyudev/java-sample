package com.heyudev.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author supeng
 * @date 2021/06/24
 */
@RestController
@RequestMapping(value = "/")
public class IndexController {

    @GetMapping(value = "/t1")
    public String t1() {
        return "ok";
    }

    @GetMapping(value = "/t2")
    public Mono t2(int i) {
        switch (i) {
            case 0:
                return Mono.create(stringMonoSink -> stringMonoSink.success("t2 ok"));
            case 1:
                return Mono.error(new Exception("error"));
            case 2:
                return Mono.justOrEmpty("just ok");
            case 3:
                return Mono.justOrEmpty("just ok");
            default:
                return Mono.empty();
        }
    }

    @GetMapping(value = "/t3")
    public Flux<Object> t3(int i) {
        switch (i) {
            case 0:
                return Flux.create(stringFluxSink -> Arrays.asList("1", "2", "3"));
            case 1:
                return Flux.error(new Exception("error"));
            case 2:
                List<String> list = new ArrayList<>();
                list.add("a");
                list.add("b");
                list.add("c");
                return Flux.fromIterable(list);
            case 3:
                return Flux.just("1", "2");
            default:
                return Flux.empty();
        }
    }


}
