package edu.woodson.app;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.start();

        while(main.isRunning()){
            main.loop();
        }

        main.stop();

        System.exit(0);
    }

    public void start(){

    }

    public boolean isRunning(){
        return false;
    }

    public void loop(){

    }

    public void stop(){

    }
}
