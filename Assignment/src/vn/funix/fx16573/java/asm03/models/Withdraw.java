package vn.funix.fx16573.java.asm03.models;

public interface Withdraw {
    boolean withdraw(double amount, String id) throws Exception;

    boolean isAccepted(double amount);
}
