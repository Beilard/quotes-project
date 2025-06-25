package tui.meta.challenge.quotes.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tui.meta.challenge.quotes.model.Quote;

@Repository
public interface QuoteRepository extends MongoRepository<Quote, String> {

  List<Quote> findByQuoteAuthor(String quoteAuthor);

}
