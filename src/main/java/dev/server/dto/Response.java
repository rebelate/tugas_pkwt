package dev.server.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Response extends ResponseEntity<Object> {

    public Response(Object data, HttpStatus status) {
        super(data, status);
    }

    public static Response generate(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        return new Response(map, HttpStatus.OK);
    }

    public static Response generate(String message, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("message", message);
        return new Response(map, HttpStatus.OK);
    }

    public static Response generate(HttpStatus status, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        return new Response(map, status);
    }
}