package hexlet.code;

public class App {
    public static void main(String[] args) {
        var v = new Validator();
        var s = v.string();
        var n = v.number();
        var rs = s.required().isValid(null);
        var rn = n.isValid(null);
        System.out.println(rs);
        System.out.println(rn);
    }
}
