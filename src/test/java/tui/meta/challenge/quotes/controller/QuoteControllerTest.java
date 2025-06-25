package tui.meta.challenge.quotes.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tui.meta.challenge.quotes.dto.QuoteDto;
import tui.meta.challenge.quotes.service.QuoteService;

@WebMvcTest(QuoteController.class)
class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteService quoteService;

    @Test
    @DisplayName("GET /api/quotes/{id} returns a quote by id")
    void getQuoteById_ReturnsQuote() throws Exception {
        QuoteDto quote = new QuoteDto("1", "Test quote", "Author", "Genre");


        Mockito.when(quoteService.getQuoteById("1")).thenReturn(quote);

        mockMvc.perform(get("/api/quotes/1"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$.id").value("1"),
                    jsonPath("$.quoteText").value("Test quote"),
                    jsonPath("$.quoteAuthor").value("Author")
                );
    }

    @Test
    @DisplayName("GET /api/quotes/author/{author} returns quotes by author")
    void getQuotesByAuthor_ReturnsQuotes() throws Exception {
        QuoteDto quote1 = new QuoteDto("1", "Quote 1", "Author", "Genre1");
        QuoteDto quote2 = new QuoteDto("2", "Quote 2", "Author", "Genre2");

        Mockito.when(quoteService.getQuotesByAuthor("Author")).thenReturn(List.of(quote1, quote2));

        mockMvc.perform(get("/api/quotes/author/Author"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$[0].id").value("1"),
                    jsonPath("$[0].quoteText").value("Quote 1"),
                    jsonPath("$[0].quoteAuthor").value("Author"),
                    jsonPath("$[1].id").value("2"),
                    jsonPath("$[1].quoteText").value("Quote 2"),
                    jsonPath("$[1].quoteAuthor").value("Author")
                );
    }

    @Test
    @DisplayName("GET /api/quotes returns all quotes")
    void getAllQuotes_ReturnsAllQuotes() throws Exception {
        QuoteDto quote1 = new QuoteDto("1", "Quote 1", "Author", "Genre1");
        QuoteDto quote2 = new QuoteDto("2", "Quote 2", "Author", "Genre2");

        Mockito.when(quoteService.getAllQuotes()).thenReturn(List.of(quote1, quote2));

        mockMvc.perform(get("/api/quotes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[1].id").value("2"));
    }
}
