package mandarinadevs.mathcompiler.utils;

public class MathUtil {
    public static Double truncate(Double number) {
        String value = String.valueOf(number);
        value = value.substring(0, value.indexOf("."));

        return (double) Integer.parseInt(value);
    }
}
