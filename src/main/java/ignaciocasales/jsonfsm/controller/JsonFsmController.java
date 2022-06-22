package ignaciocasales.jsonfsm.controller;

import ignaciocasales.jsonfsm.JsonFsm;
import ignaciocasales.jsonfsm.automata.IllegalStateTransitionException;
import ignaciocasales.jsonfsm.automata.State;
import ignaciocasales.jsonfsm.dto.Response;
import ignaciocasales.jsonfsm.mapper.ResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@RestController
public class JsonFsmController {
    private static final String EMPTY_STRING = "";
    private static final String INVALID = "INVALID";
    private static final String VALID = "VALID";
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s");

    @PostMapping("/test")
    public Response method(@RequestBody final String input) {
        return process(sanitize(input));
    }

    @ExceptionHandler({IllegalStateTransitionException.class})
    public Response handleException(final IllegalStateTransitionException ex) {
        return ResponseMapper.invalid(ex.getCharacter(), ex.getIndex());
    }

    private static Response process(final String input) {
        State state = JsonFsm.parentObject();
        for (int i = 0; i < input.length(); i++) {
            final String c = String.valueOf(input.charAt(i));
            final String index = String.valueOf(i);
            state = state.getTransitions().stream()
                    .map(transition -> transition.apply(c))
                    .filter(Objects::nonNull)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateTransitionException(c, index));
        }
        return state.isFinal() ? ResponseMapper.valid() : ResponseMapper.invalid();
    }

    private static String sanitize(final String input) {
        return Optional.ofNullable(input)
                .map(s -> WHITESPACE_PATTERN.matcher(s).replaceAll(EMPTY_STRING))
                .map(String::toLowerCase)
                .orElse(EMPTY_STRING);
    }
}
