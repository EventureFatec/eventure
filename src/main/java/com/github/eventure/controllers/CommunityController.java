package com.github.eventure.controllers;

import com.github.eventure.model.Community;
import com.github.eventure.model.User;
import com.github.eventure.storage.Storage;

public class CommunityController {
	private Storage<Community> communities;
	private static int lastGeneratedId = 0;
    private static CommunityController instance;
	private CommunityController() {
		communities = new Storage<Community>();
	}
    public static CommunityController getInstance() {
        if (instance == null) {
            instance = new CommunityController();
        }
        return instance;
    }

	public void createCommunity(User u, String nome) {
		var community = new Community(nome, u.getUserId(), generateId());
		community.addUserList(u.getUserId());
		communities.add(community);
	}

	public void deleteCommunity(int idCommunity) {
		var community = findCommunity(idCommunity);
		communities.remove(community);
	}

	public Community findCommunityById(int idCommunity) {
		return communities.find(community -> community.getIdCommunity() == idCommunity).findFirst().orElse(null);
	}

	public void invites(String nome, int idUser, int idCommunity) {
		// seria uma funcão para que usuarios possam enviar convites ao criador da
		// comunidade e ele escolha se aceita ou não
		var comunnity = findCommunity(idCommunity);
		addUser(idCommunity, idUser);
	}

	public void print(UserController uc) {

		for (Community c : communities) {
			for (int i : c.getIdUsers()) {
				System.out.println(i);
				var u = uc.findUserById(i);
				System.out.println(u != null);
				System.out.println(u.getName());
			}
			System.out.println("---------------");
		}
	}

	public void addUser(int idCommunity, int idUser) {
		var community = findCommunity(idCommunity);
		community.addUserList(idUser);
	}

	public void removeUser(int idCommunity, int idUser) {
		var community = findCommunity(idCommunity);
		community.removeUserList(idUser);
	}

	public Community findCommunity(int idCommunity) {
		return communities.find(community -> community.getIdCommunity() == idCommunity).findFirst().orElse(null);
	}

	public static int generateId() {
		return lastGeneratedId++;
	}
}
