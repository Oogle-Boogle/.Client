package com.platinum;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.MenuItem;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;

public class Jframe extends Client implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	public static TrayIcon trayIcon;

	public Jframe(String[] args, int width, int height, boolean resizable) {
		super();
		setTray();
		try {
			signlink.startpriv(InetAddress.getByName(Configuration.HOST));
			initUI(width, height, resizable);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setTray() {
		if (SystemTray.isSupported()) {
			Image icon = Toolkit.getDefaultToolkit().getImage(signlink.findcachedir() + "/Interfaces/icon.png");
			trayIcon = new TrayIcon(icon, Configuration.CLIENT_NAME);
			trayIcon.setImageAutoSize(true);
			try {
				SystemTray tray = SystemTray.getSystemTray();
				tray.add(trayIcon);
				trayIcon.displayMessage(Configuration.CLIENT_NAME, Configuration.CLIENT_NAME + " has been launched!",
						TrayIcon.MessageType.INFO);

				final MenuItem minimizeItem = new MenuItem("Hide " + Configuration.CLIENT_NAME);
				new MenuItem("-");
				MenuItem exitItem = new MenuItem("Quit");
				ActionListener minimizeListener = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (frame.isVisible()) {
							frame.setVisible(false);
							minimizeItem.setLabel("Show 1# " + Configuration.CLIENT_NAME + ".");
						} else {
							frame.setVisible(true);
							minimizeItem.setLabel("Hide 1# " + Configuration.CLIENT_NAME + ".");
						}
					}
				};
				minimizeItem.addActionListener(minimizeListener);
				ActionListener exitListener = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				};
				exitItem.addActionListener(exitListener);
			} catch (AWTException e) {
				System.err.println(e);
			}
		}
	}

	@SuppressWarnings("unused")
	public void initUI(int width, int height, boolean resizable) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);
			
			if (Configuration.LOCAL == true) {
				frame = new JFrame("Local Client");
			}
			else if (Configuration.LOCAL == false) {
				frame = new JFrame(Configuration.CLIENT_NAME);
			}

			frame.setLayout(new BorderLayout());
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

			frame.setAlwaysOnTop(Client.ALWAYS_ON_TOP);
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent we) {
					String options[] = { "Yes", "No" };
					int userPrompt = JOptionPane.showOptionDialog(null, "Are you sure you wish to exit?", "Platinum",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
					if (userPrompt == JOptionPane.YES_OPTION) {
						// openURL("http://Platinum-RSPS.net/forum");
						// System.exit(-1);
						System.exit(0);
					} else {

					}
				}
			});
			setFocusTraversalKeysEnabled(false);
			JPanel gamePanel = new JPanel();
			this.getInsets();
			super.setPreferredSize(new Dimension(width - 10, height - 10));
			frame.setLayout(new BorderLayout());
			gamePanel.setLayout(new BorderLayout());
			gamePanel.add(this);
			gamePanel.setBackground(Color.BLACK);
			initializeMenuBar();
			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
			frame.pack();
			frame.setResizable(resizable);
			init();
			graphics = getGameComponent().getGraphics();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rebuildFrame(int width, int height, boolean resizable, boolean undecorated) {

		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		frame = new JFrame(Configuration.CLIENT_NAME);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				String options[] = { "Yes", "No" };
				int userPrompt = JOptionPane.showOptionDialog(null, "Are you sure you wish to exit?", "Platinum",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (userPrompt == JOptionPane.YES_OPTION) {
					openURL("http://platinum-ps.net");
					System.exit(-1);
					System.exit(0);
				} else {

				}
			}
		});
		frame.setUndecorated(undecorated);
		setFocusTraversalKeysEnabled(false);
		JPanel gamePanel = new JPanel();
		this.getInsets();
		super.setPreferredSize(new Dimension(width - 10, height - 10));
		frame.setLayout(new BorderLayout());
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(this, BorderLayout.CENTER);
		gamePanel.setBackground(Color.BLACK);
		if (!undecorated) {
			frame.getContentPane().add(menuPanel, BorderLayout.NORTH);
		}
		frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(resizable);
		// init();
		graphics = getGameComponent().getGraphics();
		frame.setLocation((screenWidth - width) / 2,
				((screenHeight - height) / 2) - screenHeight == Client.getMaxHeight() ? 0 : undecorated ? 0 : 70);
		frame.setVisible(true);

		frame.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {

				Dimension dimension = new Dimension(frame.getWidth(), frame.getHeight());

				gamePanel.setMinimumSize(dimension);
				gamePanel.setPreferredSize(dimension);
				gamePanel.setSize(dimension);

				Jframe.super.setPreferredSize(new Dimension(frame.getWidth() - 10, frame.getHeight() - 10));
				Jframe.super.revalidate();
				Jframe.super.repaint();

				Jframe.super.graphics = getGameComponent().getGraphics();

			}

		});

	}

	public void setClientIcon() {
		Image img = Client.resourceLoader.getImage("icon");
		if (img == null)
			return;
		frame.setIconImage(img);

	}

	/**
	 * Our jpanel for the menu bar
	 */
	private static JPanel menuPanel;

	/**
	 * Initializes the menu bar
	 */
	public void initializeMenuBar() {

		/*
		 * Initialize our menu panel to hold our menu buttons
		 */
		menuPanel = new JPanel();

		/*
		 * Set the menu panel as non focusable
		 */
		menuPanel.setFocusable(false);

		/*
		 * Disable focus traversal keys
		 */
		menuPanel.setFocusTraversalKeysEnabled(false);

		menuPanel.setBackground(Color.decode("#0000"));

		menuPanel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		menuPanel.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);

		/*
		 * Create our buttons
		 */
		
		JButton homeButton = createButton("Home", "House_icon.png", "Open the official Platinumum homepage.");
		JButton forumsButton = createButton("Forum", "forums.png", "Open the official Platinum forums.");

		JButton knowledgeBaseButton = createButton("Platinum Guides", "3366503.gif",
				"Open the Platinum Guides Section on the forums.");
		JButton storeButton = createButton("Store", "cart_icon.gif", "Open the official Platinum store.");
		JButton voteButton = createButton("Vote", "Small-checkmark.png", "Open the official Platinum voting page.");
		JButton scoresButton = createButton("HiScores", "hiscores.png", "Open the official Platinum Hiscores");

		JButton tsButton = createButton("Join Discord", "discord.png", "Join the Platinum discord.");

		/*
		 * Add our buttons to the menu panel
		 */
		menuPanel.add(homeButton);
		menuPanel.add(forumsButton);
		menuPanel.add(knowledgeBaseButton);
		menuPanel.add(storeButton);
		menuPanel.add(voteButton);
		menuPanel.add(scoresButton);
		menuPanel.add(tsButton);

		/*
		 * Add our menu panel to our frame
		 */
		frame.getContentPane().add(menuPanel, BorderLayout.NORTH);
	}

	/**
	 * Creates a button on the menu panel
	 * 
	 * @param title
	 *            The Title of the button
	 * @param image
	 *            The image to display
	 * @param tooltip
	 *            The tooltip when hovering over the button
	 * @return The created button
	 */
	private JButton createButton(String title, String image, String tooltip) {
		JButton button = new JButton(title);
		if (image != null)
			button.setIcon(new ImageIcon(ResourceLoader.loadImage(image)));
		button.addActionListener(this);
		if (tooltip != null)
			button.setToolTipText(tooltip);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setForeground(Color.WHITE);
		return button;
	}

	public URL getCodeBase() {
		try {
			return new URL("http://" + Configuration.HOST + "/");
		} catch (Exception e) {
			return super.getCodeBase();
		}
	}

	public URL getDocumentBase() {
		return getCodeBase();
	}

	public void loadError(String s) {
		System.out.println("loadError: " + s);
	}

	public String getParameter(String key) {
		return "";
	}

	public static void openUpWebSite(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url));
		} catch (Exception e) {
		}
	}

	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		try {
			if (cmd != null) {
				switch (cmd) {
				case "Home":
					openURL("http://Platinum-ps.net");
					break;
				case "Forum":
					openURL("http://Platinum-ps.net");
					break;
				case "Platinum Guides":
					openURL("");
					break;
				case "Store":
					openURL("https://platinum.everythingrs.com/services/store");
					break;
				case "Vote":
					openURL("https://platinum.everythingrs.com/services/vote");
					break;
				case "HiScores":
					openURL("");
					break;
				case "Join Discord":
					// String nickname = (Client.instance.getMyUsername() != null && Client.loggedIn
					// && Client.instance.getMyUsername().length() > 2) ?
					// TextClass.fixName(Client.instance.getMyUsername().replaceAll(" ", "%20")) :
					// "ForumGuest";
					openURL("https://discord.gg/HdK3cj9gpM");
					break;
				}

			}
		} catch (Exception e) {
		}
	}

	/**
	 * Opens a URL in your default web browser
	 * 
	 * @param url
	 *            The url of the website to open
	 */
	static void openURL(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	int screenWidth = (int) screenSize.getWidth();
	int screenHeight = (int) screenSize.getHeight();

}