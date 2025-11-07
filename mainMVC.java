package controller;

import java.sql.SQLException;

import model.model;
import view.view_Accueil;

public class mainMVC {

	private static model m;
	
	public static model getM() {
		return m;
	}
		
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("lancement");
		m = new model();
		m.getall();
		
		view_Accueil va = new view_Accueil();
	}

}
