package io.github.ralfspoeth.json.tests;

import io.github.ralfspoeth.json.conv.StandardConversions;
import io.github.ralfspoeth.json.io.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        figi();

        var src = """
                {"a": 5, "b": true, "c": null, "d": "text", "e": [null], "f": {}}
                """;
        var root = JsonReader.readElement(src);
        var abc = StandardConversions.as(ABC.class, root);
        System.out.println(abc);
    }

    static void figi() throws IOException, InterruptedException {
        try (var client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build()) {
            var req = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.openfigi.com")
                            .resolve("v3/mapping/values/micCode")
                    )
                    .build();
            var resp = client.send(req, HttpResponse.BodyHandlers.ofInputStream());
            try (var is = resp.body();
                 var isr = new InputStreamReader(is);
                 var rdr = new JsonReader(isr)
            ) {
                var result = rdr.readElement();
                System.out.println(result);
            }

        }
    }

}
