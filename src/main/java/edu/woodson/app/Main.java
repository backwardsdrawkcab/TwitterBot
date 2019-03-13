package edu.woodson.app;

public class Main {
    private static final TwitterBot twitterBot = new TwitterBot();

    public static void main(String[] args) {
        twitterBot.start();

        while(twitterBot.isRunning()){
            twitterBot.loop();
        }

        twitterBot.stop();

        System.exit(0);
    }
}
