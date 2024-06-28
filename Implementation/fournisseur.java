package Implementation;
import java.util.ArrayList;

public class fournisseur {
    String name;
    String addresse;
    String email;
    String phone;
    ArrayList<String> items;
    int id;
    public fournisseur(String name, String addresse, String email, String phone, ArrayList<String> items, int id){
        this.name = name;
        this.addresse = addresse;
        this.email = email;
        this.phone = phone;
        this.items = items;
        this.id = id;
    }

    public void toSale(){
        for(int i=0; i<items.size(); i++){
            System.out.println(items.get(i) + " est disponible pour la vente.");
        }
    }
}
