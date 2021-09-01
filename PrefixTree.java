public class PrefixTree {
	
	private char character;
	private PrefixTree left;
	private PrefixTree right;



	
	
	private PrefixTree() {
		if (!StdIn.isEmpty()){
			char symbol = StdIn.readChar();
			if (symbol == '*'){
				character = symbol;
				left = new PrefixTree();
				right = new PrefixTree();
			}
			else{
				character = symbol;
			}
		}
	}
	
	public void preorder(){
		preorder("");
	}
	
	public void preorder(String code){
		if (character !='*'){
			StdOut.println(character + "  " + code.length() + "  " + code);
			code = "";
		}
		if (left != null){
			code = code + "0";
			left.preorder(code);
		}
		if (right != null){
			code = (code.substring(0, code.length() - 1) + "1");
			right.preorder(code);
		}
	}
	
	public void uncompress(){
		double counter = 0.0;
		double counter2 = 0.0;
		PrefixTree x = this;
		PrefixTree x2 = x;
		while (!StdIn.isEmpty()){
		x2 = x;
		while (x2.character == '*' && StdIn.hasNextChar()){
			char bit = StdIn.readChar();
			if (bit == '0' || bit == '1'){
				counter2++;
			}
			if (bit == '0') x2 = x2.left;
			if (bit == '1') x2 = x2.right;
		}
		StdOut.print(x2.character);
		counter++;

		}
		
		StdOut.println("Number of bits = " + (int) (counter2));
		StdOut.println("Number of characters = " + (int) counter);
		StdOut.println("Compression ratio = " + ((counter2) / (counter * 8)));

	}
	
	public static void main(String[] args) {
		PrefixTree tree = new PrefixTree();
		tree.preorder();
		tree.uncompress();
	}

	
	
}
