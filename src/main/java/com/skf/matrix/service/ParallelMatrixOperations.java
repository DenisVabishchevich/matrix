package com.skf.matrix.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Qualifier("parallel")
public class ParallelMatrixOperations implements MatrixOperations {
    @Override
    public int[][] multiply(byte[][] firstMatrix, byte[][] secondMatrix) {
        int[][] result = new int[firstMatrix.length][secondMatrix[0].length];
        List<CompletableFuture<Void>> cellCalculateFutures = new ArrayList<>();

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                int localRow = row;
                int localCol = col;
                cellCalculateFutures.add(CompletableFuture.runAsync(() -> {
                    result[localRow][localCol] = multiplyMatricesCell(firstMatrix, secondMatrix, localRow, localCol);
                }));
            }
        }
        CompletableFuture.allOf(cellCalculateFutures.toArray(new CompletableFuture[0])).join();
        return result;
    }

    int multiplyMatricesCell(byte[][] firstMatrix, byte[][] secondMatrix, int row, int col) {
        int cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }
}
