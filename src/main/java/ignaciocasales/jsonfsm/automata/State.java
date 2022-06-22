package ignaciocasales.jsonfsm.automata;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * State in a finite state machine.
 */
@Getter
public final class State {
    private final List<Function<String, State>> transitions;
    private final boolean isFinal;

    public State() {
        this(false);
    }

    public State(final boolean isFinal) {
        this.transitions = new ArrayList<>();
        this.isFinal = isFinal;
    }

    public State with(Function<String, State> tr) {
        this.transitions.add(tr);
        return this;
    }
}
