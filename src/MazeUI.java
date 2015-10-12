import java.awt.BorderLayout;
import java.awt.Container;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class MazeUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final long FRAME_INTERVAL = 16L;

    private final MazePanel mazePanel;

    public MazeUI(final Maze maze) {
	mazePanel = new MazePanel(maze);

	Container contentPanel = getContentPane();
	contentPanel.setLayout(new BorderLayout());
	contentPanel.add(mazePanel, BorderLayout.CENTER);

	pack();
    }

    public void start() {
	setVisible(true);

	ScheduledExecutorService service = Executors
		.newSingleThreadScheduledExecutor();

	service.scheduleAtFixedRate(new Runnable() {

	    @Override
	    public void run() {
		nextFrame();
	    }
	}, 0, FRAME_INTERVAL, TimeUnit.MILLISECONDS);

    }

    private void nextFrame() {
	mazePanel.repaint();
    }
}
