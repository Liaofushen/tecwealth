package com.lfs.tecwealth.common.util;

import java.util.function.BooleanSupplier;

/**
 * Exes
 *
 * @author fushenliao
 * @version 1.0.0
 * @project tecwealth
 * @create 2022/6/21
 * @modify 2022/6/21
 */
public class Exes {
    public static void runif(String option, Runnable runnable) {
        if ("1".equals(option)) {
            runnable.run();
        }
    }

    public static void runif(BooleanSupplier supplier, Runnable runnable) {
        if (supplier.getAsBoolean()) {
            runnable.run();
        }
    }

    public static void runif(boolean option, Runnable runnable) {
        if (option) {
            runnable.run();
        }
    }

    public static void main(String[] args) {
        Exes.runif("1", () -> System.out.println("i am 1"));
        Exes.runif("0", () -> System.out.println("i am 0"));
        Exes.runif(() -> true, () -> System.out.println("i am supplier 1"));
        Exes.runif(() -> false, () -> System.out.println("i am supplier 0"));
        Exes.runif(true, () -> System.out.println("i am bool 1"));
        Exes.runif(false, () -> System.out.println("i am bool 0"));
    }
}
