
public class Arreglo {


    public static void main(String[] args) {


        int[] impares = new int[50];


	impares=arreglo_impares(impares);
        

	System.out.println("la suma de arreglo es:"+sumaelementos(impares));

	}
	public static int sumaelementos ( int[] newimpares ){

	int suma =0;	
	for(int numeros: newimpares) {

	suma+=numeros;

	}

 	return suma;

    }


	public static int[] arreglo_impares  ( int[] newimpares ){
	int indiceArreglo = 0;

        for (int i = 1; i <= 100; i++) {


            if (i % 2 != 0) {

                newimpares[indiceArreglo] = i;
		
        	System.out.println(newimpares[indiceArreglo] );

                indiceArreglo++;

            }

        }

	
	return newimpares;
    }





}
