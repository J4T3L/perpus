import Controllers.RootController;
import Models.DetailTransaksiModel;
import Models.BukuModel;
import Models.UserModel;

public class App {

public static void main(String[] args) {
    initialData();
    new RootController().loginPage();
}

public static void initialData()
{
    UserModel.initialData();
    BukuModel.initialData();
    DetailTransaksiModel.initialData();
}
} 

