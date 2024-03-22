package lesson5.presenters;

import lesson5.models.TableService;
import lesson5.views.BookingView;

import java.util.Date;

public class BookingPresenter implements ViewObserver {

    private Model model;
    private  View view;

    public BookingPresenter(Model model, View view){
        this.model = model;
        this.view = view;
        view.registerObserver(this);
    }

    public void updateTablesView(){
        view.showTables(model.loadTables());
    }

    private void updateReservationTableView(int reservationNo){
        view.showReservationTableResult(reservationNo);
    }

    @Override
    public void onReservationTable(Date orderDate, int tableNo, String name) {
        try {
            int reservationNo = model.reservationTable(orderDate, tableNo, name);
            updateReservationTableView(reservationNo);
        }
        catch (Exception e){
            updateReservationTableView(-1);
        }

        

    }

    @Override
    public void onChangeReservationTable(int oldReservationNumb, Date newDate, int newTable, String newName) {
        try {
        System.out.println("_______\nПроверка работы перерезервации");    
        model.removeReservationTable(oldReservationNumb);    
        int newReservationNo = model.reservationTable(newDate, newTable, newName);
        updateReservationTableView(newReservationNo);
        }
        catch (Exception e) {
            System.out.println("Произошла ошибка: не найден номер заказа, либо некорректно указан номер столика");
        }
    }
}
