package com.jojo;

import static org.springframework.boot.WebApplicationType.REACTIVE;
import static org.springframework.nativex.hint.TypeAccess.PUBLIC_CONSTRUCTORS;
import static org.springframework.nativex.hint.TypeAccess.PUBLIC_METHODS;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.function.client.ClientHttpConnectorAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.TypeHint;

import com.jojo.domain.CrewMate;
import com.mongodb.client.model.changestream.ChangeStreamDocument;

import reactor.core.publisher.Hooks;

// set native image reflective access
@NativeHint(
    types =
        @TypeHint(
            access = {PUBLIC_CONSTRUCTORS, PUBLIC_METHODS},
            types = {ChangeStreamDocument.class, CrewMate.class}))
@SpringBootApplication(
    exclude = {
      AopAutoConfiguration.class,
      CacheAutoConfiguration.class,
      WebClientAutoConfiguration.class,
      TransactionAutoConfiguration.class,
      RestTemplateAutoConfiguration.class,
      MessageSourceAutoConfiguration.class,
      TaskExecutionAutoConfiguration.class,
      TaskSchedulingAutoConfiguration.class,
      SecurityFilterAutoConfiguration.class,
      UserDetailsServiceAutoConfiguration.class,
      ClientHttpConnectorAutoConfiguration.class,
      OAuth2ResourceServerAutoConfiguration.class,
      ApplicationAvailabilityAutoConfiguration.class,
      ReactiveUserDetailsServiceAutoConfiguration.class,
    })
public class ChangeServerDemo {

  public static void main(String[] args) {
    Hooks.onErrorDropped(t -> {});
    new SpringApplicationBuilder(ChangeServerDemo.class).web(REACTIVE).build(args).run(args);
  }
}
