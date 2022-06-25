package com.in28minutes.unittesting.unittesting.spike;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonPathTest {

    @DisplayName("jsonPath github에 표현식 존재 함")
    @Test
    public void learning() {
        String responseFromService = "[" +
                "{ \"id\": 5001, \"type\": \"None\" }," +
                "{ \"id\": 5002, \"type\": \"Glazed\" }," +
                "{ \"id\": 5005, \"type\": \"Sugar\" }," +
                "{ \"id\": 5007, \"type\": \"Powdered Sugar\" }," +
                "{ \"id\": 5006, \"type\": \"Chocolate with Sprinkles\" }," +
                "{ \"id\": 5003, \"type\": \"Chocolate\" }," +
                "{ \"id\": 5004, \"type\": \"Maple\" }" +
                "]";

        DocumentContext context = JsonPath.parse(responseFromService);
        int length = context.read("$.length()");
        assertThat(length).isEqualTo(7);

        // list of id
        List<Integer> ids = context.read("$..id");
        System.out.println(ids.toString());

        assertThat(ids).containsExactly(5001, 5002, 5005, 5007, 5006, 5003, 5004);

        // 인덱스 1번
        System.out.println(context.read("$.[1]").toString());
        System.out.println(context.read("$.[1:3]").toString()); // 범위인듯 start ~ end - 1 인덱스 까지
        System.out.println(context.read("$.[?(@.type=='Sugar')]").toString()); // type 속성 값이 Sugar 인 걸 읽음
    }
}
