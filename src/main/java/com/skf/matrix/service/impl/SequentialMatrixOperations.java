package com.skf.matrix.service.impl;

import com.skf.matrix.service.MatrixOperations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.skf.matrix.service.BooleanUtils.boolMultiply;
import static com.skf.matrix.service.BooleanUtils.boolSum;

@Service
@Qualifier("sequential")
public class SequentialMatrixOperations implements MatrixOperations {
    @Override
    public int[][] multiply(byte[][] firstMatrix, byte[][] secondMatrix) {
        int[][] result = new int[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }

        return result;
    }

    int multiplyMatricesCell(byte[][] firstMatrix, byte[][] secondMatrix, int row, int col) {
        int cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    boolean multiplyBooleanMatricesCell(boolean[][] firstMatrix, boolean[][] secondMatrix, int row, int col) {
        boolean cell = false;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell = boolSum(cell, boolMultiply(firstMatrix[row][i], secondMatrix[i][col]));
        }
        return cell;
    }


    @Override
    public boolean[][] multiply(boolean[][] firstMatrix, boolean[][] secondMatrix) {
        boolean[][] result = new boolean[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyBooleanMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }

        return result;
    }
}
