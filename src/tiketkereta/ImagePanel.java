package tiketkereta;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel(Image img) {
        this.img = img;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Menggambar gambar agar memenuhi seluruh area panel
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}