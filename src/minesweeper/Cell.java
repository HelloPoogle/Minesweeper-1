package minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

public class Cell extends JButton {
	private static final long serialVersionUID = 1L;

	private static final MatteBorder UNLOCKED_BORDER = new MatteBorder(1, 0, 0, 1, Color.GRAY);

	private static final ImageIcon MINE_PIC = new ImageIcon(Cell.class.getResource("pics/mine.png"));
	private static final ImageIcon HIT_MINE_PIC = new ImageIcon(Cell.class.getResource("pics/hitmine.gif"));
	private static final ImageIcon WRONG_MINE_PIC = new ImageIcon(Cell.class.getResource("pics/wrongmine.png"));
	private static final ImageIcon FLAG_PIC = new ImageIcon(Cell.class.getResource("pics/flag.png"));

	private final boolean isBomb;
	private boolean flagged;
	private boolean unlocked;
	private final int row;
	private final int col;
	private int numBombNeighbors;

	private Timer time = new Timer(300, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!flagged && !unlocked) {
				setBorder(new BevelBorder(BevelBorder.RAISED));
			}
		}
	});

	public Cell(boolean isBomb, int row, int col, Font font) {
		setBackground(Color.LIGHT_GRAY);
		setFont(font);
		// setFont(new Font("Acens", Font.BOLD, 15));

		setBorder(new BevelBorder(BevelBorder.RAISED));
		unlocked = false;
		flagged = false;
		this.isBomb = isBomb;
		this.row = row;
		this.col = col;
	}

	public void unlock() {
		unlocked = true;
		setBorder(UNLOCKED_BORDER);

		switch (numBombNeighbors) {
			case 0:
				return;
			case 1:
				setForeground(Color.BLUE);
				break;
			case 2:
				setForeground(Color.decode("#197519"));
				break;
			case 3:
				setForeground(Color.RED);
				break;
			case 4:
				setForeground(Color.decode("#000066"));
				break;
			case 5:
				setForeground(Color.decode("#6E2500"));
				break;
			case 6:
				setForeground(Color.decode("#1E8595"));
				break;
			case 7:
				setForeground(Color.BLACK);
				break;
			case 8:
				setForeground(Color.GRAY);
				break;
		}

		setText(String.valueOf(numBombNeighbors));
	}

	public void flag() {
		setIcon(FLAG_PIC);
		flagged = true;
	}

	public void unflag() {
		setIcon(new ImageIcon(getClass().getResource("")));
		flagged = false;
	}

	public void explode() {
		setBorder(UNLOCKED_BORDER);
		setIcon(MINE_PIC);
		unlocked = true;
	}

	public void clickExplode() {
		// FIXME red bomb disappears when hover over
		setBorder(UNLOCKED_BORDER);
		setIcon(HIT_MINE_PIC);
		setBackground(Color.RED);
		unlocked = true;
	}

	public void wrongify() {
		setBorder(UNLOCKED_BORDER);
		setIcon(WRONG_MINE_PIC);
		unlocked = true;
	}

	public void depress() {
		setBorder(UNLOCKED_BORDER);
		time.start();
	}

	public boolean isBomb() {
		return isBomb;
	}

	public boolean isUnlocked() {
		return unlocked;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getNumBombNeighbors() {
		return numBombNeighbors;
	}

	public void setNumBombNeighbors(int numBombNeighbors) {
		this.numBombNeighbors = numBombNeighbors;
	}

	public void setIsUnlocked(boolean isUnlocked) {
		unlocked = isUnlocked;
	}
}
