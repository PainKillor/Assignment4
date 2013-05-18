import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AppDriver {
	
	public static final String PATH = "C:\\Users\\brian\\Desktop\\Words.txt";
	
	public static void main(String[] args) {
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(PATH));
			
			String string;
			while ((string = br.readLine()) != null)
				bst.add(string);
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int i = 0;
		Iterator<String> it = bst.iterator();
		while (it.hasNext())
			System.out.println(++i + ") " + it.next());
		
		RenderBST<String> rbst = new RenderBST<String>(bst);
		rbst.open();
		
		bst.remove("Automaton");
		bst.remove("Vectored Processor");
		
		i = 0;
		it = bst.iterator();
		while (it.hasNext())
			System.out.println(++i + ") " + it.next());
		
		rbst = new RenderBST<String>(bst);
		rbst.open();
	}
}
