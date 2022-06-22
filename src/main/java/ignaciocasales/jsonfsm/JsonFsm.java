package ignaciocasales.jsonfsm;

import ignaciocasales.jsonfsm.automata.State;

import java.util.function.BiFunction;

public final class JsonFsm {
    private static final BiFunction<String, String, Boolean> eq = (s1, s2) -> s1.equalsIgnoreCase(String.valueOf(s2));

    /**
     * Builds a finite state machine to validate a simple
     * Json object.
     */
    public static State parentObject() {
        final State first = new State();
        final State second = new State();
        final State third = new State();
        final State fourth = new State();
        final State fifth = new State();
        final State sixth = new State();
        final State seventh = new State();
        final State eighth = new State(true);

        first.with(s -> {
            if (eq.apply(s, "{")) {
                return second;
            }
            return null;
        });

        second.with(s -> {
            if (eq.apply(s, "\"")) {
                return third;
            }
            return null;
        });
        second.with(s ->
        {
            if (eq.apply(s, "}")) {
                return eighth;
            }
            return null;
        });

        //Add transitions with chars 0-9 and a-z
        for (int i = 0; i < 26; i++) {
            if (i < 10) {
                final String si = String.valueOf(i);
                third.with(s ->
                {
                    if (eq.apply(s, si)) {
                        return third;
                    }
                    return null;
                });
                sixth.with(s ->
                {
                    if (eq.apply(s, si)) {
                        return sixth;
                    }
                    return null;
                });
            }
            final String c = String.valueOf((char) ('a' + i));
            third.with(s ->
            {
                if (eq.apply(s, c)) {
                    return third;
                }
                return null;
            });
            sixth.with(s ->
            {
                if (eq.apply(s, c)) {
                    return sixth;
                }
                return null;
            });
        }

        third.with(s -> {
            if (eq.apply(s, "\"")) {
                return fourth;
            }
            return null;
        });

        fourth.with(s ->
        {
            if (eq.apply(s, ":")) {
                return fifth;
            }
            return null;
        });

        fifth.with(s -> {
            if (eq.apply(s, "\"")) {
                return sixth;
            }
            return null;
        });
        fifth.with(s -> {
            if (eq.apply(s, "{")) {
                return innerObject(seventh);
            }
            return null;
        });

        sixth.with(s -> {
            if (eq.apply(s, "\"")) {
                return seventh;
            }
            return null;
        });

        seventh.with(s -> {
            if (eq.apply(s, ",")) {
                return second;
            }
            return null;
        });

        seventh.with(s -> {
            if (eq.apply(s, "}")) {
                return eighth;
            }
            return null;
        });

        return first;
    }

    public static State innerObject(final State s7) {
        final State second = new State();
        final State third = new State();
        final State fourth = new State();
        final State fifth = new State();
        final State sixth = new State();
        final State seventh = new State();

        second.with(s -> {
            if (eq.apply(s, "\"")) {
                return third;
            }
            return null;
        });
        second.with(s ->
        {
            if (eq.apply(s, "}")) {
                return s7;
            }
            return null;
        });

        //Add transitions with chars 0-9 and a-z
        for (int i = 0; i < 26; i++) {
            if (i < 10) {
                final String si = String.valueOf(i);
                third.with(s ->
                {
                    if (eq.apply(s, si)) {
                        return third;
                    }
                    return null;
                });
                sixth.with(s ->
                {
                    if (eq.apply(s, si)) {
                        return sixth;
                    }
                    return null;
                });
            }
            final String c = String.valueOf((char) ('a' + i));
            third.with(s ->
            {
                if (eq.apply(s, c)) {
                    return third;
                }
                return null;
            });
            sixth.with(s ->
            {
                if (eq.apply(s, c)) {
                    return sixth;
                }
                return null;
            });
        }

        third.with(s -> {
            if (eq.apply(s, "\"")) {
                return fourth;
            }
            return null;
        });

        fourth.with(s ->
        {
            if (eq.apply(s, ":")) {
                return fifth;
            }
            return null;
        });

        fifth.with(s -> {
            if (eq.apply(s, "\"")) {
                return sixth;
            }
            return null;
        });
        fifth.with(s -> {
            if (eq.apply(s, "{")) {
                return innerObject(seventh);
            }
            return null;
        });

        sixth.with(s -> {
            if (eq.apply(s, "\"")) {
                return seventh;
            }
            return null;
        });

        seventh.with(s -> {
            if (eq.apply(s, ",")) {
                return second;
            }
            return null;
        });

        seventh.with(s -> {
            if (eq.apply(s, "}")) {
                return s7;
            }
            return null;
        });

        return second;
    }
}
