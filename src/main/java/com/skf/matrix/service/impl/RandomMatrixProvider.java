package com.skf.matrix.service.impl;

import org.springframework.stereotype.Component;

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
}
