/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package naivgationdrawer.Services;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.ObservableList;
import naivgationdrawer.Entite.Produit;
/**
 *
 * @author chev
 */
public class Csv {
    public Csv(ObservableList<Produit> v) {
        // TODO code application logic here
        String outputFile = "Produit.csv";
		//String g=;
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
			
		try {
			// use FileWriter constructor that specifies open for appending
			  CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("id");
				csvOutput.write("nom");
//                                csvOutput.write("Immatriculation");
//				csvOutput.write("Date de premiere mis en circulation");
//				csvOutput.write("Date de derniere revision");
//				csvOutput.write("disponibilite");
				

				csvOutput.endRecord();
			}
			// else assume that the file already has the correct header line
			//System.out.println(v.size());
                               

				csvOutput.endRecord();
                       for(int i=0;i<v.size();i++)
                        {
                           csvOutput.write(Integer.toString(v.get(i).getId()));//get(i).getMatricule()
                           csvOutput.write(v.get(i).getNom());                     
//                           csvOutput.write(v.get(i).getImmatriculation());
//                           csvOutput.write(v.get(i).getDate_mise_en_circulation());
//                           csvOutput.write(v.get(i).getDate_de_derniere_revision());
//                           if(v.get(i).isDisponibilite())
//                           csvOutput.write("Disponible");
//                           else csvOutput.write("Not Disponible");
//                           csvOutput.endRecord();
//
 }
			
			
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
