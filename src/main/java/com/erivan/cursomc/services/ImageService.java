package com.erivan.cursomc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.erivan.cursomc.services.exceptions.FileException;

@Service
public class ImageService {

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if(!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Apenas extenções png e jpg");
		}
		
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			
			if("png".equals(ext)) {
				img = pngTojpg(img);
			}
			return img;
			
		} catch (IOException e) {
			
			throw new FileException("Erro ao ler arquivo !!");
		}
	}

	public BufferedImage pngTojpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage img, String extention) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extention, os);
			return new ByteArrayInputStream(os.toByteArray());
		}catch(IOException e){
			throw new FileException("Erro ao ler arquivo");
		}
	}
	
}
