package ignaciocasales.jsonfsm.controller;

import ignaciocasales.jsonfsm.JsonFsm;
import ignaciocasales.jsonfsm.automata.FiniteStateMachine;
import ignaciocasales.jsonfsm.automata.IllegalStateTransitionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.regex.Pattern;

@RestController
public class JsonFsmController {
    private static final String EMPTY_STRING = "";
    private static final String INVALID = "INVALID";
    private static final String VALID = "VALID";
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s");

    @PostMapping("/test")
    public String method(@RequestBody String input) {
        return process(sanitize(input));
    }

    @ExceptionHandler({IllegalStateTransitionException.class})
    public String handleException() {
        return INVALID;
    }

    private static String process(String input) {
        FiniteStateMachine machine = JsonFsm.machine();
        for (int i = 0; i < input.length(); i++) {
            machine = machine.switchState(String.valueOf(input.charAt(i)));
        }
        return machine.canStop() ? VALID : INVALID;
    }

    private static String sanitize(String input) {
        return Optional.ofNullable(input)
                .map(s -> WHITESPACE_PATTERN.matcher(s).replaceAll(EMPTY_STRING))
                .map(String::toLowerCase)
                .orElse(EMPTY_STRING);
    }
}
