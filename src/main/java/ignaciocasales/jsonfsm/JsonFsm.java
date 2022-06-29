package ignaciocasales.jsonfsm;

import ignaciocasales.jsonfsm.automata.State;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * This class represents the states and transitions for the fsm.
 */
@SuppressWarnings("DuplicatedCode")
public final class JsonFsm {
    private static final BiFunction<String, String, Boolean> eq = (s1, s2) -> s1.equalsIgnoreCase(String.valueOf(s2));
    public static final String OPENING_BRACKET_CHAR = "{";
    public static final String CLOSING_BRACKET_CHAR = "}";
    public static final String QUOTE_CHAR = "\"";
    public static final String COLON_CHAR = ":";
    public static final String COMMA_CHAR = ",";
    public static final String OPENING_SQUARE_BRACKET = "[";
    public static final String CLOSING_SQUARE_BRACKET = "]";

    public static State mainObject() {
        final State first = new State(); // 1
        final State second = new State(); // 2
        final State third = new State(); // 3
        final State fourth = new State(); // 4
        final State fifth = new State(); // 5
        final State sixth = new State(); // 6
        final State seventh = new State(); // 7
        final State eighth = new State(true); // 8; Accepted state.

        first.with(eqTransition(OPENING_BRACKET_CHAR, () -> second));

        second.with(eqTransition(QUOTE_CHAR, () -> third))
                .with(eqTransition(CLOSING_BRACKET_CHAR, () -> eighth));

        // Add transitions for [0-9]
        addZeroToNineSelfTransitions(third, sixth);
        // Add transitions for [a-z]
        addAtoZSelfTransitions(third, sixth);

        third.with(eqTransition(QUOTE_CHAR, () -> fourth));

        fourth.with(eqTransition(COLON_CHAR, () -> fifth));

        fifth.with(eqTransition(QUOTE_CHAR, () -> sixth))
                .with(eqTransition(OPENING_BRACKET_CHAR, () -> innerObject(seventh)))
                .with(eqTransition(OPENING_SQUARE_BRACKET, () -> list(seventh)));

        sixth.with(eqTransition(QUOTE_CHAR, () -> seventh));

        seventh.with(eqTransition(COMMA_CHAR, () -> second))
                .with(eqTransition(CLOSING_BRACKET_CHAR, () -> eighth));

        return first; // Starting state.
    }

    private static State innerObject(final State exitState) {
        final State aState = new State(); // A
        final State bState = new State(); // B
        final State cState = new State(); // C
        final State dState = new State(); // D
        final State eState = new State(); // E
        final State fState = new State(); // F

        aState.with(eqTransition(QUOTE_CHAR, () -> bState))
                .with(eqTransition(CLOSING_BRACKET_CHAR, () -> exitState));

        // Add transitions for [0-9]
        addZeroToNineSelfTransitions(bState, eState);
        // Add transitions for [a-z]
        addAtoZSelfTransitions(bState, eState);

        bState.with(eqTransition(QUOTE_CHAR, () -> cState));

        cState.with(eqTransition(COLON_CHAR, () -> dState));

        dState.with(eqTransition(QUOTE_CHAR, () -> eState))
                .with(eqTransition(OPENING_BRACKET_CHAR, () -> innerObject(fState)))
                .with(eqTransition(OPENING_SQUARE_BRACKET, () -> list(fState)));

        eState.with(eqTransition(QUOTE_CHAR, () -> fState));

        fState.with(eqTransition(COMMA_CHAR, () -> aState))
                .with(eqTransition(CLOSING_BRACKET_CHAR, () -> exitState));

        return aState;
    }

    private static State list(final State exitState) {
        final State x1State = new State(); // x1
        final State x2State = new State(); // x2
        final State x3State = new State(); // x3

        x1State.with(eqTransition(QUOTE_CHAR, () -> x2State))
                .with(eqTransition(CLOSING_SQUARE_BRACKET, () -> exitState));

        // Add transitions for [0-9]
        addZeroToNineSelfTransitions(x2State);
        // Add transitions for [a-z]
        addAtoZSelfTransitions(x2State);

        x2State.with(eqTransition(QUOTE_CHAR, () -> x3State));

        x3State.with(eqTransition(COMMA_CHAR, () -> x1State));

        x3State.with(eqTransition(CLOSING_SQUARE_BRACKET, () -> exitState));

        return x1State;
    }

    private static Function<String, State> eqTransition(final String c, final Supplier<State> stateSupplier) {
        return s -> {
            if (eq.apply(s, String.valueOf(c))) {
                return stateSupplier.get();
            }
            return null;
        };
    }

    private static void addZeroToNineSelfTransitions(final State... state) {
        IntStream.range(0, 10)
                .boxed()
                .map(String::valueOf)
                .forEach(value -> {
                    for (State s : state) {
                        s.with(eqTransition(value, () -> s));
                    }
                });
    }

    private static void addAtoZSelfTransitions(final State... state) {
        IntStream.range(0, 26)
                .boxed()
                .map(i -> String.valueOf((char) ('a' + i)))
                .forEach(value -> {
                    for (State s : state) {
                        s.with(eqTransition(value, () -> s));
                    }
                });
    }
}
