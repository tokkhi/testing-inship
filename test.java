import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Calendar;  
import java.util.GregorianCalendar;

public class test{
	

	public static void main(String[] args) throws IOException{
		String n,sd,st,ed,et;
		try {
    FileInputStream fis = new FileInputStream("c:\\java\\time.txt");
    InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
    BufferedReader br = new BufferedReader(isr);
    String line;
    while((line = br.readLine()) != null) {
		
            String[] arr = line.split("\\|");
        	System.out.println(arr[0]);		
				if(arr.length < 5){
				n = arr[0];
				sd = arr[1];
				ed = arr[3];
					 arr = new String [5];
					 arr[2] = "00:00"; 
					 arr[4] = "00:00";
					 et = arr[4];
					 st = arr[2];
					}else{
				n = arr[0];
				sd = arr[1];
				st = arr[2];
				ed = arr[3];
				et = arr[4];
					}
			System.out.println(kid(sd,st,ed,et));
			System.out.println("__________________________________");
    }
}            catch (IOException e) {
			e.printStackTrace();
}

		
		
	}
	
	
	
	
	   public static String kid(String sd, String st, String ed, String et){
        double sum = 0;
		double milliTime =0, baht =0, bathot =0;
		int minl = 0 ,min = 0 ,minot = 0 ,minof =0;
		
		String data = null;
		String start,end,Sot,Sl,Sw,Ew,nd;
		DateFormat format1,format2; 
		Date datestart,dateend,times,timee,ot,late,startwork,endwork,nday;
		String day1 = "";
		NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(0);
        nf.setRoundingMode(RoundingMode.HALF_UP);
		
		
		try 
    		{  
			start = sd.concat(" "+st);
			end = ed.concat(" "+et);
			Sot = sd.concat(" "+"17:30");
			Sl= sd.concat(" "+"08:05");
			Sw = sd.concat(" "+"08:00");
			Ew = sd.concat(" "+"17:00");
			nd = ed.concat(" "+"00:00");
			
			
		format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		format2 = new SimpleDateFormat("HH:mm");
      	datestart = (Date)format1.parse(start);  
		dateend = (Date)format1.parse(end);
		times = (Date)format2.parse(st);
		timee = (Date)format2.parse(et);
		ot = (Date)format1.parse(Sot);
		late = (Date)format1.parse(Sl);
		startwork = (Date)format1.parse(Sw);
		endwork = (Date)format1.parse(Ew);
		nday = (Date)format1.parse(nd);
		
		Calendar c = Calendar.getInstance();
		Calendar d = Calendar.getInstance();
		c.setTime(datestart);
		d.setTime(dateend);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int dayOfWeeked = d.get(Calendar.DAY_OF_WEEK);
		
		
			if(!dateend.equals(datestart)){
				
		double t = 36.25/60;
		
		if(dayOfWeek == 7 || dayOfWeek == 1 ){
			
			milliTime = dateend.getTime() - datestart.getTime();
			min = Integer.parseInt(nf.format(milliTime/(1000*60)));
			
			if(datestart.before(late)){
			milliTime = dateend.getTime() -startwork.getTime();
			min = Integer.parseInt(nf.format(milliTime/(1000*60)));
		}
			
			if(min >= 540 && datestart.before(endwork)){
			min = min - 60;
		}
			
				if(dateend.after(ot) || dateend.before(startwork)){
			milliTime = dateend.getTime() - ot.getTime();
			minot = Integer.parseInt(nf.format(milliTime/(1000*60)));
			min = min - minot;
		}
		
		
			baht += min*t*1.5;
			baht += (minot*t)*2;
			
			
		}else{
			
				if(datestart.before(late)){
			milliTime = dateend.getTime() -startwork.getTime();
			min = Integer.parseInt(nf.format(milliTime/(1000*60)));
		}
		
		if((dayOfWeek > 1 && dayOfWeek <= 6) && (dayOfWeeked == 7 || dayOfWeeked == 1 ) && (dateend.after(ot) || dateend.before(startwork))){
			milliTime = dateend.getTime() - datestart.getTime();
			minot = Integer.parseInt(nf.format(milliTime/(1000*60)));
			
			milliTime = dateend.getTime() - nday.getTime();
			minof = Integer.parseInt(nf.format(milliTime/(1000*60)));
			
			minof = minot - minof;
			
		}else{
			
			if(dateend.after(ot) || dateend.before(startwork)){
			milliTime = dateend.getTime() - ot.getTime();
			minot = Integer.parseInt(nf.format(milliTime/(1000*60)));
			min = min - minot;
		}
		}
		
				
		
			
		if(min >= 540 && datestart.before(endwork)){
			min = min - 60;
		}
		
			baht += min*t;
			baht += (minof*t)*2;
			baht += (minot*t)*1.5;
			
			if(datestart.after(late) && datestart.before(endwork)){
				milliTime = datestart.getTime() - startwork.getTime();
			minl = Integer.parseInt(nf.format(milliTime/(1000*60)));
			baht = baht -(t*minl);
			
		}
		
			}
		}   
		day1 +="Start :" + datestart + "\nEnd :" + dateend +	"\n";
		day1 +="working :"+ min +"\n" ;
		day1 +=" Late :"+ Integer.toString(minl)+"\n";
		day1 +=" OT :"+ Integer.toString(minot)+"\n";
		day1 +=" salary :" + Double.toString(baht);
			}
		
			
    catch (Exception e)
    {}
		
        return day1;
    }
	
}
