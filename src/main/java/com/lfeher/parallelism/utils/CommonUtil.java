package com.lfeher.parallelism.utils;

import org.apache.commons.lang3.time.StopWatch;

import static java.lang.Thread.sleep;

public final class CommonUtil {

    private CommonUtil() {}

    public static StopWatch stopWatch = new StopWatch();

    public static void delay(long delayMilliSeconds)  {
        try{
            sleep(delayMilliSeconds);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static String transForm(String s) {
        CommonUtil.delay(500);
        return s.toUpperCase();
    }

    public static void startTimer(){
        stopWatch.start();
    }

    public static void timeTaken(){
        stopWatch.stop();
        log("Total Time Taken : " + stopWatch.getTime());
    }

    public static void stopWatchReset(){
        stopWatch.reset();
    }

    public static  int noOfCores(){
        return Runtime.getRuntime().availableProcessors();
    }

    public static void log(String message){

        System.out.println("[" + Thread.currentThread().getName() +"] - " + message);

    }
}
