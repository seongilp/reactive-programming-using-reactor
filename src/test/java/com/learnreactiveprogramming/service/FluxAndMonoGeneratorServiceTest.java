package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();
        StepVerifier.create(namesFlux)
                .expectNext("alex")
                .expectNext("ben")
                .expectNext("chloe")
                .verifyComplete();
    }

    @Test
    void namesFlux_map() {
        int stringLength = 3;
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_map(stringLength);
        StepVerifier.create(namesFlux)
                .expectNext("4-ALEX", "5-CHLOE")
                .verifyComplete();
    }

    @Test
    void namesFlux_immutablity() {
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_immutablity();
        StepVerifier.create(namesFlux)
                .expectNext("alex")
                .expectNext("ben")
                .expectNext("chloe")
                .verifyComplete();
    }

    @Test
    void namesFlux_flatmap() {
        int stringLength = 3;
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_flatmap(stringLength);
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();
    }

    @Test
    void namesFlux_flatmap_async() {
        int stringLength = 3;
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_flatmap_async(stringLength);
        StepVerifier.create(namesFlux)
//                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void namesFlux_concatmap() {
        int stringLength = 3;
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_concatmap(stringLength);
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
//                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void namesMono_flatMap() {
        int stringLength = 3;
        var value = fluxAndMonoGeneratorService.namesMono_flatMap(stringLength);
        StepVerifier.create(value)
                .expectNext(List.of("A","L","E","X"))
                .verifyComplete();
    }

    @Test
    void namesMono_flatMapMany() {
        int stringLength = 3;
        var value = fluxAndMonoGeneratorService.namesMono_flatMapMany(stringLength);
        StepVerifier.create(value)
                .expectNext("A","L","E","X")
                .verifyComplete();
    }

    @Test
    void namesFlux_transform() {
        int stringLength = 3;
        var value = fluxAndMonoGeneratorService.namesFlux_transform(stringLength);
        StepVerifier.create(value)
                .expectNext("A","L","E","X","C","H","L","O","E")
                .verifyComplete();
    }

    @Test
    void namesFlux_transform_1() {
        int stringLength = 6;
        var value = fluxAndMonoGeneratorService.namesFlux_transform(stringLength);
        StepVerifier.create(value)
//                .expectNext("A","L","E","X","C","H","L","O","E")
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void namesFlux_transform_switchifEmpty() {
        int stringLength = 6;
        var value = fluxAndMonoGeneratorService.namesFlux_transform_switchifEmpty(stringLength);
        StepVerifier.create(value)
//                .expectNext("A","L","E","X","C","H","L","O","E")
                .expectNext("D","E","F","A","U","L","T")
                .verifyComplete();
    }

    @Test
    void explore_concat() {
        var value = fluxAndMonoGeneratorService.explore_concat();
        StepVerifier.create(value)
                .expectNext("A","B","C","D","E","F")
                .verifyComplete();
    }

    @Test
    void explore_concatWith() {
        var value = fluxAndMonoGeneratorService.explore_concat();
        StepVerifier.create(value)
                .expectNext("A","B","C","D","E","F")
                .verifyComplete();
    }

    @Test
    void explore_merge() {
        var value = fluxAndMonoGeneratorService.explore_merge();
        StepVerifier.create(value)
                .expectNext("A","D","B","E","C","F")
//                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    void explore_mergeWith() {
        var value = fluxAndMonoGeneratorService.explore_merge();
        StepVerifier.create(value)
                .expectNext("A","D","B","E","C","F")
//                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    void explore_mergeSequential() {
        var value = fluxAndMonoGeneratorService.explore_mergeSequential();
        StepVerifier.create(value)
                .expectNext("A","B","C","D","E","F")
                .verifyComplete();
    }

    @Test
    void explore_zip() {
        var value = fluxAndMonoGeneratorService.explore_zip();
        StepVerifier.create(value)
                .expectNext("AD","BE","CF")
                .verifyComplete();
    }

    @Test
    void explore_zip_1() {
        var value = fluxAndMonoGeneratorService.explore_zip_1();
        StepVerifier.create(value)
                .expectNext("AD14","BE25","CF36")
                .verifyComplete();
    }
}