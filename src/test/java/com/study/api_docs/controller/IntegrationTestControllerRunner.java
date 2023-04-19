package com.study.api_docs.controller;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTestControllerRunner {

    @Karate.Test
    Karate testGet() {
        return Karate.run("get").relativeTo(getClass());
    }

    @Karate.Test
    Karate testPost() {
        return Karate.run("post").relativeTo(getClass());
    }

    @Karate.Test
    Karate testPut() {
        return Karate.run("put").relativeTo(getClass());
    }

    @Karate.Test
    Karate testDelete() {
        return Karate.run("delete").relativeTo(getClass());
    }

    @Karate.Test
    Karate testPostGetPutDelete() {
        return Karate.run("post_get_put_delete").relativeTo(getClass());
    }
}
