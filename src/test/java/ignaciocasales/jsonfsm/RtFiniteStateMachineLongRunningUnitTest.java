package ignaciocasales.jsonfsm;

import ignaciocasales.jsonfsm.automata.FiniteStateMachine;
import ignaciocasales.jsonfsm.automata.RtFiniteStateMachine;
import ignaciocasales.jsonfsm.automata.RtState;
import ignaciocasales.jsonfsm.automata.RtTransition;
import ignaciocasales.jsonfsm.automata.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class RtFiniteStateMachineLongRunningUnitTest {

    @Test
    public void acceptsSimplePair() {
        String json = "{\"key\":\"value\"}";
        FiniteStateMachine machine = JsonFsm.machine();
        for (int i = 0; i < json.length(); i++) {
            machine = machine.switchState(String.valueOf(json.charAt(i)));
        }
        assertTrue(machine.canStop());
    }

    @Test
    public void acceptsMorePairs() {
        String json = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        FiniteStateMachine machine =  JsonFsm.machine();
        for (int i = 0; i < json.length(); i++) {
            machine = machine.switchState(String.valueOf(json.charAt(i)));
        }
        assertTrue(machine.canStop());
    }

    @Test
    public void missingColon() {
        String json = "{\"key\"\"value\"}";
        FiniteStateMachine machine = JsonFsm.machine();
        for (int i = 0; i < json.length(); i++) {
            machine = machine.switchState(String.valueOf(json.charAt(i)));
        }
    }
}