import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.MovieDao;
import dao.impl.MovieDaoImpl;
import entities.Movie;
import entities.Rate;



/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args ) throws IOException
	{

		MovieDao dao = new MovieDaoImpl();
	/*	Map<String,Object> filters = new HashMap<String,Object>();
		filters.put("rate.rating",8);
		List<Movie> list = dao.list(filters);
		
		System.out.println(list.get(0).getName()+list.get(0).getId());
		*/

		
		Movie movie2 = dao.getByName(" Forrest  Gump  ");
		
		System.out.println(movie2.getName()+"  "+movie2.getReleaseYear()+ " "+movie2.getId());
		
		

	}
	
	public void getFromRatingList() throws IOException{
		MovieDao dao = new MovieDaoImpl();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("ratings.list"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String line = br.readLine();
		

			while (line != null) {

				String arrayString[] = line.split("\\s+");
				if(arrayString.length>5){
					
					if(isNumeric(arrayString[1]) && isNumeric(arrayString[2]) && isNumeric(arrayString[3])){
						int tmp = line.indexOf(arrayString[3])+3;
						while(line.charAt(tmp) == ' '){
							tmp++;
						}
						String movieNamePlusYear = line.substring(tmp);
						String movieName =  movieNamePlusYear.substring(0,movieNamePlusYear.indexOf("("));
						if(movieNamePlusYear.indexOf(")")<0 || movieNamePlusYear.indexOf("(")<0 || movieNamePlusYear.indexOf(")")< movieNamePlusYear.indexOf("(") || arrayString[2].length()>4 ){
							line = br.readLine();
							continue;
						}
						String year = movieNamePlusYear.substring(movieNamePlusYear.indexOf("(")+1,movieNamePlusYear.indexOf(")"));
						if(year.length()>=5){
							year = year.substring(0, 4);
						}

						if(movieName.contains("\"")){
							// dizi
						}else{
								Movie movie = new Movie();
							movie.setName(movieName);
							
							Rate rate = new Rate();
							try{
								movie.setReleaseYear(Long.valueOf(year));
								rate.setRating(Double.valueOf(arrayString[2]));
								rate.setVoteCount(Long.valueOf(arrayString[1]));
								movie.setRate(rate);
							}catch(Exception e){
								line = br.readLine();
								continue;
							}
							dao.persist(movie);
						}
					}
				}

				line = br.readLine();
			}
		} finally {
			br.close();
		}
	}

	public static boolean isNumeric(String str)  
	{  
		try  
		{  
			double d = Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}
}
