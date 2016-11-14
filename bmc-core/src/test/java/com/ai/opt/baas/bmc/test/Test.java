package com.ai.opt.baas.bmc.test;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;

public class Test {

    public static void main(String[] args) {
        int[] intArray = { 1, 2, 3, 4, 5 };
        int[] removed = ArrayUtils.remove(intArray, 3);// create a new array
        System.out.println(Arrays.toString(removed));
    }

}
