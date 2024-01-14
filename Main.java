import java.lang.annotation.*;
import java.lang.reflect.*;


//method interface, cause it has only one method, usually is used with lambdas
interface StringFunction<T> {
    T bobik(String str);
}

class StringOps {
    static String toTheUp(String target) {
        return target.toUpperCase();
    } 
}


//annotation
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno{
    String str() default "this is default annotation";
    int val() default 7777;
}


class Meta3 {
    @MyAnno()
    public static void myMeth() {
        Meta3 ob = new Meta3();

        try {
            Class<?> c = ob.getClass();
            Method m = c.getMethod("myMeth");
            //getting annotation
            MyAnno anno = m.getAnnotation(MyAnno.class);

            System.out.println(anno.str() + " " + anno.val());

        }catch(NoSuchMethodException e1) {
            System.out.println("Method not found");
        }
    }

    //generic method
    public static <T> void superPrinter(T thingToPrint) {
        System.out.println(thingToPrint);
    }

    public static void stringOp(StringFunction<String> op, String str ) {
        String result = op.bobik(str);
        System.out.println(result);
    }

    public static void printFormatted(String str, StringFunction<String> format) {
        String result = format.bobik(str);
        System.out.println(result);
    }


    public static void main(String[] args) {
        myMeth();
        superPrinter("Hello world");
        superPrinter(20234);
        superPrinter(234.444);



        StringFunction<String> exclaim = (s) -> s + "!";
        StringFunction<String> ask = (s) -> s + "?";
        printFormatted("Hello", exclaim);
        printFormatted("Hello", ask);


        //Method reference using lambdas
        stringOp(StringOps::toTheUp, "i want ot break free!");

    }
}