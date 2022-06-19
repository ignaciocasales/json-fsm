package ignaciocasales.jsonfsm;

import ignaciocasales.jsonfsm.automata.FiniteStateMachine;
import ignaciocasales.jsonfsm.automata.RtFiniteStateMachine;
import ignaciocasales.jsonfsm.automata.RtState;
import ignaciocasales.jsonfsm.automata.RtTransition;
import ignaciocasales.jsonfsm.automata.State;

public final class JsonFsm {
    /**
     * Builds a finite state machine to validate a simple
     * Json object.
     *
     * @return
     */
    public static FiniteStateMachine machine() {
        State first = new RtState(); // Opening bracket
        State second = new RtState();
        State third = new RtState();
        State fourth = new RtState();
        State fifth = new RtState();
        State sixth = new RtState();
        State seventh = new RtState();
        State eighth = new RtState(true);

        first.with(new RtTransition("{", second));

        second.with(new RtTransition("\"", third));
        second.with(new RtTransition("}", eighth));
        //Add transitions with chars 0-9 and a-z
        for (int i = 0; i < 26; i++) {
            if (i < 10) {
                third = third.with(new RtTransition(String.valueOf(i), third));
                sixth = sixth.with(new RtTransition(String.valueOf(i), sixth));
            }
            third = third.with(new RtTransition(String.valueOf((char) ('a' + i)), third));
            sixth = sixth.with(new RtTransition(String.valueOf((char) ('a' + i)), sixth));
        }
        third.with(new RtTransition("\"", fourth));
        fourth.with(new RtTransition(":", fifth));
        fifth.with(new RtTransition("\"", sixth));
        sixth.with(new RtTransition("\"", seventh));
        seventh.with(new RtTransition(",", second));
        seventh.with(new RtTransition("}", eighth));
        return new RtFiniteStateMachine(first);
    }
}
