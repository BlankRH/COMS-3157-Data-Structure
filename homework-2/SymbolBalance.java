import java.io.*;

public class SymbolBalance {
	
		public static boolean checkMatch(char a, char b) {
				if(a == '*' || a == '\"')
					return a == b;
				if(a == '[') return b == ']';
				if(a == '(') return b == ')';
				if(a == '{') return b == '}';
				return false;
			}

        public static BalanceError checkFile(String pathToFile) { 
            int buffer = 0;
            char currentSymbol;
			Stack<Character> s = new ArrayStack<Character>();
			
			int lineCount = 1;
			
			//for test
			int starCount = 0;
			int bracketCount = 0;
			
			try {
				FileReader fr = new FileReader(pathToFile);
				boolean quotation = false, comment = false;
				while((buffer = fr.read()) != -1){
					currentSymbol = (char)buffer;
					if(currentSymbol == '\n'){
						lineCount++;
						continue;
					}
					if(!quotation && !comment && currentSymbol == '\"') {
						quotation = true;
						s.push(currentSymbol);
						//System.out.print("“");
						continue;
					}
					else if(quotation && currentSymbol == '\"') {
						quotation = false;
						s.pop();
						//System.out.println("”"); 
						continue;
					}
					if(!comment && !quotation && currentSymbol == '/') {
						// a / b /*
						currentSymbol = (char)fr.read();
						if(currentSymbol == '*'){
							s.push(currentSymbol);
							comment = true;
							//System.out.print("/*"); 
							continue;
						}
					}
					if(comment && currentSymbol == '*') {
						currentSymbol = (char)fr.read();
						if(currentSymbol == '/') {
							s.pop();
							comment = false;
							//System.out.println("*/"); 
							continue;
						}
					}
					if(comment || quotation) {
						//System.out.print(currentSymbol);
						continue;
					}
					if(currentSymbol == '{' || currentSymbol == '(' || currentSymbol == '[')
						s.push(currentSymbol);
					if(currentSymbol == '}' || currentSymbol == ')' || currentSymbol == ']') {
						 //System.out.print(currentSymbol);
						 if(!checkMatch(s.top(), currentSymbol))
							 return new MismatchError(lineCount, currentSymbol, s.top());
						 else
							 s.pop();
					 }			    
				}
			} catch(FileNotFoundException e) {
				System.out.println(e.toString());
			} catch(IOException e) {
				System.out.println(e.toString());
			} catch(IndexOutOfBoundsException e) {
				return new EmptyStackError(lineCount);
			}
			
			if(!s.isEmpty()) {
				char top = s.top();
				int size = 0;
				while(!s.isEmpty()) {
					s.pop();
					size += 1;
				}
				return new NonEmptyStackError(top, size);
			}
			return null;
        }
    
    
        public static void main(String[] args) {
			BalanceError e = checkFile("TestFiles/" + args[0]);
			if(e != null)
				System.out.println(e.toString());
			else
				System.out.println("OK");
            
        }
    
}