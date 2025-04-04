package com.github.eventure.controllers;

import com.github.eventure.model.Community;
import com.github.eventure.model.User;
import com.github.eventure.storage.Storage;

public class CommunityController {
	private Storage<Community> communities;

	public CommunityController() {
		if (communities == null) {
			communities = new Storage<Community>();
		}
	}

	public void createCommunity(User u, String nome) {
		var community = new Community(nome, u.getUserId());
	}

	public void addUser(int idCommunity, int idUser) {
		var community = findCommunity(idCommunity);
		community.addUserList(idUser);
	}

	public Community findCommunity(int idCommunity) {
		return communities.find(community -> community.getIdCommunity() == idCommunity).findFirst().orElse(null);
	}
}
