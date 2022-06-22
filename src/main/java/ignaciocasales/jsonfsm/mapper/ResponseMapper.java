package ignaciocasales.jsonfsm.mapper;

import ignaciocasales.jsonfsm.dto.Response;

public final class ResponseMapper {
    public static Response valid() {
        return Response.builder()
                .result("VALID")
                .message("The JSON is valid")
                .build();
    }

    public static Response invalid(final String c, final String index) {
        return Response.builder()
                .result("INVALID")
                .message(String.format("Invalid character %s at pos %s", c, index))
                .build();
    }

    public static Response invalid() {
        return Response.builder()
                .result("INVALID")
                .message("The JSON is malformed")
                .build();
    }
}
