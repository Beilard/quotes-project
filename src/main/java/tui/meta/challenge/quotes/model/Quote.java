package tui.meta.challenge.quotes.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "quotes")
public class Quote {

  @Id
  @Field("_id")
  private String id;

  @Field("quoteText")
  private String quoteText;

  @Field("quoteAuthor")
  private String quoteAuthor;

  @Field("quoteGenre")
  private String quoteGenre;

  @Field("__v")
  private Integer version;

}
