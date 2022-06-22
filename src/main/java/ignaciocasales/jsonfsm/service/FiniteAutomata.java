package ignaciocasales.jsonfsm.service;

import ignaciocasales.jsonfsm.JsonFsm;
import ignaciocasales.jsonfsm.automata.IllegalStateTransitionException;
import ignaciocasales.jsonfsm.automata.State;
import ignaciocasales.jsonfsm.dto.Response;
import ignaciocasales.jsonfsm.mapper.ResponseMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FiniteAutomata {
    public Response start(final String input) {
        State state = JsonFsm.mainObject();
        for (int i = 0; i < input.length(); i++) {
            final String c = String.valueOf(input.charAt(i));
            final String index = String.valueOf(i);
            state = state.getTransitions().stream()
                    .map(transition -> transition.apply(c))
                    .filter(Objects::nonNull)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateTransitionException(c, index));
        }
        return state.isFinal() ? ResponseMapper.valid() : ResponseMapper.invalid();
    }
}
