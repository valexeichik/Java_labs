import javax.swing.*;
import java.awt.*;

public class Panel3 extends JPanel {
    final ImageIcon[] images = new ImageIcon[]{new ImageIcon("a.png"), new ImageIcon("b.png"),
                                        new ImageIcon("c.png"), new ImageIcon("d.png")};
    Panel3() {
        super();
        setLayout(new GridLayout(2,2));

        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < images.length; i++) {
            JRadioButton radioButton = new JRadioButton(images[3]);
            radioButton.setPressedIcon(images[0]);
            radioButton.setRolloverIcon(images[1]);
            radioButton.setSelectedIcon(images[2]);
            group.add(radioButton);
            add(radioButton);
        }
    }
}
