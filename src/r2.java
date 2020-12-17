import java.util.ArrayList;
import java.util.List;

public class r2 {

    public static void main(String[] args) {

        String value = "In JavaRush, Diego the best, Diego is Java God".replace("Java", "Rush");
        System.out.println(value);


        int s = 'f';


        System.out.println((char) (s ^ ' '));


        System.out.println(jkStrTo.strToInt("1"));


        //Вот несколько примеров того, как создать экземпляр List:
        List testList = new ArrayList();


        //Вы вставляете элементы (объекты) в список, используя его метод add():
        testList.add(79);


        //Можно вставить элемент в список по определенному индексу. Интерфейс List имеет версию метода add(), которая
        // принимает индекс в качестве первого параметра, и элемент для вставки в качестве второго:
        testList.add(1, 80);

        //Для получения элемента используется индекс элементов, а также метод get(int index):
        Object x = testList.get(1);
        Object y = testList.get(0);

        System.out.println((int) x);
        System.out.println((int) y);




    }

}
