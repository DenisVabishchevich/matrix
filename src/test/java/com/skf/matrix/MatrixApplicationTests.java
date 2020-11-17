package com.skf.matrix;

import com.skf.matrix.service.MatrixOperations;
import com.skf.matrix.service.impl.RandomMatrixProvider;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class MatrixApplicationTests {

    @Autowired
    private RandomMatrixProvider matrixProvider;
    @Autowired
    private MatrixOperations sequentialMatrixOperations;
    @Autowired
    private MatrixOperations parallelMatrixOperations;

    @Test
    void testMultiplyMatrix() {
        byte[][] randomSquareMatrix = matrixProvider.createRandomSquareMatrix(100);
        double[][] doubleMatrix = new double[randomSquareMatrix.length][randomSquareMatrix.length];

        for (int i = 0; i < randomSquareMatrix.length; i++) {
            for (int i1 = 0; i1 < randomSquareMatrix[i].length; i1++) {
                doubleMatrix[i][i1] = randomSquareMatrix[i][i1];
            }
        }

        //apache math library is used to evaluate expected value
        RealMatrix firstMatrix = new Array2DRowRealMatrix(doubleMatrix);
        RealMatrix secondMatrix = new Array2DRowRealMatrix(doubleMatrix);
        RealMatrix result = firstMatrix.multiply(secondMatrix);

        int[][] intMatrix = new int[randomSquareMatrix.length][randomSquareMatrix.length];
        for (int i = 0; i < result.getData().length; i++) {
            for (int i1 = 0; i1 < result.getData()[i].length; i1++) {
                intMatrix[i][i1] = (int) result.getData()[i][i1];
            }
        }

        int[][] multiplySeq = sequentialMatrixOperations.multiply(randomSquareMatrix, randomSquareMatrix);
        int[][] multiplyParallel = parallelMatrixOperations.multiply(randomSquareMatrix, randomSquareMatrix);

        Assertions.assertTrue(Arrays.deepEquals(intMatrix, multiplySeq));
        Assertions.assertTrue(Arrays.deepEquals(intMatrix, multiplyParallel));

    }

}
