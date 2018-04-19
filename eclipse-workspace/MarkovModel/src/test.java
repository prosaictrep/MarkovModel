

public class test {

	    public static String MarkovModel(String text, int k) {
	        int order = k;
	        StringBuilder stringBuffer = new StringBuilder();
	        if (order < text.length()) {
	     	   for (int i = 0; i < order - 1; i++) {
	     	     stringBuffer.append(text.charAt(i));
	     	   }
	     	   }
	     	   String hello = text + stringBuffer.toString();
	     	   return hello;
	    }


	    public static void main(String[] args) {
	    	 System.out.println(MarkovModel("bannnana",3));
	    }
	}

