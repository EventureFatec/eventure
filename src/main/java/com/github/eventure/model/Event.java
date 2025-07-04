package com.github.eventure.model;

//import java.time.LocalTime;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import com.github.eventure.model.address.Cep;

public class Event {
	public boolean ativo = true;
	private int id;
	private int idMaker;
	private String title;
	private String description;
	private EventClassification type;
	private List<Image> images;
	private String imagePath;
	private String date;
	private String dateEnd;
	private String startHours;
	private String endHours;
	private Address address;
	private Cep cep;
	private Visibilidade visibilidade; // verifica se o evento é privado ou publico
	private List<Integer> participacaoConfirmada = new ArrayList<>(); // usuarios que foram no evento
	private List<Integer> confirmedParticipantIds = new ArrayList<>(); // usuario que estão confirmados nos eventos
	private List<Integer> pendingRequestIds = new ArrayList<>(); /* lista para caso se o evento for privado
	o criador do evento vai ter essa lista com os id para ele decidir se confirma a participação ou não dos 
	usuarios
    */
	public Event(int id, String description, String title, EventClassification type) {
		this.id = id;
		this.description = description;
		this.title = title;
		this.type = type;
	}

	public Event(int id, String description, String title, EventClassification type, String date,
			String startHours, String endHours, String imagePath, Address address) {
		super();
		this.id = id;
		this.description = description;
		this.title = title;
		this.type = type;
		this.date = date;
		this.startHours = startHours;
		this.endHours = endHours;
		this.imagePath = imagePath;
		this.address = address;
	}

	public Event() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getIdMaker() {
		return idMaker;
	}

	public void setIdMaker(int idMaker) {
		this.idMaker = idMaker;
	}

	public Cep getCep() {
		return cep;
	}

	public void setCep(Cep cep) {
		this.cep = cep;
	}

	public void setName(Cep cep) {
		this.cep = cep;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartHours() {
		return startHours;
	}

	public void setStartHours(String startHours) {
		this.startHours = startHours;
	}

	public String getEndHours() {
		return endHours;
	}

	public void setEndHours(String endHours) {
		this.endHours = endHours;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EventClassification getType() {
		return type;
	}

	public void setType(EventClassification type) {
		this.type = type;
	}

	public void addConfirmedParticipantIds(int id) {
		confirmedParticipantIds.add(id);
	}

	public void removeConfirmedParticipantIds(int id) {
	    boolean removed = confirmedParticipantIds.remove(Integer.valueOf(id));
	    System.out.println("Removido? " + removed);
	}
	public void setConfirmedParticipantIds(List<Integer> confirmedParticipantIds) {
		this.confirmedParticipantIds = confirmedParticipantIds;
	}
	public List<Integer> getConfirmedParticipantIds() {
		return confirmedParticipantIds;
	}

	public void setPendingRequestIds(List<Integer> pendingRequestIds) {
		this.pendingRequestIds = pendingRequestIds;
	}
	public List<Integer> getPendingRequestIds() {
		return pendingRequestIds;
	}
	public void addPendingRequestIds(int id) {
		pendingRequestIds.add(id);
	}

	public void removePendingRequestIds(int id) {
	    boolean removed = pendingRequestIds.remove(Integer.valueOf(id));
	    System.out.println("Removido? " + removed);
	}
	public boolean usersParticipaOuNão(int id)
	{
		return confirmedParticipantIds.contains(id);
	}
	public boolean usersParticipaOuNãoListPending(int id)
	{
		return pendingRequestIds.contains(id);
	}
	
	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Visibilidade getVisibilidade() {
		return visibilidade;
	}

	public void setVisibilidade(Visibilidade visibilidade) {
		this.visibilidade = visibilidade;
	}

	public List<Integer> getParticipacaoConfirmada() {
		return participacaoConfirmada;
	}

	public void setParticipacaoConfirmada(List<Integer> participacaoConfirmada) {
		this.participacaoConfirmada = participacaoConfirmada;
	}
    public void addParticipaoConfirmada(int idEvento) {
    	participacaoConfirmada.add(idEvento);
    }
    public void removeParticipaoConfirmada(int idEvento) {
    	 boolean removed = participacaoConfirmada.remove(Integer.valueOf(idEvento));
 	    System.out.println("Removido? " + removed);
    }
	
    
	
}
