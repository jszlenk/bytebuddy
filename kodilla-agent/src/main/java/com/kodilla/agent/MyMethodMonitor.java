package com.kodilla.agent;

import net.bytebuddy.asm.Advice;

public class MyMethodMonitor {

    @Advice.OnMethodEnter
    public static long onEnter(@Advice.Origin("#m") String methodName,
                               @Advice.Origin Class<?> clazz) {
        long startTime = System.currentTimeMillis();
        System.out.println("Entering method: " + clazz.getName() + "." + methodName);
        System.out.println("Start time: " + startTime);
        return startTime;
    }

    @Advice.OnMethodExit
    public static void onExit(@Advice.Origin("#m") String methodName,
                              @Advice.Origin Class<?> clazz,
                              @Advice.Enter long startTime) {
        long endTime = System.currentTimeMillis();
        System.out.println("Exiting method: " + clazz.getName() + "." + methodName);
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }
}
