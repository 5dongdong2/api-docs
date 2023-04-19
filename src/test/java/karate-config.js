function fn() {
    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);
    // karate.configure('abortSuiteOnFailure', true);
    var port = '8080';
    var protocol = 'http';
    var config = {baseUrl: protocol + '://localhost:' + port + '/test/api'};
    return config;
}
