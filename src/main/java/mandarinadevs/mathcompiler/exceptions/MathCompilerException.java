package mandarinadevs.mathcompiler.exceptions;

public class MathCompilerException extends Exception {
    private String msg;

    public MathCompilerException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MathCompilerException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
