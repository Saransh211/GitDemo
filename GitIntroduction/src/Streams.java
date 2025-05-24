import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Streams {
	public static void main(String[] args) {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Abi");
		names.add("Don");
		names.add("Al");
		names.add("Adam");
		names.add("Ram");
		
		//names.stream - is converting the names arraylist to a stream
		//filter - method filter all the names starts with A
		//count - method return the cound
		long c = names.stream().filter(s->s.startsWith("A")).count();
		System.out.println("Printing count of names starting with the letter A - "+c);
		//Output - Printing count of names starting with the letter A - 3
		
		System.out.print("Printing 1 name having length more than 2 - ");
		names.stream().filter(s->s.length()>2).limit(1).forEach(s->System.out.println(s));
		//Output - Printing 1 name having length more than 2 - Abi
		
		System.out.print("Printing names ending with n in Upper Case - ");
		Stream.of("Abi","Don","Al","Adam","Ram").filter(s->s.endsWith("n")).map(s->s.toUpperCase()).forEach(s->System.out.println(s));
		//Output - Printing names ending with n in Upper Case - DON
		
		System.out.print("Printing names which have first letter as a with uppercase and sorted - ");
		names.stream().filter(s->s.startsWith("A")).map(s->s.toUpperCase()).sorted().forEach(s->System.out.print(s+" "));
		//Output - Printing names which have first letter as a with uppercase and sorted - ABI ADAM AL 
		
		String[] arr = new String[]{"Abi","Don","Al","Adam","Rama"};
		List<String> shortNames = Arrays.asList(arr);
		System.out.println();
		System.out.print("Printing name starting with D and sorted - ");
		shortNames.stream().filter(s->s.startsWith("D")).sorted().map(s->s.toUpperCase()).forEach(s->System.out.print(s+" "));
		//Output - Printing name starting with D and sorted - DON 
		
		//Concatinate two streams
		String[] arr1 = new String[] {"One","Two","Three","Four"};
		String[] arr2 = new String[] {"Five","Six","Seven","Eight"};
		List<String> Listarr1 = Arrays.asList(arr1);
		List<String> Listarr2 = Arrays.asList(arr2);
		Stream<String> newStream = Stream.concat(Listarr1.stream(), Listarr2.stream());
		System.out.println("");
		System.out.print("Printing two concatenated streams - ");
		newStream.forEach(s->System.out.print(s+" "));
		//Output - Printing two concatenated streams - One Two Three Four Five Six Seven Eight 
		
		//Check whether three is present in the new Stream
		Stream<String> newStream1 = Stream.concat(Listarr1.stream(), Listarr2.stream());
		boolean flag = newStream1.anyMatch(s->s.equalsIgnoreCase("Three"));
		System.out.println();
		System.out.print("Three is present in the newStream1 - "+flag);
		//Output - Three is present in the newStream - true
		
		//Take list -> convert to stream -> convert it back to list
		String[] arr3 = new String[] {"One","Two","Three","Four"};
		String[] arr4 = new String[] {"Five","Six","Seven","Eight"};
		List<String> Listarr3 = Arrays.asList(arr1);
		List<String> Listarr4 = Arrays.asList(arr2);
		Stream<String> newStream2 = Stream.concat(Listarr3.stream(), Listarr4.stream());
		System.out.println("");
		List<String> ls = newStream2.filter(s->s.startsWith("T")).map(s->s.toUpperCase()).collect(Collectors.toList());
		System.out.print(ls.getFirst());
		//Output - TWO
		
		
	}
}
