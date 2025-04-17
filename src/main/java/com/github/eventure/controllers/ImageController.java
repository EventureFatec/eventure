package com.github.eventure.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageController {
	private String imagePath;

	public String selecionarImagem() {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Selecionar Arquivo");
		jfc.setFileFilter(
				new FileNameExtensionFilter("Arquivo de Imagens (*.PNG, *.JPG, *.JPEG)", "png", "jpg", "jpeg"));
		int resultado = jfc.showOpenDialog(jfc);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			try {
				File selectedFile = jfc.getSelectedFile();
				imagePath = selectedFile.getAbsolutePath();
				System.out.println("Caminho da imagem selecionada: " + imagePath);
				return imagePath;
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return imagePath;
	}

	public JLabel carregarImagem(String imagePath) throws IOException {
		BufferedImage myPicture = ImageIO.read(new File(imagePath));
		JLabel picLabel = new JLabel();
		picLabel.setIcon(new ImageIcon(myPicture));
		return picLabel;
	}
}
