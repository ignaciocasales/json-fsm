package ignaciocasales.jsonfsm;

import ignaciocasales.jsonfsm.automata.FiniteStateMachine;
import ignaciocasales.jsonfsm.automata.FiniteStateMachineImpl;
import ignaciocasales.jsonfsm.automata.State;
import ignaciocasales.jsonfsm.automata.StateImpl;
import ignaciocasales.jsonfsm.automata.TransitionImpl;

public final class JsonFsm {
    /**
     * Builds a finite state machine to validate a simple
     * Json object.
     */
    public static FiniteStateMachine machine() {
        State first = new StateImpl(); // Opening bracket
        State second = new StateImpl();
        State third = new StateImpl();
        State fourth = new StateImpl();
        State fifth = new StateImpl();
        State sixth = new StateImpl();
        State seventh = new StateImpl();
        State eighth = new StateImpl(true);

        first.with(new TransitionImpl("{", second));

        second.with(new TransitionImpl("\"", third));
        second.with(new TransitionImpl("}", eighth));
        //Add transitions with chars 0-9 and a-z
        for (int i = 0; i < 26; i++) {
            if (i < 10) {
                third = third.with(new TransitionImpl(String.valueOf(i), third));
                sixth = sixth.with(new TransitionImpl(String.valueOf(i), sixth));
            }
            third = third.with(new TransitionImpl(String.valueOf((char) ('a' + i)), third));
            sixth = sixth.with(new TransitionImpl(String.valueOf((char) ('a' + i)), sixth));
        }
        third.with(new TransitionImpl("\"", fourth));
        fourth.with(new TransitionImpl(":", fifth));
        fifth.with(new TransitionImpl("\"", sixth));
        sixth.with(new TransitionImpl("\"", seventh));
        seventh.with(new TransitionImpl(",", second));
        seventh.with(new TransitionImpl("}", eighth));
        return new FiniteStateMachineImpl(first);
    }
}
