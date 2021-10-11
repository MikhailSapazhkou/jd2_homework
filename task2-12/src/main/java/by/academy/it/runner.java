package by.academy.it;

public class runner {
    public static void main(String[] args) {
        ServletLogger logger = new ServletLogger();
        String pathFile = logger.getLogger("log.txt","files");
        logger.setLogger(pathFile,"Test");
    }
}
