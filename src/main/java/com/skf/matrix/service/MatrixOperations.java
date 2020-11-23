package com.skf.matrix.service;

public interface MatrixOperations {

    int[][] multiply(byte[][] firstMatrix, byte[][] secondMatrix);

    boolean[][] multiply(boolean[][] firstMatrix, boolean[][] secondMatrix);
}
