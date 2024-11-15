package com.kodilla.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("Kodilla agent is running.");
        System.out.println("Is redefine classes allowed: " + instrumentation.isRedefineClassesSupported());
        System.out.println("Is retransform classes allowed: " + instrumentation.isRetransformClassesSupported());

        AgentBuilder agentBuilder = new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("com.kodilla.bytebuddy.User"))
                .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                        builder.visit(Advice.to(MyMethodMonitor.class).on(ElementMatchers.named("doSomething")))
                );

        agentBuilder.installOn(instrumentation);
    }

    public static void agentmain(String args, Instrumentation instrumentation) {
        premain(args, instrumentation);
    }
}
