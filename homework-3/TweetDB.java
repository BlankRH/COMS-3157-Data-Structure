import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap; 
import java.util.HashSet;
import java.util.TreeMap; 
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class TweetDB {
   
    
    HashMap<String, List<Tweet>> tweetsPerUser;
    HashMap<String, List<Tweet>> tweetsPerKeyword;  
    TreeMap<DateTime, List<Tweet>> tweetsByTime;
    
    public TweetDB(String pathToFile) throws FileNotFoundException, IOException {
        tweetsPerUser = new HashMap<>();               
        tweetsPerKeyword = new HashMap<>();   
        tweetsByTime = new TreeMap<>();

		BufferedReader br = new BufferedReader(new FileReader(pathToFile));
		CsvReader cr = new CsvReader(br);
		String[] line;
		while((line = cr.nextLine()) != null) {
			String user = line[0];
			DateTime datetime = new DateTime(line[2]);
			String content = line[1];
			Tweet t = new Tweet(user, datetime, content);
			
			//put tweets into tweets per user
			List<Tweet> list;		
			if(tweetsPerUser.containsKey(user)) {
				tweetsPerUser.get(user).add(t);
			}
			else {
				list = new ArrayList<>();
				list.add(t);
				tweetsPerUser.put(user, list);
			}
			
			//put tweets into tweets per keyword
			String[] keywords = getKeywords(content);
			for(String kw : keywords) {
				if(tweetsPerKeyword.containsKey(kw)) {
					list = tweetsPerKeyword.get(kw);
					if(!list.contains(t))
						list.add(t);
				}
				else {
					list = new ArrayList<>();
					list.add(t);
					tweetsPerKeyword.put(kw, list);
				}
			}
			
			//put tweets into tweetsByTime
			if(tweetsByTime.containsKey(datetime))
				tweetsByTime.get(datetime).add(t);
			else {
				list = new ArrayList<>();
				list.add(t);
				tweetsByTime.put(datetime, list);
			}

		}
    } 
	
	public String[] getKeywords(String content) {
		content = content.replaceAll("[,.?!'\"()*@#{}|/^%$&-+~=`:;<>]", "");
		String[] keywords = content.split(" ");
		return keywords;
	}

    public List<Tweet> getTweetsByUser(String user) {
		HashSet<Tweet> hs = new HashSet<>(tweetsPerUser.get(user));
		List<Tweet> l = new ArrayList<>(hs);
        return l;
    }  

    public List<Tweet> getTweetsByKeyword(String kw) {
		HashSet<Tweet> hs = new HashSet<>(tweetsPerKeyword.get(kw));
		List<Tweet> l = new ArrayList<>(hs);
        return l;
    }
    
     public List<Tweet> getTweetsInRange(DateTime start, DateTime end) {
		 HashSet<Tweet> hs = new HashSet<>();
		 for(List<Tweet> i : tweetsByTime.subMap(start, end).values())
			 hs.addAll(i);
		 List<Tweet> l = new ArrayList<>(hs);
         return l;
    }  
	
    
    public static void main(String[] args) {
          
        try {
            TweetDB tdb = new TweetDB("coachella_tweets.csv");

           /** Part 1: Search by username */
           //for(Tweet t : tdb.getTweetsByUser("hannah_frog"))
             //   System.out.println(t);
            
              
           /** Part 2: Search by keyword */
			//for(Tweet t : tdb.getTweetsByKeyword("92"))
                //System.out.println(t);
            
           /** Part 3: Search by date/time interval          
        */  for(Tweet t : tdb.getTweetsInRange(new DateTime("1/7/15 00:00"), new DateTime("1/7/15 0:30")))
                System.out.println(t);
            
        } catch (FileNotFoundException e) {
            System.out.println(".csv File not found.");
        } catch (IOException e) {
            System.out.println("Error reading from .csv file.");
        }
        
        
        
        
    }
    
}