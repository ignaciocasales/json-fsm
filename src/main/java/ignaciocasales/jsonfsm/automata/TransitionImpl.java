package ignaciocasales.jsonfsm.automata;

/**
 * Transition in finite state machine.
 */
public final class TransitionImpl implements Transition {

    private final String rule;
    private final State next;
    
    /**
     * @param rule Rule that a character has to meet
     *  in order to get to the next state.
     * @param next Next state.
     */
    public TransitionImpl(String rule, State next) {
        this.rule = rule;
        this.next = next;
    }
    
    public State state() {
        return this.next;
    }

    public boolean isPossible(CharSequence c) {
        return this.rule.equalsIgnoreCase(String.valueOf(c));
    }

}