package tui.meta.challenge.quotes.config;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MongoConfig {

  MongoTemplate mongoTemplate;

  @PostConstruct
  public void initIndexes() {

    mongoTemplate.indexOps("quotes")
        .ensureIndex(
            new Index().on("quoteAuthor", org.springframework.data.domain.Sort.Direction.ASC));
  }

}
