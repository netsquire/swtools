package reactor;

import java.util.ArrayList;
import java.util.List;

public class Ping {

    public static void main(String[] args) {
        List<String> answer = new ArrayList<>();
        answer.add(fx.Pong.replay("init"));
        answer.add(up.Pong.replay("init"));
        System.out.println(answer);
    }

}
