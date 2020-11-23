package com.skf.matrix.service.impl;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomMatrixProvider {

    public byte[][] createRandomSquareMatrix(int size) {
        byte[][] result = new byte[size][size];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = (byte) Math.round(Math.random());
            }
        }
        return result;
    }

    public boolean[][] createRandomBoolSquareMatrix(int size) {
        boolean[][] result = new boolean[size][size];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = ThreadLocalRandom.current().nextBoolean();
            }
        }
        return result;
    }
}
