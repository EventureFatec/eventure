package com.github.eventure.model;

public enum EventClassification {
	MUSICAL("Musicais"), PARTIES_AND_SHOWS("Festas e Shows"), THEATERS_AND_SPECTACLES("Teatros e Espetaculos"),
	STAND_UP_COMEDY("Stand up Comedy"), TOURS("Passeios"), SPORTS_AND_LEISURE("Esportes e Lazer"),
	CONGRESS_AND_LECTURE("Congressos e Palestras"), CHILDRENS("Infantis"), GASTRONOMY("Gastronomia"),
	COURSES_AND_WORKSHOPS("Cursos e oficinas"), TECHNOLOGY("Tecnologia"),
	RELIGION_AND_SPIRITUALITY("Religiao e espiritualidade"), EDUCATIONAL("Educacional"), HOBBIES("Hobbies"),
	CORPORATE("Corporativo"), ACADEMIC("Academico"), SCIENTIFIC("Cientifico"), NETWORKING("Networking"),
	HEALTH_AND_WELL_BEING("Saude e bem estar"), ART_AND_CULTURE("Arte e Cultura"), FESTIVALS("Festivais"),
	CONFERENCE("Conferencia");

	private String label;

	private EventClassification(String eventLabel) {
		label = eventLabel;
	}

	public String getLabel() {
		return label;
	}
}
