package by.academy.it;

public class ParserArgs {

    public static String[][] getParam(String[] args) {

        String[][] arrayParam = new String[args.length][3];

        for (int i = 0; i < args.length; i++) {
//            if (args[i].contentEquals(";")) {

            arrayParam[i] = args[i].split(";");
//            } else {
            // ТоDO описать сообщение об ошибке
        }
        //       }
        return arrayParam;
    }
}
