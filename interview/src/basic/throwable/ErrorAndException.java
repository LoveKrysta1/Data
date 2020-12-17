package basic.throwable;

import java.io.FileNotFoundException;

public class ErrorAndException {
    private void throwError() {
        throw new StackOverflowError();
    }

    private void throwRuntimeException() {
        throw new RuntimeException();
    }

    private void throwCheckedException() {//直接抛出异常，这锅我不背
        try {
            throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {//一旦异常被抛出，后续就不会被执行
        ErrorAndException errorAndException = new ErrorAndException();
        errorAndException.throwError();
        errorAndException.throwRuntimeException();
        errorAndException.throwCheckedException();
    }
}
