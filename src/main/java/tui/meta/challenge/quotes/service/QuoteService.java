package tui.meta.challenge.quotes.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tui.meta.challenge.quotes.dto.QuoteDto;
import tui.meta.challenge.quotes.exception.NotFoundException;
import tui.meta.challenge.quotes.repository.QuoteRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuoteService {

  private QuoteRepository quoteRepository;

  @Cacheable(value = "quotes", key = "#id")
  public QuoteDto getQuoteById(String id) {
    log.info("Fetching quote by ID: {}", id);

    return quoteRepository.findById(id)
        .map(QuoteDto::new)
        .orElseThrow(() -> new NotFoundException("Quote not found with ID: " + id));
  }

  @Cacheable(value = "quotesByAuthor", key = "#author")
  public List<QuoteDto> getQuotesByAuthor(String author) {
    log.info("Fetching quotes by author: {}", author);

    return quoteRepository.findByQuoteAuthor(author).stream()
        .map(QuoteDto::new)
        .toList();
  }

  public List<QuoteDto> getAllQuotes() {
    log.info("Fetching all quotes");

    return quoteRepository.findAll()
        .stream()
        .map(QuoteDto::new)
        .collect(Collectors.toList());
  }

}
