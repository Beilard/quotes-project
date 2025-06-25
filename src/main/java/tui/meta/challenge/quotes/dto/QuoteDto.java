package tui.meta.challenge.quotes.dto;

import tui.meta.challenge.quotes.model.Quote;

public record QuoteDto(String id, String quoteText, String quoteAuthor, String quoteGenre) {
  public QuoteDto(Quote quote) {
    this(
        quote.getId(),
        quote.getQuoteText(),
        quote.getQuoteAuthor(),
        quote.getQuoteGenre()
    );
  }
}
