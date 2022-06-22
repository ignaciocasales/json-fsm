package ignaciocasales.jsonfsm.controller;

import ignaciocasales.jsonfsm.automata.IllegalStateTransitionException;
import ignaciocasales.jsonfsm.dto.Response;
import ignaciocasales.jsonfsm.mapper.ResponseMapper;
import ignaciocasales.jsonfsm.service.FiniteAutomata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JsonFsmController {
    private static final String EMPTY_STRING = "";
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s");
    private final FiniteAutomata finiteAutomata;

    @PostMapping("/test")
    public Response method(@RequestBody final String input) {
        return finiteAutomata.start(sanitize(input));
    }

    @ExceptionHandler({IllegalStateTransitionException.class})
    public Response handleException(final IllegalStateTransitionException ex) {
        return ResponseMapper.invalid(ex.getCharacter(), ex.getIndex());
    }

    private static String sanitize(final String input) {
        return Optional.ofNullable(input)
                .map(s -> WHITESPACE_PATTERN.matcher(s).replaceAll(EMPTY_STRING))
                .map(String::toLowerCase)
                .orElse(EMPTY_STRING);
    }
}
