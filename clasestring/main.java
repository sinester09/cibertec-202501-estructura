public class Main {
private static String contarvocales(char []a ){
int conta=0,conte=0,conti=0,conto=0,contu=0;
for (int i=0;i<a.length;i++){
    switch (a[i]) {

        case 'a' -> conta++;
        case 'e' -> conte++;
        case 'i' -> conti++;
        case 'o' -> conto++;
        case 'u' -> contu++;
        default -> {


        }
    }

}
return "";
}

@SuppressWarnings("unused")
private static String contarvocales2(String palabra){

return "";

}

public static void main (String[] args ){

System.out.println(contarvocales("yuyes".toCharArray()));
    
}



}
