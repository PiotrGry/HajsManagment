package tools;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class DbCredentialsProvider {

    public  Map<String,String> getDbCredential() {
        int dbUrl = 0;
        int userName = 1;
        int dbPass = 2;

        Map<String, String> credentials = null;
        try {
            credentials = parseCredentialsFromFile(dbUrl, userName, dbPass);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    private Map<String, String> parseCredentialsFromFile(int dbUrl, int userName, int dbPass) throws URISyntaxException, IOException {
        Map<String,String> map = new HashMap<>();

        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("file/pass")).toURI());

        Stream<String> lines = Files.lines(path);
        String[]data = lines.toArray(String[]::new);
        map.put("dbURL",data[dbUrl]);
        map.put("userName",data[userName]);
        map.put("dbPass",data[dbPass]);
        lines.close();
        return map;
    }
}
