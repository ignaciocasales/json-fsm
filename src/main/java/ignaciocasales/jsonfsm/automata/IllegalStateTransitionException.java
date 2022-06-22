package ignaciocasales.jsonfsm.automata;

import lombok.Getter;

@Getter
public class IllegalStateTransitionException extends RuntimeException {
    private final String character;
    private final String index;

    public IllegalStateTransitionException(final String character, final String index) {
        super(("Input not accepted: " + character));
        this.character = character;
        this.index = index;
    }
}
