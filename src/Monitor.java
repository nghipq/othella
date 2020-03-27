
import Screen.Option;
import Screen.Welcome;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hikari_Group SE1403
 */
public class Monitor {    
    public static void main(String[] args) throws InterruptedException {
        Welcome wc = new Welcome();
        wc.setVisible(true);
        wc.setLocationRelativeTo(null);

        /*
            loop from 0 to 100 to make loading effect
         */
        
        for (int i = 0; i <= 100; i++) {
            wc.JLBLoading.setText("Loading: " + i + "%");
            wc.PBLoading.setValue(i);
            Thread.sleep(30);
        }
        
        wc.setVisible(false);

        Option game = new Option();
        game.setVisible(true);
        game.setLocationRelativeTo(null);
        while(true) {
            game.run();
            Thread.sleep(100);
        }
    }
}
