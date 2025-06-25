package tui.meta.challenge.quotes.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tui.meta.challenge.quotes.dto.QuoteDto;
import tui.meta.challenge.quotes.exception.NotFoundException;
import tui.meta.challenge.quotes.model.Quote;
import tui.meta.challenge.quotes.repository.QuoteRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class QuoteServiceTest {
    @Mock
    private QuoteRepository quoteRepository;

    @InjectMocks
    private QuoteService quoteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getQuoteById returns QuoteDto when found")
    void getQuoteById_ReturnsQuoteDto() {
        Quote quote = new Quote();
        quote.setId("1");
        quote.setQuoteText("Test quote");
        quote.setQuoteAuthor("Author");
        when(quoteRepository.findById("1")).thenReturn(Optional.of(quote));

        QuoteDto result = quoteService.getQuoteById("1");
        assertThat(result.id()).isEqualTo("1");
        assertThat(result.quoteText()).isEqualTo("Test quote");
        assertThat(result.quoteAuthor()).isEqualTo("Author");
    }

    @Test
    @DisplayName("getQuoteById throws NotFoundException when not found")
    void getQuoteById_NotFound() {
        when(quoteRepository.findById("2")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> quoteService.getQuoteById("2"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Quote not found with ID: 2");
    }

    @Test
    @DisplayName("getQuotesByAuthor returns list of QuoteDto")
    void getQuotesByAuthor_ReturnsList() {
        Quote quote1 = new Quote();
        quote1.setId("1");
        quote1.setQuoteText("Quote 1");
        quote1.setQuoteAuthor("Author");
        Quote quote2 = new Quote();
        quote2.setId("2");
        quote2.setQuoteText("Quote 2");
        quote2.setQuoteAuthor("Author");
        when(quoteRepository.findByQuoteAuthor("Author")).thenReturn(List.of(quote1, quote2));

        List<QuoteDto> result = quoteService.getQuotesByAuthor("Author");
        assertThat(result).hasSize(2);
        assertThat(result.get(0).id()).isEqualTo("1");
        assertThat(result.get(1).id()).isEqualTo("2");
    }

    @Test
    @DisplayName("getAllQuotes returns all QuoteDto")
    void getAllQuotes_ReturnsAll() {
        Quote quote1 = new Quote();
        quote1.setId("1");
        quote1.setQuoteText("Quote 1");
        quote1.setQuoteAuthor("Author 1");

        Quote quote2 = new Quote();
        quote2.setId("2");
        quote2.setQuoteText("Quote 2");
        quote2.setQuoteAuthor("Author 2");
        when(quoteRepository.findAll()).thenReturn(List.of(quote1, quote2));

        List<QuoteDto> result = quoteService.getAllQuotes();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).id()).isEqualTo("1");
        assertThat(result.get(1).id()).isEqualTo("2");
    }
}
