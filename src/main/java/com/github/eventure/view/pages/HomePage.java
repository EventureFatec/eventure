package com.github.eventure.view.pages;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import com.github.eventure.view.windowing.basic.Page;
import com.github.eventure.view.windowing.basic.PageLayouts;
import com.github.eventure.web.Requests;
import com.github.eventure.controllers.AddressController;
import com.github.eventure.controllers.CommunityController;
import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.ImageController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.Community;
import com.github.eventure.model.EventClassification;
import com.github.eventure.model.Session;
import com.github.eventure.model.User;
import com.github.eventure.model.address.Address;
import com.github.eventure.model.address.Cep;
import com.github.eventure.view.windowing.basic.Notification;

public class HomePage extends Page {
	private String imagePath = "";
	private JPanel p;

	public HomePage() {
		super(PageLayouts.GALLERY_LAYOUT);
		setDefault(true);

		var gallery = new JLabel("GALERIA");
		gallery.setFont(gallery.getFont().deriveFont(48f));
		add(gallery, "span");
		add(new JLabel("Example 1"), "wrap");
		System.out.println("test");
		p = new JPanel(new MigLayout("fillx", PageLayouts.REGULAR_LAYOUT));
		var b = new JButton("Send Notification");
		b.addActionListener(event -> {
			sendNotification(new Notification("example notification"));
		});
		var b1 = new JButton("Button 1");
		b1.addActionListener(event -> test());
		p.add(b1, "split 1");
		// var b1 = new JButton("utt'U p.add(new JButton("Button 1"), "split 1");
		var b2 = new JButton("Button 2");
		b2.addActionListener(event -> switchPage(getPageId(SecondaryPage.class)));
		p.add(b2, "wrap");
		p.add(b, "growx, span");

		add(p, "");
		add(new JLabel("Example 3"), "span");
	}
    public void test()
    {
    	var userController = new UserController();
    	userController.createUser("allisson", "thomas", "Allisson7787@", "allissonthomas600@gmail.com");
    	userController.createUser("lucas", "bernado", "Allisson7787@", "lucas@gmail.com");
    	var communityController = new CommunityController();
    	var user = new User();
    	user.setUserId(3);
    	user.setName("marlone");
    	communityController.createCommunity(user, "jogos legais");
    	communityController.addUser(0, 0);
    	communityController.addUser(0, 1);
    	communityController.print();
    	communityController.removeUser(0, 0);
    	communityController.print();
    	
    }
	private void tryLogout() {
		Session.logout();
		switchPage(getPageId(LoginPage.class));
	}
}
