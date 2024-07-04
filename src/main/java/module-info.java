module json.tests.main {
    requires io.github.ralfspoeth.json;
    requires java.net.http;
    exports io.github.ralfspoeth.json.tests;
    opens io.github.ralfspoeth.json.tests to io.github.ralfspoeth.json;
}