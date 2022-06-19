package ignaciocasales.jsonfsm.automata;

public class IllegalStateTransitionException extends RuntimeException {
    public IllegalStateTransitionException(String s) {
        super(s);
    }
}
