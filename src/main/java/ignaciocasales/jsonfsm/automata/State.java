package ignaciocasales.jsonfsm.automata;

import java.util.List;

/**
 * State. Part of a finite state machine.
 */
public interface State {
    /**
     * Add a Transition to this state.
     */
    State with(final Transition tr);

    /**
     * Follow one of the transitions, to get
     * to the next state.
     */
    State transit(final CharSequence c);

    List<Transition> transitions();

    /**
     * Can the automaton stop on this state?
     */
    boolean isFinal();
}
