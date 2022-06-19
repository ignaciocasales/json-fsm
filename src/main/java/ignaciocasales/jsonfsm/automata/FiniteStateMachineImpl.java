package ignaciocasales.jsonfsm.automata;

/**
 * Default implementation of a finite state machine.
 * This class is immutable and thread-safe.
 */
public final class FiniteStateMachineImpl implements FiniteStateMachine {

    /**
     * Current state.
     */
    private final State current;

    /**
     * Ctor.
     *
     * @param initial Initial state of this machine.
     */
    public FiniteStateMachineImpl(final State initial) {
        this.current = initial;
    }

    public FiniteStateMachine switchState(final CharSequence c) {
        return new FiniteStateMachineImpl(this.current.transit(c));
    }

    public boolean canStop() {
        return this.current.isFinal();
    }
}