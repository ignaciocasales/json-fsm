# Transitions image

Use [Graphviz](http://www.graphviz.org/)

- Go to this website: http://www.webgraphviz.com/ OR http://magjac.com/graphviz-visual-editor/
- Paste the following code:
- Generate the image

```javascript
digraph finite_state_machine {
    fontname="Helvetica,Arial,sans-serif"
    node [fontname="Helvetica,Arial,sans-serif"]
    edge [fontname="Helvetica,Arial,sans-serif"]
    rankdir=LR;
    node [shape = none]
    0 [label= ""];
    node [shape = doublecircle]; 8;
    node [shape = circle];
    0 -> 1;

    1 -> 2 [label = "{"];

    2 -> 3 [label = "\""];
    2 -> 8 [label = "}"];

    3 -> 3 [label = "[a-z0-9]"];
    3 -> 4 [label = "\""];

    4 -> 5 [label = ":"];

    5 -> 6 [label = "\""];

    6 -> 6 [label = "[a-z0-9]"];
    6 -> 7 [label = "\""];

    7 -> 2 [label = ","];
    7 -> 8 [label = "}"];

    8  [label = "8"];

    5 -> A [label = "{"];

    A -> B [label = "\""];
    A -> 7 [label = "}"];
    B -> B [label = "[a-z0-9]"];
    B -> C [label = "\""];
    C -> D [label = ":"];

    D -> A [label = "{"];
    D -> E [label = "\""];
    D -> x1 [label = "["];

    E -> E [label = "[a-z0-9]"];
    E -> F [label = "\""];
    F -> A [label = ","];

    F -> F [label = "}"];
    F -> 7 [label = "}"];

    5 -> x1 [label = "["];
    x1 -> x2 [label = "\""];
    x2 -> x2 [label = "[a-z0-9]"];
    x2 -> x3 [label = "\""];
    x3 -> x1 [label = ","];
    x3 -> 7 [label = "]"];
    x3 -> F [label = "]"];
}
```
