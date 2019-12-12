/**
 * Represent a timestamp consisting of a date (day/month/year) and time 
 * in hours and minutes (24h format.
 */
public class DateTime implements Comparable<DateTime> { //For part 4
    
    public int year;
    public int month;
    public int day; 
    public int hours;
    public int minutes;    
    public int seconds;
    public boolean pm; 

    
    public DateTime(int year, int day, int month, int h, int m) {        
        this.year = year; 
        this.month = month; 
        this.day = day;     
        this.hours = h; 
        this.minutes = m;                
    }
    
    public DateTime(String datetime) {
        String[] fields = datetime.split(" ");
        String[] dateFields = fields[0].split("/");
        month = Integer.parseInt(dateFields[0]);
        day = Integer.parseInt(dateFields[1]);
        year = Integer.parseInt(dateFields[2]);
        
        String[] timeFields = fields[1].split(":"); 
        hours = Integer.parseInt(timeFields[0]);
        minutes = Integer.parseInt(timeFields[1]);;
    }
    
	public int compareTo(DateTime x) {
		if(x.year != this.year)
			return new Integer(this.year).compareTo(x.year);
		if(x.month != this.month)
			return new Integer(this.month).compareTo(x.month);
		if(x.day != this.day)
			return new Integer(this.day).compareTo(x.day);
		if(x.hours != this.hours)
			return new Integer(this.hours).compareTo(x.hours);
		return new Integer(this.minutes).compareTo(x.minutes);
	}
	
	@Override
	public boolean equals(Object o) {
		DateTime x = (DateTime)o;
		return x.year == this.year && x.month == this.month && x.day == this.day && x.hours == this.hours && x.minutes == this.minutes;
	}
	
	@Override
	public int hashCode() {
		int res = 1;
		res = 31 * res + month;
		res = 31 * res + day;
		res = 31 * res + hours;
		res = 31 * res + minutes;
		return res;
	}
	
    public String toString() {
        return Integer.toString(month)+"/"+Integer.toString(day)+"/"+Integer.toString(year)+"  "+
            String.format("%02d" , hours)+":"+String.format("%02d", minutes);
    }   
    
}