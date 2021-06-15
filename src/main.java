



public class main {


    public static void main(String args[]) {
        Raymond R = new Raymond(15);
        R.setParentAndRequest(3, 1, false);
        R.setParentAndRequest(2, 1, false);
        R.setParentAndRequest(4, 2, false);
        R.setParentAndRequest(5, 2, false);
        R.setParentAndRequest(6, 3, true);
        R.setParentAndRequest(7, 3, false);
        R.setParentAndRequest(8, 3, true);
        R.setParentAndRequest(9, 4, false);
        R.setParentAndRequest(10, 5, false);
        R.setParentAndRequest(11, 5, true);
        R.setParentAndRequest(12, 7, false);
        R.setParentAndRequest(13, 7, false);
        R.setParentAndRequest(14, 8, false);
        R.setParentAndRequest(15, 8, false);

        System.out.println(R.toString());

        R.start();

    }

}