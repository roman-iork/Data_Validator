package hexlet.code;

public class App {
    public static void main(String[] args) {
//        var v = new Validator();
//        var strV = v.string();
//        var strV1 = v.string();
////        strV.required();
////        strV.minLength(0);
////        strV.contains("");
//        System.out.println(strV.contains("whatthe").isValid("what does the fox say"));
//        System.out.println(strV1.minLength(40).minLength(2).isValid("abc"));

        var x = new Validator();
        var p = x.number();
        var t = p.positive().range(-5, 9).isValid(3);
        System.out.println(t);
    }
}
