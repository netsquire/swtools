package reactor;

import fx.JavaFxOK;
import pgsql.PostgreSqlOK;
import utils.RedisOK;

import java.util.Arrays;

public class CheckUpSubs {

    private static final String REPLY = "UP";

    public static void main(String[] args) {
        System.out.println("Sub-dependencies:");
        Arrays.asList(
                new JavaFxOK().replay(REPLY),
                new RedisOK().replay(REPLY),
                new PostgreSqlOK().replay(REPLY))

                .forEach(System.out::println);
    }

}
