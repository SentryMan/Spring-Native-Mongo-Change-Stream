package com.jojo.domain;

import org.springframework.data.mongodb.core.ChangeStreamOptions;

public interface Constants {

  ChangeStreamOptions FULL_DOC = ChangeStreamOptions.builder().returnFullDocumentOnUpdate().build();
}
