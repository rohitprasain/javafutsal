package anykey;

public class splashClass {

    public static void main1() {
        splash sp = new splash();
        sp.setVisible(true);
        BounceBall1 b = new BounceBall1();
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(30);
                sp.jLabel1.setText(Integer.toString(i));
                sp.jProgressBar1.setValue(i);
                if (i == 100) {
                    sp.setVisible(false);
                    b.setVisible(true);
                }
            }
            presskey pre = new presskey();
            pre.setVisible(true);
        } catch (Exception e) {
        }
    }
}
