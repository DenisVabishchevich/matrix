package com.skf.matrix;

import com.skf.matrix.service.MatrixOperations;
import com.skf.matrix.service.impl.RandomMatrixProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MatrixApplication implements CommandLineRunner {

    private final MatrixOperations sequentialMatrixOperations;
    private final MatrixOperations parallelMatrixOperations;
    private final RandomMatrixProvider matrixProvider;

    public MatrixApplication(@Qualifier("sequential") MatrixOperations sequentialMatrixOperations,
                             @Qualifier("parallel") MatrixOperations parallelMatrixOperations,
                             RandomMatrixProvider matrixProvider) {
        this.sequentialMatrixOperations = sequentialMatrixOperations;
        this.parallelMatrixOperations = parallelMatrixOperations;
        this.matrixProvider = matrixProvider;
    }


    public static void main(String[] args) {
        SpringApplication.run(MatrixApplication.class, args);
    }

    @Override
    public void run(String... args) {

        if (args == null || args.length != 1 || args[0] == null || !StringUtils.isNumeric(args[0])) {
            System.out.println("Please provide first argument as matrix size");
            return;
        }

        boolean[][] randomMatrix = matrixProvider.createRandomBoolSquareMatrix(Integer.parseInt(args[0]));

        System.out.println("--------------------------------------");
        System.out.println("start multiply in sequential");
        long start = System.currentTimeMillis();
        boolean[][] seqResult = sequentialMatrixOperations.multiply(randomMatrix, randomMatrix);
        long end = System.currentTimeMillis();
        System.out.println("Time spend im millis: " + (end - start));

        System.out.println("--------------------------------------");
        System.out.println("start multiply in parallel");
        start = System.currentTimeMillis();
        boolean[][] parallelResult = parallelMatrixOperations.multiply(randomMatrix, randomMatrix);
        end = System.currentTimeMillis();
        System.out.println("Time spend im millis: " + (end - start));

        System.out.println("--------------------------------------");
        System.out.println("arrays equals: " + Arrays.deepEquals(seqResult, parallelResult));
    }
}
