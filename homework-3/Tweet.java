/**
 * Represent a tweet, including the content, author's username
 * and time when it was posted. 
 */
public class Tweet {
    
    public String user;
    public DateTime datetime; 
    public String content;
    
    public Tweet(String user, DateTime datetime, String content) {
            this.user = user; 
            this.datetime = datetime;
            this.content = content;       
    }
	
	@Override
	public boolean equals(Object o) {
		Tweet t = (Tweet)o;
		if(o == null)
			return false;
		return t.user.equals(this.user) && t.datetime.equals(this.datetime) && t.content.equals(this.content);
	}
	
	@Override
	public int hashCode() {
		int res = 1;
		res = 31 * res + user.hashCode();
		res = 31 * res + datetime.hashCode();
		res = 31 * res + content.hashCode();
		return res;
	}
    
    public String toString(){
        return "@"+this.user+" ["+datetime.toString()+"]: "+content;
    }
    
}