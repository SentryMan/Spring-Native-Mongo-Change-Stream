package com.jojo.controller;

import static com.jojo.domain.Constants.FULL_DOC;

import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jojo.domain.CrewMate;
import com.jojo.repo.CrewMateRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ControllerClass {
  private final CrewMateRepo repo;

  public ControllerClass(CrewMateRepo repo, ReactiveMongoTemplate template) {
    this.repo = repo;

    template
        .changeStream("Among Us", FULL_DOC, CrewMate.class)
        .doOnNext(
            event -> {
              final var changed = event.getBody();
              final var operation = event.getOperationType();

              if (changed != null)
                System.out.println(
                    "Operation " + operation.getValue() + " Performed on CrewMate: " + changed);
            })
        .mapNotNull(ChangeStreamEvent::getBody)
        .onErrorContinue(
            (ex, o) -> {
              System.err.println("Error processing " + o + " Exception is " + ex);
              ex.printStackTrace();
            })
        .subscribe();
    repo.save(new CrewMate("Red", true)).block();
  }

  @GetMapping("/get")
  Flux<CrewMate> get() {
    return repo.findAll();
  }

  @GetMapping("/save")
  Mono<CrewMate> save(CrewMate crew) {
    return repo.save(crew);
  }
}
