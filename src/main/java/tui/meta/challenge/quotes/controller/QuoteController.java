package tui.meta.challenge.quotes.controller;

import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tui.meta.challenge.quotes.dto.QuoteDto;
import tui.meta.challenge.quotes.service.QuoteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quotes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuoteController {
  QuoteService quoteService;

  @GetMapping("/{id}")
  public ResponseEntity<QuoteDto> getQuoteById(@PathVariable String id) {
    var quoteById = quoteService.getQuoteById(id);
    return ResponseEntity.ok(quoteById);
  }

  @GetMapping("/author/{author}")
  public ResponseEntity<List<QuoteDto>> getQuotesByAuthor(@PathVariable String author) {
    var quotes = quoteService.getQuotesByAuthor(author);
    return ResponseEntity.ok(quotes);
  }

  @GetMapping
  public ResponseEntity<List<QuoteDto>> getAllQuotes() {
    var quotes = quoteService.getAllQuotes();
    return ResponseEntity.ok(quotes);
  }

}
